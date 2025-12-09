package br.com.cwi.RedeSocial.service.validator;

import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.AmizadeRepository;
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
public class AmizadeValidatorTest {

    @InjectMocks
    private AmizadeValidator amizadeValidator;

    @Mock
    private AmizadeRepository amizadeRepository;

    private final Usuario usuarioA = Usuario.builder().id(1L).build();
    private final Usuario usuarioB = Usuario.builder().id(2L).build();

    @Test
    void deveLancarExcecaoAoTentarSolicitarParaSiMesmo() {
        // Act
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            amizadeValidator.validarNovaSolicitacao(usuarioA, usuarioA);
        });
        //&Assert
        assertEquals("Não é possível enviar solicitação de amizade para si mesmo.", exception.getReason());
    }

    @Test
    void deveLancarExcecaoQuandoVinculoJaExiste() {
        // Arrange
        when(amizadeRepository.findViculoEntre(usuarioA, usuarioB)).thenReturn(Optional.of(Amizade.builder().build()));

        // Act
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            amizadeValidator.validarNovaSolicitacao(usuarioA, usuarioB);
        });
        // Assert
        assertEquals("Já existe um vínculo (amizade ou solicitação pendente) com este usuário.", exception.getReason());
        verify(amizadeRepository).findViculoEntre(usuarioA, usuarioB);
    }
}