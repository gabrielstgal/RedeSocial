package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.StatusAmizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.mapper.UsuarioMapper;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ListarSolicitacoesRecebidasService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public List<UsuarioResponse> listar() {
        Usuario usuarioLogado = usuarioAutenticadoService.get();

        List<Amizade> solicitacoes = amizadeRepository.findAllBySolicitadoAndStatus(
                usuarioLogado,
                StatusAmizade.PENDENTE
        );

        return UsuarioMapper.toResponseListAmizade(solicitacoes);
    }
}
