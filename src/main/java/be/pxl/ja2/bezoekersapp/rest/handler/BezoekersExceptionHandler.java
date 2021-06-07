package be.pxl.ja2.bezoekersapp.rest.handler;

import be.pxl.ja2.bezoekersapp.util.exception.BezoekersAppException;
import be.pxl.ja2.bezoekersapp.util.exception.OngeldigTijdstipException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// Geeft advies aan de restcontroller wat die moet doen met exceptions.
public class BezoekersExceptionHandler {
    @ExceptionHandler(value = {BezoekersAppException.class, OngeldigTijdstipException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage errorMessage(RuntimeException exception) {
        return new ErrorMessage(exception.getMessage());
    }
}

