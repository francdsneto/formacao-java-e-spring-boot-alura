package estudo.alura.springboot.med.voll.api.medico;

import estudo.alura.springboot.med.voll.api.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
                                    @NotNull
                                    Long id,
                                    String nome,
                                    String telefone,
                                    @Valid
                                    DadosEndereco endereco) {
}
