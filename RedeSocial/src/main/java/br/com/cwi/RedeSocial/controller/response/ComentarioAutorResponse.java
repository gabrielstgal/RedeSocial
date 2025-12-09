package br.com.cwi.RedeSocial.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComentarioAutorResponse {
    private Long id;
    private String apelido;
}