package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarAmigosServiceTest {

    @InjectMocks
    private ListarAmigosService listarAmigosService;

    @Mock
    private BuscarAmizadeService buscarAmizadeService;

    @Test
    void deveListarAmigosCorretamente() {
        // Arrange
        Usuario amigo1 = Usuario.builder().id(2L).apelido("A1").build();
        Usuario amigo2 = Usuario.builder().id(3L).build();
        Set<Usuario> amigosSet = new HashSet<>(Arrays.asList(amigo1, amigo2));

        when(buscarAmizadeService.buscar()).thenReturn(amigosSet);

        // Act
        List<UsuarioResponse> resultado = listarAmigosService.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(buscarAmizadeService).buscar();
    }
}