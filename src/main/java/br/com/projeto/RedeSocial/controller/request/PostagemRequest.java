package br.com.cwi.RedeSocial.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemRequest {

    @NotBlank
    private String conteudo;

    @NotNull
    private Boolean publico;
}
