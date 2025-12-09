package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.request.PostagemPermissaoRequest;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.repository.PostagemRepository;
import br.com.cwi.RedeSocial.service.validator.PostagemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarPermissaoPostagemService {

    @Autowired
    private PostagemValidator postagemValidator;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void alterar(Long idPostagem, PostagemPermissaoRequest request) {

        Postagem postagem = postagemValidator.validarEBuscarPostagem(idPostagem);

        Long idUsuarioLogado = usuarioAutenticadoService.get().getId();
        postagemValidator.validarAutorizacao(postagem, idUsuarioLogado);

        postagem.setPublico(request.getPublico());

        postagemRepository.save(postagem);
    }
}