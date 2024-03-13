package estudo.alura.springboot.med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface iConsultaRepository extends JpaRepository<Consulta,Long> {


    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long aLong, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
