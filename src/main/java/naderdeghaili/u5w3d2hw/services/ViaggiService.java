package naderdeghaili.u5w3d2hw.services;

import lombok.extern.slf4j.Slf4j;
import naderdeghaili.u5w3d2hw.entities.Viaggio;
import naderdeghaili.u5w3d2hw.exceptions.NotFoundException;
import naderdeghaili.u5w3d2hw.payloads.ModifyViaggioDTO;
import naderdeghaili.u5w3d2hw.payloads.NewViaggioDTO;
import naderdeghaili.u5w3d2hw.repositories.ViaggiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ViaggiService {

    private final ViaggiRepository viaggiRepository;

    public ViaggiService(ViaggiRepository viaggiRepository) {
        this.viaggiRepository = viaggiRepository;
    }

    //GET LISTA VIAGGI
    public Page<Viaggio> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return this.viaggiRepository.findAll(pageable);
    }

    //POST VIAGGI
    public Viaggio saveViaggio(NewViaggioDTO payload) {

        Viaggio newViaggio = new Viaggio(payload.destinazione(), payload.dataViaggio());

        return viaggiRepository.save(newViaggio);
    }

    //GET VIAGGIO
    public Viaggio findById(UUID viaggioId) {
        return viaggiRepository.findById(viaggioId).orElseThrow(() -> new NotFoundException(viaggioId));
    }

    //PUT VIAGGIO
    public Viaggio findByIdAndUpdate(UUID viaggioId, ModifyViaggioDTO payload) {
        Viaggio found = this.findById(viaggioId);

        found.setDestinazione(payload.destinazione());
        found.setDataViaggio(payload.dataViaggio());
        found.setStatoViaggio(payload.statoViaggio());

        Viaggio modifiedViaggio = this.viaggiRepository.save(found);

        log.info("il viaggio con id " + viaggioId + " è stato modificato");

        return modifiedViaggio;
    }

    //DELETE VIAGGIO
    public void findByIdAndDelete(UUID viaggioId) {
        Viaggio found = this.findById(viaggioId);
        this.viaggiRepository.delete(found);
        log.info("il viaggio è stato cancellato con successo");
    }


    //PATCH STATO VIAGGIO
    public Viaggio updateStato(UUID viaggioId, String statoViaggio) {
        Viaggio found = this.findById(viaggioId);

        found.setStatoViaggio(statoViaggio);

        return viaggiRepository.save(found);


    }
}
