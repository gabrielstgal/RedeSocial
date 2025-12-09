package br.com.cwi.RedeSocial.service.validator;

import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.StatusAmizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AmizadeValidator {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public void validarNovaSolicitacao(Usuario solicitante, Usuario solicitado) {
        if (solicitante.getId().equals(solicitado.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível enviar solicitação de amizade para si mesmo.");
        }

        if (amizadeRepository.findViculoEntre(solicitante, solicitado).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um vínculo (amizade ou solicitação pendente) com este usuário.");
        }
    }

    public Amizade buscarSolicitacaoPendente(Usuario solicitante, Usuario solicitado) {
        return amizadeRepository
                .findBySolicitanteAndSolicitadoAndStatus(
                        solicitante,
                        solicitado,
                        StatusAmizade.PENDENTE
                )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe uma solicitação de amizade pendente deste usuário."));
    }


    public Amizade buscarVinculoParaRemocao(Usuario usuarioLogado, Usuario amigo) {
        return amizadeRepository
                .findViculoEntre(usuarioLogado, amigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe vínculo de amizade ou solicitação pendente com este usuário para ser removido."));
    }
}