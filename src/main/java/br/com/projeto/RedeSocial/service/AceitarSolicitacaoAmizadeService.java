package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.StatusAmizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import br.com.cwi.RedeSocial.service.validator.AmizadeValidator;
import br.com.cwi.RedeSocial.service.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AceitarSolicitacaoAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmizadeValidator amizadeValidator;

    @Autowired
    private UsuarioValidator usuarioValidator;

    public void aceitar(Long idSolicitante) {

        Usuario usuarioLogado = usuarioAutenticadoService.get();

        Usuario solicitante = usuarioValidator.validarEBuscarUsuario(idSolicitante);

        Amizade amizade = amizadeValidator.buscarSolicitacaoPendente(solicitante, usuarioLogado);

        amizade.setStatus(StatusAmizade.ACEITA);
        amizadeRepository.save(amizade);

    }
}
