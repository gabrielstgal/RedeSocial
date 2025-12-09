package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.UsuarioRequest;
import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.mapper.UsuarioMapper;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import br.com.cwi.RedeSocial.service.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioValidator usuarioValidator;

    public UsuarioResponse cadastrar(UsuarioRequest request) {

        usuarioValidator.validarEmailUnico(request.getEmail());

        Usuario usuario = UsuarioMapper.toEntity(request);

        usuario.setSenha(passwordEncoder.encode(request.getSenha()));

        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }
}

