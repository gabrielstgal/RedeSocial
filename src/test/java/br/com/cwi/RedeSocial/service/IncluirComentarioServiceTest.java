package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.ComentarioRequest;
import br.com.cwi.RedeSocial.domain.Comentario;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.ComentarioRepository;
import br.com.cwi.RedeSocial.service.validator.PostagemValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncluirComentarioServiceTest {

    @InjectMocks
    private IncluirComentarioService incluirComentarioService;

    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private PostagemValidator postagemValidator;

    private final Usuario autor = Usuario.builder().id(1L).build();
    private final Postagem postagem = Postagem.builder().id(5L).build();
    private final ComentarioRequest request = new ComentarioRequest();

    @Test
    void deveSalvarNovoComentarioCorretamente() {
        // Arrange
        Long idPostagem = 5L;
        request.setConteudo("Comentario teste.");

        when(usuarioAutenticadoService.get()).thenReturn(autor);
        when(postagemValidator.validarEBuscarPostagem(idPostagem)).thenReturn(postagem);

        // Act
        incluirComentarioService.incluir(idPostagem, request);

        // Assert
        verify(postagemValidator).validarEBuscarPostagem(idPostagem);
        verify(comentarioRepository).save(any(Comentario.class));
    }
}