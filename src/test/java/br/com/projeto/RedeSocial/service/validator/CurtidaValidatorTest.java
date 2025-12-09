package br.com.cwi.RedeSocial.service.validator;

import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.CurtidaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurtidaValidatorTest {

    @InjectMocks
    private CurtidaValidator curtidaValidator;

    @Mock
    private CurtidaRepository curtidaRepository;

    private final Usuario usuario = Usuario.builder().id(1L).build();
    private final Postagem postagem = Postagem.builder().id(10L).build();


    @Test
    void deveLancarExcecaoQuandoCurtidaNaoExisteAoBuscarParaRemocao() {
        // Arrange
        when(curtidaRepository.findByUsuarioAndPostagem(usuario, postagem)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            curtidaValidator.buscarCurtidaExistente(usuario, postagem);
        });
        // Assert
        assertEquals("Você não curtiu esta postagem para removê-la.", exception.getReason());
    }
}