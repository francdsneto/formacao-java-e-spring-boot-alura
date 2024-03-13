package estudo.alura.springboot.med.voll.api.domain.consulta.validacoes.cancelamento;

import estudo.alura.springboot.med.voll.api.domain.ValidacaoException;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import estudo.alura.springboot.med.voll.api.domain.consulta.iConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements iValidadorCancelamentoConsulta {

    @Autowired
    private iConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        Duration duracao = Duration.between(LocalDateTime.now(),consulta.getData());

        if(duracao.toHours() < 24)
        {
            throw new ValidacaoException("A consulta não pode ser cancelada, pois o cancelamento foi solicitado faltando menos de 24h para o atendimento.");
        }

    }

}
