package naderdeghaili.u5w3d2hw.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorsMessages;

    public ValidationException(List<String> errorsMessages) {

        super("il payload contiene errori");
        this.errorsMessages = errorsMessages;
    }
}