package estudo.alura.springboot.med.voll.api.domain.consulta;

import estudo.alura.springboot.med.voll.api.domain.ValidacaoException;
import estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.agendamento.iValidadorAgendamentoDeConsulta;
import estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.cancelamento.iValidadorCancelamentoConsulta;
import estudo.alura.springboot.med.voll.api.domain.medico.Medico;
import estudo.alura.springboot.med.voll.api.domain.medico.iMedicoRepository;
import estudo.alura.springboot.med.voll.api.domain.paciente.iPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private iConsultaRepository consultaRepository;
    @Autowired
    private iMedicoRepository medicoRepository;
    @Autowired
    private iPacienteRepository pacienteRepository;

    @Autowired
    private List<iValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<iValidadorCancelamentoConsulta> validadoresDeCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if(!pacienteRepository.existsById(dados.idPaciente()))
        {
            throw new ValidacaoException("Não existe um paciente com o id informado!");
        }

        /**
         * Caso o médico não tenha sido informado no momento do cadastro, um médico
         * aletório que esteja disponível será selecionado.
         * Por tal motivo, o idMedico pode vir nulo do frontend.
         */
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
        {
            throw new ValidacaoException("Não existe um médico com o id informado!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = this.pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if(medico == null)
        {
            throw new ValidacaoException("Não existe médico disponível para esta data!");
        }

        var consulta = new Consulta(null,medico,paciente,dados.data(),null);

        consulta = consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {

        if(!consultaRepository.existsById(dados.idConsulta()))
        {
            throw new ValidacaoException("Não existe uma consulta com o id informado");
        }

        validadoresDeCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        consulta.cancelar(dados.motivo());

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null)
        {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null)
        {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido.");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());

    }

}
