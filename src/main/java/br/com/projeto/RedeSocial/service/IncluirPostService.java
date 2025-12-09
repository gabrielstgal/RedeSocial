package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.PostagemRequest;
import br.com.cwi.RedeSocial.controller.response.PostagemResponse;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.mapper.PostagemMapper;
import br.com.cwi.RedeSocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncluirPostService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public PostagemResponse incluir(PostagemRequest request) {

        Usuario autor = usuarioAutenticadoService.get();

        Postagem postagem = PostagemMapper.toEntity(request, autor);

        postagemRepository.save(postagem);

        return PostagemMapper.toResponse(postagem);
    }
}

