package estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.agendamento;

import estudo.alura.springboot.med.voll.api.domain.ValidacaoException;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements iValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) {

        var isDomingo = dados.data().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isAntesHorarioAberturaClinica = dados.data().getHour() < 7;
        var isDepoisHorarioFechamentoClinica = dados.data().getHour() > 18;

        if(isDomingo || isAntesHorarioAberturaClinica || isDepoisHorarioFechamentoClinica)
        {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clinica!");
        }

    }

}
