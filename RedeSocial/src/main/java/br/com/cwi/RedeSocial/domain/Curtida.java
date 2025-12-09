package br.com.cwi.RedeSocial.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Curtida {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
