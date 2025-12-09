package br.com.cwi.RedeSocial.mapper;

import br.com.cwi.RedeSocial.controller.request.PostagemRequest;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostagemMapperTest {

    private final Usuario usuario = Usuario.builder().id(1L).build();

    @Test
    void deveMapearPostagemRequestParaEntityComStatusPublicoTrue() {
        // Arrange
        PostagemRequest request = new PostagemRequest();
        request.setConteudo("Postagem de teste público.");
        request.setPublico(true);

        // Act
        Postagem entity = PostagemMapper.toEntity(request, usuario);

        // Assert
        assertEquals("Postagem de teste público.", entity.getConteudo());
        assertTrue(entity.isPublico());
        assertEquals(usuario.getId(), entity.getUsuario().getId());
        assertNotNull(entity.getDataPostagem());
    }
}