package estudo.alura.springboot.med.voll.api.controller;

import estudo.alura.springboot.med.voll.api.domain.consulta.AgendaDeConsultas;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import estudo.alura.springboot.med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import estudo.alura.springboot.med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    private AgendaDeConsultas agendaDeConsultas;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                            .andReturn()
                            .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosAgendamento = new DadosAgendamentoConsulta(1l,1l, data, especialidade);
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null,1l,1l,data);

        // Substituindo o any pelo parâmetro passado no request
        // when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        when(agendaDeConsultas.agendar(dadosAgendamento)).thenReturn(dadosDetalhamento);

        var response = mvc.perform(
                                    post("/consultas")
                                        .contentType(MediaType.APPLICATION_JSON)
                                            .content(
                                                    dadosAgendamentoConsultaJson
                                                            .write(dadosAgendamento)
                                                            .getJson())
                                )
                                .andReturn()
                                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson
                                .write(dadosDetalhamento)
                                    .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}