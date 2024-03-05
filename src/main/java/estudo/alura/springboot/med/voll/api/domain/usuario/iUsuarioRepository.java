package estudo.alura.springboot.med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface iUsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
