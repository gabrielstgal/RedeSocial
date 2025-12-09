package br.com.cwi.RedeSocial.service.validator;

import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class PostagemValidator {

    @Autowired
    private PostagemRepository postagemRepository;

    public Postagem validarEBuscarPostagem(Long id) {
        return postagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada."));
    }

    public void validarAutorizacao(Postagem postagem, Long idUsuarioLogado) {
        if (!postagem.getUsuario().getId().equals(idUsuarioLogado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Não tem permissão para alterar as permissões desta postagem.");
        }
    }
}