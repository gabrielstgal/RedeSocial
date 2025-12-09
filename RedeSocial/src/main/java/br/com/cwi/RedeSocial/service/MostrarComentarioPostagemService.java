package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.response.PostagemResponse;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.mapper.ComentarioMapper;
import br.com.cwi.RedeSocial.mapper.PostagemMapper;
import br.com.cwi.RedeSocial.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MostrarComentarioPostagemService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public PostagemResponse mostrarComentario(Postagem postagem) {

        PostagemResponse response = PostagemMapper.toResponse(postagem);
        Long idPostagem = postagem.getId();

        response.setComentarios(
                ComentarioMapper.toResponseList(
                        comentarioRepository.findAllByPostagemIdOrderByDataComentarioAsc(idPostagem)
                )
        );

        return response;
    }
}