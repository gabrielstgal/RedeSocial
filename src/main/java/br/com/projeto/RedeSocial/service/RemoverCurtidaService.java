package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.domain.Curtida;
import br.com.cwi.RedeSocial.domain.Postagem;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.repository.CurtidaRepository;
import br.com.cwi.RedeSocial.service.validator.CurtidaValidator;
import br.com.cwi.RedeSocial.service.validator.PostagemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverCurtidaService {

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private CurtidaValidator curtidaValidator;

    @Autowired
    private PostagemValidator postagemValidator;

    public void removerCurtida(Long idPostagem) {
        Usuario usuario = usuarioAutenticadoService.get();

        Postagem postagem = postagemValidator.validarEBuscarPostagem(idPostagem);

        Curtida curtida = curtidaValidator.buscarCurtidaExistente(usuario, postagem);

        curtidaRepository.delete(curtida);
    }
}

