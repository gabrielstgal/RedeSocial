package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarUsuarioServiceTest {

    @InjectMocks
    private BuscarUsuarioService buscarUsuarioService;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private UsuarioRepository usuarioRepository;

    private final Usuario usuario = Usuario.builder().email("perfil@teste.com").build();

    @Test
    void deveChamarRepositoryAoBuscarPorEmailParaLogin() {
        // Arrange
        String email = "saopaulo@cwi.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> resultado = buscarUsuarioService.buscarPorEmail(email);

        // Assert
        assertTrue(resultado.isPresent());
        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    void deveBuscarUsuarioLogadoParaExibicaoDePerfil() {
        // Arrange
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        // Act
        buscarUsuarioService.buscar();

        // Assert
        verify(usuarioAutenticadoService).get();
    }
}