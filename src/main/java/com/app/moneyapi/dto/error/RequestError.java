package com.app.moneyapi.dto.error;

import java.time.LocalDateTime;
import java.util.List;

public class RequestError {

    private LocalDateTime time;
    private List<Erros> errors;

    public RequestError(List<Erros> errors) {
        this.time = LocalDateTime.now();
        this.errors = errors;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public List<Erros> getErrors() {
        return errors;
    }
}
