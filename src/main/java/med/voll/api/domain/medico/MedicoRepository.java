package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByActiveTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.active = true
            AND m.especialidade = :especialidade
            AND m.id not in(
                SELECT c.medico.id from Consulta c
                WHERE c.data = :data
                AND c.motivoCancelamento is null
            )
            ORDER BY random()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.active 
            FROM Medico m
            WHERE m.id = :idMedico
            """)
    Boolean findActiveById(Long idMedico);
}
