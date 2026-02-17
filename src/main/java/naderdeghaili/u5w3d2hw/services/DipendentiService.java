package naderdeghaili.u5w3d2hw.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import naderdeghaili.u5w3d2hw.entities.Dipendente;
import naderdeghaili.u5w3d2hw.exceptions.NotFoundException;
import naderdeghaili.u5w3d2hw.payloads.NewDipendenteDTO;
import naderdeghaili.u5w3d2hw.repositories.DipendentiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class DipendentiService {

    private final DipendentiRepository dipendentiRepository;
    private final Cloudinary clUploader;
    private final PasswordEncoder bcrypt;


    public DipendentiService(DipendentiRepository dipendentiRepository, Cloudinary clUploader, PasswordEncoder bcrypt) {
        this.dipendentiRepository = dipendentiRepository;
        this.clUploader = clUploader;
        this.bcrypt = bcrypt;
    }

    //GET LISTA DIPENDENTI
    public Page<Dipendente> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return this.dipendentiRepository.findAll(pageable);
    }

    //POST DIPENDENTI
    public Dipendente saveDipendente(NewDipendenteDTO payload) {
        if (dipendentiRepository.existsByEmail(payload.email())) {
            throw new IllegalArgumentException("l'email è già in uso");
        }
        if (dipendentiRepository.existsByUsername(payload.username())) {
            throw new IllegalArgumentException("l'username è già in uso");
        }

        Dipendente newDipendente = new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email(), bcrypt.encode(payload.password()));

        return dipendentiRepository.save(newDipendente);
    }

    //GET DIPENDENTE
    public Dipendente findById(UUID dipendenteId) {
        return dipendentiRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }

    //PUT DIPENDENTE
    public Dipendente findByIdAndUpdate(UUID dipendenteId, NewDipendenteDTO payload) {
        Dipendente found = this.findById(dipendenteId);

        if (!found.getEmail().equals(payload.email())
                && dipendentiRepository.existsByEmail(payload.email())) {
            throw new IllegalArgumentException("l'email è già in uso");
        }

        if (!found.getUsername().equals(payload.username())
                && dipendentiRepository.existsByUsername(payload.username())) {
            throw new IllegalArgumentException("l'username è già in uso");
        }

        found.setUsername(payload.username());
        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setEmail(payload.email());

        Dipendente modifiedDipendente = this.dipendentiRepository.save(found);

        log.info("il dipendente con id " + dipendenteId + " è stato modificato");

        return modifiedDipendente;
    }

    //DELETE DIPENDENTE
    public void findByIdAndDelete(UUID dipendenteId) {
        Dipendente found = this.findById(dipendenteId);
        this.dipendentiRepository.delete(found);
        log.info("il dipendente è stato licenziato con successo");
    }

    //PATCH PROFILE PIC
    public Dipendente uploadAvatar(UUID dipendenteId, MultipartFile file) {
        Dipendente found = this.findById(dipendenteId);
        try {
            Map result = clUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("secure_url");
            found.setAvatar(imageUrl);
            return dipendentiRepository.save(found);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    //FIND BY EMAIL
    public Dipendente findByEmail(String email) {
        return this.dipendentiRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));


    }

}
