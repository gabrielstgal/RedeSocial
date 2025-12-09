package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.PostagemPermissaoRequest;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.PostagemRepository;
import br.com.cwi.RedeSocial.service.validator.PostagemValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlterarPermissaoPostagemServiceTest {

    @InjectMocks
    private AlterarPermissaoPostagemService alterarPermissaoPostagemService;

    @Mock
    private PostagemValidator postagemValidator;
    @Mock
    private PostagemRepository postagemRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    private final Usuario autor = Usuario.builder().id(1L).build();
    private final Postagem postagem = Postagem.builder().id(5L).usuario(autor).publico(true).build();

    @Test
    void deveAlterarPermissaoParaPrivadoComSucesso() {
        // Arrange
        Long idPostagem = 5L;
        PostagemPermissaoRequest request = new PostagemPermissaoRequest();
        request.setPublico(false);

        when(usuarioAutenticadoService.get()).thenReturn(autor);
        when(postagemValidator.validarEBuscarPostagem(idPostagem)).thenReturn(postagem);
        doNothing().when(postagemValidator).validarAutorizacao(postagem, autor.getId());

        // Act
        alterarPermissaoPostagemService.alterar(idPostagem, request);

        // Assert
        verify(postagemValidator).validarEBuscarPostagem(idPostagem);
        verify(postagemValidator).validarAutorizacao(postagem, autor.getId());
        verify(postagemRepository).save(postagem);

        assertFalse(postagem.isPublico());
    }
}