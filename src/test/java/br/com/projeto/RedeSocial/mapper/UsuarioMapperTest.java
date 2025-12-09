package br.com.cwi.RedeSocial.mapper;

import br.com.cwi.RedeSocial.controller.request.UsuarioRequest;
import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioMapperTest {

    @Test
    void deveMapearUsuarioRequestParaEntityCorretamente() {
        // Arrange
        UsuarioRequest request = new UsuarioRequest();
        request.setNomeCompleto("Palmeiras");
        request.setEmail("palmeiras@teste.com");
        request.setApelido("porco");
        request.setDataNascimento(LocalDate.of(1933, 10, 28));
        request.setEstadio("Allianz");
        request.setImagemPerfil("url/palmeiras");

        // Act
        Usuario entity = UsuarioMapper.toEntity(request);

        // Assert
        assertEquals("Palmeiras", entity.getNomeCompleto());
        assertEquals("palmeiras@teste.com", entity.getEmail());
        assertEquals(LocalDate.of(1933, 10, 28), entity.getDataNascimento());
        assertNull(entity.getId());
    }

    @Test
    void deveMapearUsuarioEntityParaResponseCorretamente() {
        // Arrange
        Usuario entity = Usuario.builder()
                .id(1L)
                .nomeCompleto("Sao paulo")
                .apelido("tricolor")
                .email("saopaulo@teste.com")
                .dataNascimento(LocalDate.of(1940, 10, 23))
                .estadio("Morumbi")
                .build();

        // Act
        UsuarioResponse response = UsuarioMapper.toResponse(entity);

        // Assert
        assertEquals("Sao paulo", response.getNomeCompleto());
        assertEquals("tricolor", response.getApelido());
        assertEquals("saopaulo@teste.com", response.getEmail());
        assertNotNull(response.getDataNascimento());
    }
}