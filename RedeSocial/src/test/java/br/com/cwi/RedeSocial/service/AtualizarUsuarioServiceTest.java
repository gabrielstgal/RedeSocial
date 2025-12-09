package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.AtualizarUsuarioRequest;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtualizarUsuarioServiceTest {

    @InjectMocks
    private AtualizarUsuarioService atualizarUsuarioService;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    @Test
    void deveAtualizarNomeApelidoEImagemDoUsuarioLogado() {
        // Arrange
        Usuario usuarioLogado = Usuario.builder().id(1L).nomeCompleto("nome velho").build();
        AtualizarUsuarioRequest request = new AtualizarUsuarioRequest();
        request.setNomeCompleto("nome teste");
        request.setApelido("apelido teste");
        request.setImagemPerfil("url teste");

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioLogado);

        // Act
        atualizarUsuarioService.atualizar(request);

        // Assert
        verify(usuarioRepository).save(usuarioCaptor.capture());

        Usuario savedUsuario = usuarioCaptor.getValue();
        assertEquals(1L, savedUsuario.getId());
        assertEquals("nome teste", savedUsuario.getNomeCompleto());
        assertEquals("apelido teste", savedUsuario.getApelido());
        assertEquals("url teste", savedUsuario.getImagemPerfil());
    }
}