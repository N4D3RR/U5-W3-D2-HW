package naderdeghaili.u5w3d2hw.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

// TODO: togliere dipendenteId e viaggioId, da passare con AuthenticationPrincipal
public record NewPrenotazioneDTO(
        @NotNull(message = "il dipendente è obbligatorio") UUID dipendenteId,
        @NotNull(message = "il viaggio è obbligatorio") UUID viaggioId,
        @Size(max = 300, message = "la nota può avere massimo 300 caratteri") String note) {
}
