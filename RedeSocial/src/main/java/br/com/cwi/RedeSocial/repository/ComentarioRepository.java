package br.com.cwi.RedeSocial.repository;

import br.com.cwi.RedeSocial.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findAllByPostagemIdOrderByDataComentarioAsc(Long postagemId);
}
