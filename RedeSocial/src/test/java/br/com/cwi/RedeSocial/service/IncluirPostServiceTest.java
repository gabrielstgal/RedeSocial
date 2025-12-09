package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.PostagemRequest;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.PostagemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncluirPostServiceTest {

    @InjectMocks
    private IncluirPostService incluirPostService;

    @Mock
    private PostagemRepository postagemRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    private final Usuario autor = Usuario.builder().id(1L).build();
    private final PostagemRequest request = new PostagemRequest();

    @Test
    void deveChamarSaveNoRepositorioAoIncluirPostagem() {
        // Arrange
        request.setConteudo("Post Teste");
        request.setPublico(true);
        when(usuarioAutenticadoService.get()).thenReturn(autor);

        // Act
        incluirPostService.incluir(request);

        // Assert
        verify(postagemRepository).save(any(Postagem.class));
    }
}