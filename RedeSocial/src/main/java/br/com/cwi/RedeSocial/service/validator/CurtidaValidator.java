package br.com.cwi.RedeSocial.service.validator;

import br.com.cwi.RedeSocial.domain.Curtida;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CurtidaValidator {

    @Autowired
    private CurtidaRepository curtidaRepository;

    public void validarDuplicidade(Usuario usuario, Postagem postagem) {
        if (curtidaRepository.findByUsuarioAndPostagem(usuario, postagem).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já curtiu esta postagem.");
        }
    }


    public Curtida buscarCurtidaExistente(Usuario usuario, Postagem postagem) {
        return curtidaRepository.findByUsuarioAndPostagem(usuario, postagem)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não curtiu esta postagem para removê-la.")
                );
    }
}