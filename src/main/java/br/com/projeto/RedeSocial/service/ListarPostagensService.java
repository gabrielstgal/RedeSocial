package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.response.PostagemResponse;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ListarPostagensService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarAmizadeService buscarAmizadeService;

    @Autowired
    private MostrarComentarioPostagemService mostrarComentarioPostagemService;

    public Page<PostagemResponse> listar(Pageable pageable) {

        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Set<Usuario> amigos = buscarAmizadeService.buscar();

        List<Usuario> autoresDeInteresse = new ArrayList<>();
        autoresDeInteresse.add(usuarioLogado);
        autoresDeInteresse.addAll(amigos);

        Page<Postagem> posts = postagemRepository.findAllByUsuarioInOrPublicoTrueOrderByDataPostagemDesc(
                autoresDeInteresse,
                pageable
        );

        return posts.map(mostrarComentarioPostagemService::mostrarComentario);
    }
}

