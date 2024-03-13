package estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.agendamento;

import estudo.alura.springboot.med.voll.api.domain.ValidacaoException;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import estudo.alura.springboot.med.voll.api.domain.consulta.iConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements iValidadorAgendamentoDeConsulta {

    @Autowired
    private iConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia)
            throw new ValidacaoException("O paciente já possui uma consulta agendada para este dia!");

    }

}
