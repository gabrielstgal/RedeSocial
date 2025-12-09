package br.com.cwi.RedeSocial.repository;

import br.com.cwi.RedeSocial.domain.Amizade;
import br.com.cwi.RedeSocial.domain.StatusAmizade;
import br.com.cwi.RedeSocial.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {


    List<Amizade> findAllBySolicitadoAndStatus(Usuario solicitado, StatusAmizade status);

    @Query("""
                SELECT a FROM Amizade a 
                WHERE (a.solicitante = :usuarioA AND a.solicitado = :usuarioB) 
                   OR (a.solicitante = :usuarioB AND a.solicitado = :usuarioA)
            """)
    Optional<Amizade> findViculoEntre(Usuario usuarioA, Usuario usuarioB);

    Optional<Amizade> findBySolicitanteAndSolicitadoAndStatus(Usuario solicitante, Usuario solicitado, StatusAmizade status);

    List<Amizade> findAllBySolicitanteIdOrSolicitadoId(Long solicitanteId, Long solicitadoId);
}