package dai_ichi_customer_portal.shared_modules.exception.filter;

import dai_ichi_customer_portal.shared_modules.exception.aabstract.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class GlobalExceptionFilter {

    @ExceptionHandler(AbstractUnauthorizedException.class)
    public final ResponseEntity<Object> handelUnAuthorizeOtpException(final AbstractUnauthorizedException exception) {
        final var error = new HashMap<String, Object>();
        error.put("code", HttpStatus.UNAUTHORIZED);
        error.put("message", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AbstractServiceUnavailableException.class)
    public final ResponseEntity<Object> handelServiceUnavailableException(final AbstractServiceUnavailableException exception) {
        final var error = new HashMap<String, Object>();
        error.put("code", HttpStatus.SERVICE_UNAVAILABLE);
        error.put("message", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(AbstractForbiddenException.class)
    public final ResponseEntity<Object> handelForbiddenExceptionException(final AbstractForbiddenException exception) {
        final var error = new HashMap<String, Object>();
        error.put("code", HttpStatus.FORBIDDEN);
        error.put("message", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AbstractResourceNotFoundException.class)
    public final ResponseEntity<Object> handelResourceNotFoundException(final AbstractResourceNotFoundException exception) {
        final var error = new HashMap<String, Object>();
        error.put("code", HttpStatus.NOT_FOUND);
        error.put("message", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AbstractInternalServerErrorException.class)
    public final ResponseEntity<Object> handelInternalServerErrorException(final AbstractInternalServerErrorException exception) {
        log.error(exception.getMessage());
        final var error = new HashMap<String, Object>();
        error.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        error.put("message", "Internal Server Error.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handelException(final Exception exception) {
        log.error(exception.getMessage());
        final var error = new HashMap<String, Object>();
        error.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        error.put("message", "Internal Server Error.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
