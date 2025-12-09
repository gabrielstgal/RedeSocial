package br.com.cwi.RedeSocial.service.validator;

import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.PostagemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostagemValidatorTest {

    @InjectMocks
    private PostagemValidator postagemValidator;

    @Mock
    private PostagemRepository postagemRepository;

    private final Usuario autor = Usuario.builder().id(1L).build();
    private final Usuario outroUsuario = Usuario.builder().id(2L).build();
    private final Postagem postagem = Postagem.builder().usuario(autor).build();

    @Test
    void deveLancarExcecaoQuandoPostagemNaoExisteAoBuscar() {
        // Arrange
        Long id = 99L;
        when(postagemRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Act
        assertThrows(ResponseStatusException.class, () -> {
            postagemValidator.validarEBuscarPostagem(id);
        });
        // Assert
        verify(postagemRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEAutorAoValidarAutorizacao() {
        // Act
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            postagemValidator.validarAutorizacao(postagem, outroUsuario.getId());
        });
        //Assert
        assertEquals("Não tem permissão para alterar as permissões desta postagem.", exception.getReason());
    }
}