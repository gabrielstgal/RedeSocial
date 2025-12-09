package br.com.cwi.RedeSocial.controller.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostagemResponse {

    private Long id;
    private UsuarioResponse usuario;
    private String conteudo;
    private LocalDateTime dataPostagem;
    private boolean publico;
    private List<ComentarioResponse> comentarios;
}