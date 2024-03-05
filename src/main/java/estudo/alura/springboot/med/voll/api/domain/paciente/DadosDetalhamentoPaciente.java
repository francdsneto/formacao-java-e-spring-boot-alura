package estudo.alura.springboot.med.voll.api.domain.paciente;

import estudo.alura.springboot.med.voll.api.domain.endereco.Endereco;
import estudo.alura.springboot.med.voll.api.domain.paciente.Paciente;

public record DadosDetalhamentoPaciente(Long id, String email, String telefone, String cpf, Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
       this(paciente.getId(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }

}
