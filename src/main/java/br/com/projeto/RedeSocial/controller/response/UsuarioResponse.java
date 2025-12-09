package br.com.cwi.RedeSocial.controller.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponse {

    private String nomeCompleto;
    private String apelido;
    private String email;
    private LocalDate dataNascimento;
    private String imagemPerfil;
    private String estadio;
}