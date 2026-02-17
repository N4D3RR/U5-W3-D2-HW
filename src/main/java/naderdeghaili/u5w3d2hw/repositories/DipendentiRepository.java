package naderdeghaili.u5w3d2hw.repositories;

import naderdeghaili.u5w3d2hw.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DipendentiRepository extends JpaRepository<Dipendente, UUID> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Dipendente> findByEmail(String email);
}
