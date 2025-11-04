package br.com.coletaresiduos.coleta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CollectionPointNotFoundException extends RuntimeException {

    public CollectionPointNotFoundException(String message) {
        super(message);
    }

}
