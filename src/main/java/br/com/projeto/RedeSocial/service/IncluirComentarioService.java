package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.ComentarioRequest;
import br.com.cwi.RedeSocial.domain.Comentario;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.mapper.ComentarioMapper;
import br.com.cwi.RedeSocial.repository.ComentarioRepository;
import br.com.cwi.RedeSocial.service.validator.PostagemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncluirComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostagemValidator postagemValidator;

    public void incluir(Long idPostagem, ComentarioRequest request) {

        Usuario autor = usuarioAutenticadoService.get();

        Postagem postagem = postagemValidator.validarEBuscarPostagem(idPostagem);

        Comentario comentario = ComentarioMapper.toEntity(request, autor, postagem);

        comentarioRepository.save(comentario);
    }
}