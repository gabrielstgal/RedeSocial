package br.com.cwi.RedeSocial.controller;

import br.com.cwi.RedeSocial.controller.request.AtualizarUsuarioRequest;
import br.com.cwi.RedeSocial.controller.request.UsuarioRequest;
import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private SolicitarAmizadeService solicitarAmizadeService;

    @Autowired
    private AceitarSolicitacaoAmizadeService aceitarSolicitacaoAmizadeService;

    @Autowired
    private ListarSolicitacoesRecebidasService listarSolicitacoesRecebidasService;

    @Autowired
    private BuscarContatoService buscarContatoService;

    @Autowired
    private AtualizarUsuarioService atualizarUsuarioService;

    @Autowired
    private RejeitarAmizadeService rejeitarAmizadeService;

    @Autowired
    private DesfazerAmizadeService desfazerAmizadeService;

    @Autowired
    private ListarAmigosService listarAmigosService;

    @PostMapping
    public UsuarioResponse cadastrar(@Valid @RequestBody UsuarioRequest request) {
        return cadastrarUsuarioService.cadastrar(request);
    }

    @GetMapping("/me")
    public UsuarioResponse buscar() {
        return buscarUsuarioService.buscar();
    }

    @PutMapping("/me")
    public UsuarioResponse editar(@Valid @RequestBody AtualizarUsuarioRequest request) {
        return atualizarUsuarioService.atualizar(request);
    }

    @GetMapping("/amigos")
    public List<UsuarioResponse> listarAmigos() {
        return listarAmigosService.listar();
    }

    @GetMapping
    public List<UsuarioResponse> buscarContatos(@RequestParam String text) {
        return buscarContatoService.buscar(text);
    }

    @PostMapping("/{idSolicitado}")
    @ResponseStatus(HttpStatus.CREATED)
    public void solicitar(@PathVariable Long idSolicitado) {
        solicitarAmizadeService.solicitar(idSolicitado);
    }

    @GetMapping("/solicitacoes")
    public List<UsuarioResponse> listarSolicitacoes() {
        return listarSolicitacoesRecebidasService.listar();
    }

    @PostMapping("/aceitar/{idSolicitante}")
    @ResponseStatus(HttpStatus.OK)
    public void aceitar(@PathVariable Long idSolicitante) {
        aceitarSolicitacaoAmizadeService.aceitar(idSolicitante);
    }

    @DeleteMapping("/rejeitar/{idSolicitante}")
    @ResponseStatus(HttpStatus.OK)
    public void rejeitar(@PathVariable Long idSolicitante) {
        rejeitarAmizadeService.rejeitar(idSolicitante);
    }

    @DeleteMapping("/amigos/{idAmigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desfazerAmizade(@PathVariable Long idAmigo) {
        desfazerAmizadeService.desfazer(idAmigo);
    }
}
