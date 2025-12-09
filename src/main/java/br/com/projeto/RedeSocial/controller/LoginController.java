package br.com.cwi.RedeSocial.controller;

import br.com.cwi.RedeSocial.controller.request.LoginRequest;
import br.com.cwi.RedeSocial.controller.response.LoginResponse;
import br.com.cwi.RedeSocial.domain.Usuario;
import br.com.cwi.RedeSocial.service.BuscarUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtEncoder jwtEncoder;


    @PostMapping
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<Usuario> optUser = buscarUsuarioService.buscarPorEmail(loginRequest.getEmail());

        if (optUser.isEmpty() || !isLoginCorreto(loginRequest.getSenha(), optUser.get().getSenha())) {
            throw new BadCredentialsException("Usuário ou senha incorretos!");
        }

        Usuario usuario = optUser.get();

        long expiresIn = 600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("rede-social-spfc")
                .subject(usuario.getNomeCompleto())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("email", usuario.getEmail())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponse(token, expiresIn));
    }

    private boolean isLoginCorreto(String password, String savedPassword) {
        return passwordEncoder.matches(password, savedPassword);
    }
}