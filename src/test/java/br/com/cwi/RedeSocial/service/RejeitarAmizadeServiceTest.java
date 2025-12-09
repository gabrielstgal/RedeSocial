package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
import br.com.cwi.RedeSocial.service.validator.AmizadeValidator;
import br.com.cwi.RedeSocial.service.validator.UsuarioValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RejeitarAmizadeServiceTest {

    @InjectMocks
    private RejeitarAmizadeService rejeitarAmizadeService;

    @Mock
    private AmizadeRepository amizadeRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private UsuarioValidator usuarioValidator;
    @Mock
    private AmizadeValidator amizadeValidator;

    private final Usuario usuarioLogado = Usuario.builder().id(2L).build();
    private final Usuario solicitante = Usuario.builder().id(1L).build();
    private final Amizade amizadePendente = Amizade.builder().id(10L).build();

    @Test
    void deveRejeitarAmizadeComSucessoEDeletarRegistro() {
        // Arrange
        Long idSolicitante = 1L;

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(usuarioValidator.validarEBuscarUsuario(idSolicitante)).thenReturn(solicitante);
        when(amizadeValidator.buscarSolicitacaoPendente(solicitante, usuarioLogado)).thenReturn(amizadePendente);

        // Act
        assertDoesNotThrow(() -> rejeitarAmizadeService.rejeitar(idSolicitante));

        // Assert
        verify(usuarioValidator).validarEBuscarUsuario(idSolicitante);
        verify(amizadeValidator).buscarSolicitacaoPendente(solicitante, usuarioLogado);
        verify(amizadeRepository).delete(amizadePendente);
    }
}