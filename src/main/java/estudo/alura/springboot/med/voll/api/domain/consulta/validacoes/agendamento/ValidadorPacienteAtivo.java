package estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.agendamento;

import estudo.alura.springboot.med.voll.api.domain.ValidacaoException;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import estudo.alura.springboot.med.voll.api.domain.paciente.iPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements iValidadorAgendamentoDeConsulta {

    @Autowired
    private iPacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if(!pacienteEstaAtivo)
            throw new ValidacaoException("A consulta não pode ser agendada para um paciente excluído");

    }

}
