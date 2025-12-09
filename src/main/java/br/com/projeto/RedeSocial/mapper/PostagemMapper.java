package br.com.cwi.RedeSocial.mapper;

import br.com.cwi.RedeSocial.controller.request.PostagemRequest;
import br.com.cwi.RedeSocial.controller.response.PostagemResponse;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class PostagemMapper {

    public static Postagem toEntity(PostagemRequest request, Usuario usuario) {
        Postagem entity = new Postagem();
        entity.setUsuario(usuario);
        entity.setConteudo(request.getConteudo());
        entity.setDataPostagem(LocalDateTime.now());
        entity.setPublico(request.getPublico());
        return entity;
    }

    public static PostagemResponse toResponse(Postagem entity) {
        return PostagemResponse.builder()
                .id(entity.getId())
                .usuario(UsuarioMapper.toResponse(entity.getUsuario()))
                .conteudo(entity.getConteudo())
                .dataPostagem(entity.getDataPostagem())
                .publico(entity.isPublico())
                .build();
    }
}