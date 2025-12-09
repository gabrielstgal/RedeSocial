package br.com.cwi.RedeSocial.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ComentarioResponse {
    private Long id;
    private ComentarioAutorResponse autor;
    private String conteudo;
    private LocalDateTime dataComentario;
}