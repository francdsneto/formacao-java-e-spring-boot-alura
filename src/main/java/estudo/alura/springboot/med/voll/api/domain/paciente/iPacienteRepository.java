package estudo.alura.springboot.med.voll.api.domain.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface iPacienteRepository extends JpaRepository<Paciente,Long> {

    @Query("""
            SELECT 
                p.ativo
            FROM 
                Paciente p
            WHERE 
                p.id = :idPaciente
            """)
    Boolean findAtivoById(Long idPaciente);
}
