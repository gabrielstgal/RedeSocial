package br.com.cwi.RedeSocial.controller;

import br.com.cwi.RedeSocial.controller.request.ComentarioRequest;
import br.com.cwi.RedeSocial.controller.request.PostagemPermissaoRequest;
import br.com.cwi.RedeSocial.controller.request.PostagemRequest;
import br.com.cwi.RedeSocial.controller.response.PostagemResponse;
import br.com.cwi.RedeSocial.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private IncluirPostService incluirPostService;

    @Autowired
    private ListarPostagensService listarPostagensService;

    @Autowired
    private RealizarCurtidaService realizarCurtidaService;

    @Autowired
    private RemoverCurtidaService removerCurtidaService;

    @Autowired
    private IncluirComentarioService incluirComentarioService;

    @Autowired
    private AlterarPermissaoPostagemService alterarPermissaoPostagemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostagemResponse incluir(@Valid @RequestBody PostagemRequest request) {
        return incluirPostService.incluir(request);
    }

    @GetMapping
    public Page<PostagemResponse> listarPostagens(Pageable pageable) {
        return listarPostagensService.listar(pageable);
    }

    @PostMapping("/{idPostagem}/curtir")
    @ResponseStatus(HttpStatus.CREATED)
    public void curtir(@PathVariable Long idPostagem) {
        realizarCurtidaService.curtir(idPostagem);
    }

    @DeleteMapping("/{idPostagem}/curtir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCurtida(@PathVariable Long idPostagem) {
        removerCurtidaService.removerCurtida(idPostagem);
    }

    @PostMapping("/{idPostagem}/comentarios")
    @ResponseStatus(HttpStatus.CREATED)
    public void incluirComentario(@PathVariable Long idPostagem, @Valid @RequestBody ComentarioRequest request) {
        incluirComentarioService.incluir(idPostagem, request);
    }

    @PutMapping("/{idPostagem}")
    @ResponseStatus(HttpStatus.OK)
    public void alterarPermissao(@PathVariable Long idPostagem, @Valid @RequestBody PostagemPermissaoRequest request) {
        alterarPermissaoPostagemService.alterar(idPostagem, request);
    }

}