package br.com.cwi.RedeSocial.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioRequest {

    @NotBlank
    private String conteudo;
}