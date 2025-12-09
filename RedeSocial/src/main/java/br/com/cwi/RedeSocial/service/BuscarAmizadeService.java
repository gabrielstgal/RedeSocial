package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.StatusAmizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BuscarAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;


    public Set<Usuario> buscar() {
        Long UsuarioLogadoId = usuarioAutenticadoService.get().getId();

        List<Amizade> todosVinculos = amizadeRepository.findAllBySolicitanteIdOrSolicitadoId(UsuarioLogadoId, UsuarioLogadoId);


        return todosVinculos.stream()
                .filter(amizade -> amizade.getStatus() == StatusAmizade.ACEITA)
                .map(amizade -> amizade.getSolicitante().getId().equals(UsuarioLogadoId) ?
                        amizade.getSolicitado() :
                        amizade.getSolicitante())
                .collect(Collectors.toSet());
    }
}

