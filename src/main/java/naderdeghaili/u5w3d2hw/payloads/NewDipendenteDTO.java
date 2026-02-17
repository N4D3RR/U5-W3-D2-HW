package naderdeghaili.u5w3d2hw.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotBlank(message = "l'username è obbligatorio") @Size(min = 2, max = 20, message = "l'username deve essere compreso tra 2 e 20 caratteri") String username,
        @NotBlank(message = "il nome è obbligatorio") @Size(min = 2, max = 20, message = "il nome deve essere compreso tra 2 e 20 caratteri") String nome,
        @NotBlank(message = "il cognome è obbligatorio") @Size(min = 2, max = 20, message = "il cognome deve essere compreso tra 2 e 20 caratteri") String cognome,
        @NotBlank(message = "la mail è obbligatoria") @Email(message = "la mail non è nel formato corretto") String email
) {
}
