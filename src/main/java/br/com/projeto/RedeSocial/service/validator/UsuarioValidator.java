package br.com.cwi.RedeSocial.service.validator;

import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UsuarioValidator {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarEmailUnico(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail informado já está em uso.");
        }
    }

    public Usuario validarEBuscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
    }
}
