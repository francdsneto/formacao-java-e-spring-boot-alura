package estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.agendamento;

import estudo.alura.springboot.med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public interface iValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados);

}
