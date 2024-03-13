package estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.agendamento;

import estudo.alura.springboot.med.voll.api.domain.ValidacaoException;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements iValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();

        var agora = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(agora,dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30)
        {
            throw new ValidacaoException("Consulta deve ser agendada com atecedência minima de 30 minutos");
        }

    }

}
