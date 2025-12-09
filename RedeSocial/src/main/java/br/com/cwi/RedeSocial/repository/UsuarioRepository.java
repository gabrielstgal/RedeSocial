package br.com.cwi.RedeSocial.repository;

import br.com.cwi.RedeSocial.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(String nomeCompleto, String email);


}

