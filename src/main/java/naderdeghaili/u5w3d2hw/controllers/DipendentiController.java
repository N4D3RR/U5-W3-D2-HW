package naderdeghaili.u5w3d2hw.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import naderdeghaili.u5w3d2hw.entities.Dipendente;
import naderdeghaili.u5w3d2hw.exceptions.ValidationException;
import naderdeghaili.u5w3d2hw.payloads.NewDipendenteDTO;
import naderdeghaili.u5w3d2hw.services.DipendentiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {

    private final DipendentiService dipendentiService;

    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    //GET DIPENDENTI
    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                    @RequestParam(defaultValue = "15") @Min(0) @Max(15) int size) {
        return this.dipendentiService.findAll(page, size);
    }


    //GET DIPENDENTE
    @GetMapping("/{dipendenteId}")
    public Dipendente getDipendenteById(@PathVariable UUID dipendenteId) {
        return this.dipendentiService.findById(dipendenteId);
    }

    //PUT DIPENDNETE
    @PutMapping("/{dipendenteId}")
    public Dipendente updateDipendente(@PathVariable UUID dipendenteId, @RequestBody @Validated NewDipendenteDTO payload, BindingResult validateResult) {
        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return this.dipendentiService.findByIdAndUpdate(dipendenteId, payload);
        }
    }

    //DELETE DIPENDENTE
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable UUID dipendenteId) {
        this.dipendentiService.findByIdAndDelete(dipendenteId);
    }

    //CLOUDINARY
    @PatchMapping("/{dipendenteId}/avatar")
    public Dipendente uploadImg(@PathVariable UUID dipendenteId, @RequestParam("profile_picture") MultipartFile file) {

        Dipendente modifiedAvatarDipendente = this.dipendentiService.uploadAvatar(dipendenteId, file);
        return modifiedAvatarDipendente;
    }


}
