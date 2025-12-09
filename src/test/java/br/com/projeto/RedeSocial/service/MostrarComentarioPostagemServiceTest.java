package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Comentario;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.repository.ComentarioRepository;
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
public class MostrarComentarioPostagemServiceTest {

    @InjectMocks
    private MostrarComentarioPostagemService mostrarComentarioPostagemService;

    @Mock
    private ComentarioRepository comentarioRepository;

    private final Postagem postagem = Postagem.builder().id(1L).build();

    @Test
    void deveBuscarComentariosPeloIdDaPostagem() {
        // Arrange
        List<Comentario> comentarios = Collections.singletonList(Comentario.builder().build());

        when(comentarioRepository.findAllByPostagemIdOrderByDataComentarioAsc(postagem.getId())).thenReturn(comentarios);

        // Act
        mostrarComentarioPostagemService.mostrarComentario(postagem);

        // Assert
        verify(comentarioRepository).findAllByPostagemIdOrderByDataComentarioAsc(postagem.getId());
    }
}