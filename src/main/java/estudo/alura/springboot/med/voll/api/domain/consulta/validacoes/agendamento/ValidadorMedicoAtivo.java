package estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.agendamento;

import estudo.alura.springboot.med.voll.api.domain.ValidacaoException;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import estudo.alura.springboot.med.voll.api.domain.medico.iMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements iValidadorAgendamentoDeConsulta {

    @Autowired
    private iMedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        //Escolha do médico é opcional
        if(dados.idMedico() == null)
            return;

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if(!medicoEstaAtivo)
            throw new ValidacaoException("A consulta não pode ser agendada com um médico inativo");

    }

}
