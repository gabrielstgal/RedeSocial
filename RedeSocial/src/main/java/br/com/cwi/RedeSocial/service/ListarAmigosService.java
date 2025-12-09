package br.com.cwi.RedeSocial.service;

import br.com.cwi.RedeSocial.controller.response.UsuarioResponse;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ListarAmigosService {

    @Autowired
    private BuscarAmizadeService buscarAmizadeService;

    public List<UsuarioResponse> listar() {

        Set<Usuario> todosAmigos = buscarAmizadeService.buscar();

        List<Usuario> amigosList = todosAmigos
                .stream()
                .collect(Collectors.toList());

        return UsuarioMapper.toResponseList(amigosList);
    }
}