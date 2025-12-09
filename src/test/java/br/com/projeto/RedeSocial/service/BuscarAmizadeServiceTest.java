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

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarAmizadeServiceTest {

    @InjectMocks
    private BuscarAmizadeService buscarAmizadeService;

    @Mock
    private AmizadeRepository amizadeRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    private final Usuario usuarioLogado = Usuario.builder().id(1L).build();
    private final Usuario amigoAceito = Usuario.builder().id(2L).build();
    private final Usuario amigoPendente = Usuario.builder().id(3L).build();


    @Test
    void deveRetornarApenasAmigosComStatusAceita() {
        // Arrange
        Long loggedId = usuarioLogado.getId();

        Amizade aceita1 = Amizade.builder().solicitante(usuarioLogado).solicitado(amigoAceito).status(StatusAmizade.ACEITA).build();
        Amizade pendente1 = Amizade.builder().solicitante(usuarioLogado).solicitado(amigoPendente).status(StatusAmizade.PENDENTE).build();

        List<Amizade> vinculos = Arrays.asList(aceita1, pendente1);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(amizadeRepository.findAllBySolicitanteIdOrSolicitadoId(loggedId, loggedId)).thenReturn(vinculos);

        // Act
        Set<Usuario> resultado = buscarAmizadeService.buscar();

        // Assert
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(amigoAceito));
        assertFalse(resultado.contains(amigoPendente));
        verify(amizadeRepository).findAllBySolicitanteIdOrSolicitadoId(loggedId, loggedId);
    }
}