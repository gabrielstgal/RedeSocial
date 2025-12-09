package br.com.cwi.RedeSocial.controller.response;

public record LoginResponse(String accessToken, Long expiresIn) {
}
