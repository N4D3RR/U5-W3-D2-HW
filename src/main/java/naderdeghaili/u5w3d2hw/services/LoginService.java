package naderdeghaili.u5w3d2hw.services;

import naderdeghaili.u5w3d2hw.entities.Dipendente;
import naderdeghaili.u5w3d2hw.exceptions.UnauthorizedException;
import naderdeghaili.u5w3d2hw.payloads.LoginDTO;
import naderdeghaili.u5w3d2hw.repositories.DipendentiRepository;
import naderdeghaili.u5w3d2hw.security.JWTTools;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class LoginService {

    private final DipendentiService dipendentiService;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;


    public LoginService(DipendentiService dipendentiService, JWTTools jwtTools, DipendentiRepository dipendentiRepository, PasswordEncoder bcrypt) {
        this.dipendentiService = dipendentiService;
        this.jwtTools = jwtTools;


        this.bcrypt = bcrypt;
    }


    public String getCredentialsGiveToken(LoginDTO body) {
        Dipendente found = this.dipendentiService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwtTools.createToken(found);

            return accessToken;

        } else {
            throw new UnauthorizedException("Credenziali errate, riprova");
        }

    }
}


