package naderdeghaili.u5w3d2hw.exceptions;

import java.util.UUID;


public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Dipendente con id " + id + " Non trovato");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
