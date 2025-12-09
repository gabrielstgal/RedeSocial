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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SolicitarAmizadeServiceTest {

    @InjectMocks
    private SolicitarAmizadeService solicitarAmizadeService;

    @Mock
    private AmizadeRepository amizadeRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private UsuarioValidator usuarioValidator;
    @Mock
    private AmizadeValidator amizadeValidator;

    private final Usuario solicitante = Usuario.builder().id(1L).build();
    private final Usuario solicitado = Usuario.builder().id(2L).build();

    @Test
    void deveSalvarNovaSolicitacaoComStatusPendente() {
        // Arrange
        Long idSolicitado = 2L;

        when(usuarioAutenticadoService.get()).thenReturn(solicitante);
        when(usuarioValidator.validarEBuscarUsuario(idSolicitado)).thenReturn(solicitado);
        doNothing().when(amizadeValidator).validarNovaSolicitacao(solicitante, solicitado);

        // Act
        solicitarAmizadeService.solicitar(idSolicitado);

        // Assert
        verify(usuarioValidator).validarEBuscarUsuario(idSolicitado);
        verify(amizadeValidator).validarNovaSolicitacao(solicitante, solicitado);
        verify(amizadeRepository).save(any(Amizade.class));
    }
}