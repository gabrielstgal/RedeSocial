package br.com.cwi.RedeSocial.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemPermissaoRequest {

    @NotNull
    private Boolean publico;
}