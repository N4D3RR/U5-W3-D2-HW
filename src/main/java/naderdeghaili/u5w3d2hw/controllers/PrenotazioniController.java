package naderdeghaili.u5w3d2hw.controllers;

import naderdeghaili.u5w3d2hw.entities.Dipendente;
import naderdeghaili.u5w3d2hw.entities.Prenotazione;
import naderdeghaili.u5w3d2hw.exceptions.ValidationException;
import naderdeghaili.u5w3d2hw.payloads.NewPrenotazioneDTO;
import naderdeghaili.u5w3d2hw.services.PrenotazioniService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione save(@AuthenticationPrincipal Dipendente currentAuthDipendente,
                             @RequestBody @Validated NewPrenotazioneDTO payload, BindingResult validateResult) {

        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return prenotazioniService.savePrenotazione(payload, currentAuthDipendente);
        }
    }
}
