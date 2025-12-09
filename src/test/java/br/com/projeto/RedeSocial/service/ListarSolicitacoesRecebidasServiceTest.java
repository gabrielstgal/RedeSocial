package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.StatusAmizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarSolicitacoesRecebidasServiceTest {

    @InjectMocks
    private ListarSolicitacoesRecebidasService listarSolicitacoesRecebidasService;

    @Mock
    private AmizadeRepository amizadeRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    private final Usuario usuarioLogado = Usuario.builder().id(2L).build();
    private final Usuario solicitante = Usuario.builder().id(1L).build();
    private final Amizade solicitacao = Amizade.builder().solicitante(solicitante).solicitado(usuarioLogado).status(StatusAmizade.PENDENTE).build();

    @Test
    void deveChamarRepositoryComStatusPendenteEUsuarioLogado() {
        // Arrange
        List<Amizade> solicitacoes = Collections.singletonList(solicitacao);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(amizadeRepository.findAllBySolicitadoAndStatus(usuarioLogado, StatusAmizade.PENDENTE)).thenReturn(solicitacoes);

        // Act
        listarSolicitacoesRecebidasService.listar();

        // Assert
        verify(usuarioAutenticadoService).get();
        verify(amizadeRepository).findAllBySolicitadoAndStatus(usuarioLogado, StatusAmizade.PENDENTE);
    }
}