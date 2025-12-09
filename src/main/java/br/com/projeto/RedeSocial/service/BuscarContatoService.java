package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.mapper.UsuarioMapper;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarContatoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public List<UsuarioResponse> buscar(String text) {

        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Long usuarioLogadoId = usuarioLogado.getId();

        List<Usuario> resultados = usuarioRepository
                .findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(text, text);

        List<Amizade> amizadesRelacionadas = amizadeRepository.findAllBySolicitanteIdOrSolicitadoId(usuarioLogadoId, usuarioLogadoId);

        List<Long> IdRelacionado = amizadesRelacionadas.stream()
                .map(amizade -> amizade.getSolicitante().getId().equals(usuarioLogadoId) ?
                        amizade.getSolicitado().getId() :
                        amizade.getSolicitante().getId())
                .distinct()
                .collect(Collectors.toList());

        IdRelacionado.add(usuarioLogadoId);

        List<Usuario> filtrados = resultados.stream()
                .filter(u -> !IdRelacionado.contains(u.getId()))
                .collect(Collectors.toList());

        return UsuarioMapper.toResponseList(filtrados);
    }
}