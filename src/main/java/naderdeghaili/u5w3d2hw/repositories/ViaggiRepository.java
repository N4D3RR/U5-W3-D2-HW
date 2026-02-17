package naderdeghaili.u5w3d2hw.repositories;

import naderdeghaili.u5w3d2hw.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {
}
