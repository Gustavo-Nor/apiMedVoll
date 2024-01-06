package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByActiveTrueOrderByNome(Pageable paginacao);

    @Query("""
            SELECT p.active 
            FROM Paciente p
            WHERE p.id = :id
            """)
    boolean findActiveById(Long idPaciente);
}
