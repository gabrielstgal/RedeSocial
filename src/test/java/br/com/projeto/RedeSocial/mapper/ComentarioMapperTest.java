package br.com.cwi.RedeSocial.mapper;

import br.com.cwi.RedeSocial.controller.response.ComentarioResponse;
import br.com.cwi.RedeSocial.domain.Comentario;
import br.com.cwi.RedeSocial.domain.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ComentarioMapperTest {

    @Test
    void deveMapearComentarioEntityParaResponseComAutorMinimalista() {
        // Arrange
        Usuario autor = Usuario.builder().id(5L).apelido("treze").build();
        LocalDateTime dataComentario = LocalDateTime.now();

        Comentario entity = Comentario.builder()
                .Id(10L)
                .usuario(autor)
                .conteudo("Tricampeão paraibano!")
                .dataComentario(dataComentario)
                .build();

        // Act
        ComentarioResponse response = ComentarioMapper.toResponse(entity);

        // Assert
        assertEquals(10L, response.getId());
        assertEquals("Tricampeão paraibano!", response.getConteudo());
        assertNotNull(response.getAutor());
        assertEquals(5L, response.getAutor().getId());
        assertEquals("treze", response.getAutor().getApelido());
    }
}