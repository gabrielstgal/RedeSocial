package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.PostagemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListarPostagensServiceTest {

    @InjectMocks
    private ListarPostagensService listarPostagensService;

    @Mock
    private PostagemRepository postagemRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private BuscarAmizadeService buscarAmizadeService;
    @Mock
    private MostrarComentarioPostagemService mostrarComentarioPostagemService;

    private final Usuario usuarioLogado = Usuario.builder().id(1L).build();
    private final Usuario amigo = Usuario.builder().id(2L).build();
    private final Pageable pageable = Pageable.unpaged();

    @Test
    void deveChamarRepositoryComListaCorretaDeAutores() {
        // Arrange
        Set<Usuario> amigosSet = new HashSet<>(Collections.singletonList(amigo));
        List<Usuario> expectedAutores = Arrays.asList(usuarioLogado, amigo);
        Postagem postagem = Postagem.builder().id(10L).build();
        Page<Postagem> postsPage = new PageImpl<>(Collections.singletonList(postagem), pageable, 1);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscarAmizadeService.buscar()).thenReturn(amigosSet);
        when(postagemRepository.findAllByUsuarioInOrPublicoTrueOrderByDataPostagemDesc(anyList(), eq(pageable)))
                .thenReturn(postsPage);

        // Act
        listarPostagensService.listar(pageable);

        // Assert
        verify(postagemRepository).findAllByUsuarioInOrPublicoTrueOrderByDataPostagemDesc(
                argThat(list -> list.contains(usuarioLogado) && list.contains(amigo) && list.size() == 2),
                eq(pageable)
        );
        verify(mostrarComentarioPostagemService).mostrarComentario(postagem);
    }
}