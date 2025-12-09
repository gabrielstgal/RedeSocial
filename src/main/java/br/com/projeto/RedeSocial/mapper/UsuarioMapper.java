package br.com.cwi.RedeSocial.mapper;

import br.com.cwi.RedeSocial.controller.request.UsuarioRequest;
import br.com.cwi.RedeSocial.controller.response.ComentarioAutorResponse;
import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNomeCompleto(request.getNomeCompleto());
        entity.setEmail(request.getEmail());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setImagemPerfil(request.getImagemPerfil());
        entity.setEstadio(request.getEstadio());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        return UsuarioResponse.builder()
                .nomeCompleto(entity.getNomeCompleto())
                .apelido(entity.getApelido())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .imagemPerfil(entity.getImagemPerfil())
                .estadio(entity.getEstadio())
                .build();
    }

    public static List<UsuarioResponse> toResponseListAmizade(List<Amizade> solicitacoes) {
        return solicitacoes.stream()
                .map(Amizade::getSolicitante)
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static List<UsuarioResponse> toResponseList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static ComentarioAutorResponse toComentarioAutorResponse(Usuario entity) {
        return ComentarioAutorResponse.builder()
                .id(entity.getId())
                .apelido(entity.getApelido())
                .build();
    }
}