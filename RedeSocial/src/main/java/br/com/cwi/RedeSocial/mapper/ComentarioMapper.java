package br.com.cwi.RedeSocial.mapper;

import br.com.cwi.RedeSocial.controller.request.ComentarioRequest;
import br.com.cwi.RedeSocial.controller.response.ComentarioResponse;
import br.com.cwi.RedeSocial.domain.Comentario;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ComentarioMapper {

    public static Comentario toEntity(ComentarioRequest request, Usuario usuario, Postagem postagem) {
        Comentario entity = new Comentario();
        entity.setUsuario(usuario);
        entity.setPostagem(postagem);
        entity.setConteudo(request.getConteudo());
        entity.setDataComentario(LocalDateTime.now());
        return entity;
    }

    public static ComentarioResponse toResponse(Comentario entity) {
        return ComentarioResponse.builder()
                .id(entity.getId())
                .autor(UsuarioMapper.toComentarioAutorResponse(entity.getUsuario()))
                .conteudo(entity.getConteudo())
                .dataComentario(entity.getDataComentario())
                .build();
    }

    public static List<ComentarioResponse> toResponseList(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(ComentarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}