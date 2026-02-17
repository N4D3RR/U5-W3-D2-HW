package naderdeghaili.u5w3d2hw.exceptions;

import naderdeghaili.u5w3d2hw.payloads.ErrorsDTO;
import naderdeghaili.u5w3d2hw.payloads.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsWithListDTO handleValidationException(ValidationException ex) {

        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsMessages());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFoundException(NotFoundException ex) {

        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsDTO handleUnauthorizedException(UnauthorizedException ex) {

        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorsDTO handleAuthorizationDeniedException(AuthorizationDeniedException ex) {

        return new ErrorsDTO("Non disponi dei permessi necessari", LocalDateTime.now());
    }


}