package naderdeghaili.u5w3d2hw.controllers;

import naderdeghaili.u5w3d2hw.entities.Dipendente;
import naderdeghaili.u5w3d2hw.exceptions.ValidationException;
import naderdeghaili.u5w3d2hw.payloads.LoginDTO;
import naderdeghaili.u5w3d2hw.payloads.LoginResDTO;
import naderdeghaili.u5w3d2hw.payloads.NewDipendenteDTO;
import naderdeghaili.u5w3d2hw.services.DipendentiService;
import naderdeghaili.u5w3d2hw.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class DipendentiLoginController {

    private final LoginService loginService;
    private final DipendentiService dipendentiService;


    public DipendentiLoginController(LoginService loginService, DipendentiService dipendentiService) {
        this.loginService = loginService;

        this.dipendentiService = dipendentiService;
    }


    @PostMapping("/login")
    public LoginResDTO login(@RequestBody LoginDTO body) {

        return new LoginResDTO(this.loginService.getCredentialsGiveToken(body));
    }

    //POST DIPENDENTE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO payload, BindingResult validateResult) {
        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return this.dipendentiService.saveDipendente(payload);
        }
    }
}
