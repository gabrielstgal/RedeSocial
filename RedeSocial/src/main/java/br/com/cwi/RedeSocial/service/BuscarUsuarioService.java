package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.mapper.UsuarioMapper;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse buscar() {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        return UsuarioMapper.toResponse(usuarioAutenticado);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}