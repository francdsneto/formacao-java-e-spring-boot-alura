package estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.agendamento;

import estudo.alura.springboot.med.voll.api.domain.ValidacaoException;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import estudo.alura.springboot.med.voll.api.domain.consulta.iConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoOutraConsultaNoMesmoHorario implements iValidadorAgendamentoDeConsulta {

    @Autowired
    private iConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        var medicoPossuoOutraConsultaNoMesmoDiaEHorario = this.consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(),dados.data());

        if(medicoPossuoOutraConsultaNoMesmoDiaEHorario)
        {
            throw new ValidacaoException("O Médico escolhido para consulta já possui outra consulta agendada neste mesmo horário");
        }

    }

}
