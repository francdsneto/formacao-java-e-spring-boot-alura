package estudo.alura.springboot.med.voll.api.domain.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface iPacienteRepository extends JpaRepository<Paciente,Long> {
}
