package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.AtualizarUsuarioRequest;
import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.mapper.UsuarioMapper;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse atualizar(AtualizarUsuarioRequest request) {

        Usuario usuario = usuarioAutenticadoService.get();

        usuario.setNomeCompleto(request.getNomeCompleto());
        usuario.setApelido(request.getApelido());
        usuario.setImagemPerfil(request.getImagemPerfil());

        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }
}

