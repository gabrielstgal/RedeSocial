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
public class SolicitarAmizadeService {

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

    public void solicitar(Long idSolicitado) {

        Usuario solicitante = usuarioAutenticadoService.get();

        Usuario solicitado = usuarioValidator.validarEBuscarUsuario(idSolicitado);

        amizadeValidator.validarNovaSolicitacao(solicitante, solicitado);

        Amizade amizade = new Amizade();
        amizade.setSolicitante(solicitante);
        amizade.setSolicitado(solicitado);
        amizade.setStatus(StatusAmizade.PENDENTE);

        amizadeRepository.save(amizade);

    }
}

