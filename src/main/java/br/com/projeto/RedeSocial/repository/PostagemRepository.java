package br.com.cwi.RedeSocial.repository;

import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    Page<Postagem> findAllByUsuarioInOrPublicoTrueOrderByDataPostagemDesc(List<Usuario> autores, Pageable pageable);
}
