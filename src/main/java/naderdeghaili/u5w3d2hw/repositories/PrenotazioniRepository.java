package naderdeghaili.u5w3d2hw.repositories;

import naderdeghaili.u5w3d2hw.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {

    boolean existsByViaggioDataViaggioAndDipendenteId(LocalDate dataViaggio, UUID dipendenteId);

    boolean existsByViaggioIdAndDipendenteId(UUID viaggioId, UUID dipendenteId);


}
