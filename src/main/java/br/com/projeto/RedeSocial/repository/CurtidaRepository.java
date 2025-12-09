package br.com.cwi.RedeSocial.repository;

import br.com.cwi.RedeSocial.domain.Curtida;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    Optional<Curtida> findByUsuarioAndPostagem(Usuario usuario, Postagem postagem);
}
