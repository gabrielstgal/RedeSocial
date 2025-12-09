package br.com.cwi.RedeSocial.service.validator;

import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioValidatorTest {

    @InjectMocks
    private UsuarioValidator usuarioValidator;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        // Arrange
        String email = "existe@teste.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(Usuario.builder().build()));

        // Act
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            usuarioValidator.validarEmailUnico(email);
        });
        // Assert
        assertEquals("O e-mail informado já está em uso.", exception.getReason());
        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExisteAoBuscarPorId() {
        // Arrange
        Long id = 99L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            usuarioValidator.validarEBuscarUsuario(id);
        });
        // Assert
        assertEquals("Usuário não encontrado.", exception.getReason());
        verify(usuarioRepository).findById(id);
    }
}