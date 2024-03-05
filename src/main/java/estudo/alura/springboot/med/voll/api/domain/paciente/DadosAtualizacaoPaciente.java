package estudo.alura.springboot.med.voll.api.domain.paciente;

import estudo.alura.springboot.med.voll.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull
                                        Long id,
                                       String nome,
                                       String telefone,
                                       @Valid
                                       DadosEndereco endereco) {
}
