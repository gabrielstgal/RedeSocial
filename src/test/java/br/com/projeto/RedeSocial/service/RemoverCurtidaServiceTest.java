package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Curtida;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.CurtidaRepository;
import br.com.cwi.RedeSocial.service.validator.CurtidaValidator;
import br.com.cwi.RedeSocial.service.validator.PostagemValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoverCurtidaServiceTest {

    @InjectMocks
    private RemoverCurtidaService removerCurtidaService;

    @Mock
    private CurtidaRepository curtidaRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private CurtidaValidator curtidaValidator;
    @Mock
    private PostagemValidator postagemValidator;

    private final Usuario usuario = Usuario.builder().id(1L).build();
    private final Postagem postagem = Postagem.builder().id(10L).build();
    private final Curtida curtidaExistente = Curtida.builder().id(5L).build();

    @Test
    void deveRemoverCurtidaComSucesso() {
        // Arrange
        Long idPostagem = 10L;

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(postagemValidator.validarEBuscarPostagem(idPostagem)).thenReturn(postagem);
        when(curtidaValidator.buscarCurtidaExistente(usuario, postagem)).thenReturn(curtidaExistente);

        // Act
        assertDoesNotThrow(() -> removerCurtidaService.removerCurtida(idPostagem));

        // Assert
        verify(postagemValidator).validarEBuscarPostagem(idPostagem);
        verify(curtidaValidator).buscarCurtidaExistente(usuario, postagem);
        verify(curtidaRepository).delete(curtidaExistente);
    }
}