package br.com.cwi.RedeSocial.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Comentario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String conteudo;

    @Column(name = "data_comentario")
    private LocalDateTime dataComentario;
}
