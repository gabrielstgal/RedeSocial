package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarContatoServiceTest {

    @InjectMocks
    private BuscarContatoService buscarContatoService;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private AmizadeRepository amizadeRepository;

    private final Usuario usuarioLogado = Usuario.builder().id(1L).build();
    private final Usuario amigo = Usuario.builder().id(2L).build();
    private final Usuario disponivel = Usuario.builder().id(3L).build();

    @Test
    void deveExcluirUsuarioLogadoEAmigosDoResultadoFinal() {
        // Arrange
        String termo = "teste";
        List<Usuario> resultadosBusca = Arrays.asList(usuarioLogado, amigo, disponivel);
        Amizade vinculoAmigo = Amizade.builder().solicitante(usuarioLogado).solicitado(amigo).build();

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(usuarioRepository.findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(termo, termo))
                .thenReturn(resultadosBusca);
        when(amizadeRepository.findAllBySolicitanteIdOrSolicitadoId(usuarioLogado.getId(), usuarioLogado.getId()))
                .thenReturn(Collections.singletonList(vinculoAmigo));

        // Act
        List<UsuarioResponse> lista = buscarContatoService.buscar(termo);

        // Assert
        assertEquals(1, lista.size());
    }
}