package com.app.moneyapi.dto.error;

import java.time.LocalDateTime;
import java.util.List;

public class RequestError {

    private LocalDateTime time;
    private List<Erros> erros;

    public RequestError(List<Erros> erros) {
        this.time = LocalDateTime.now();
        this.erros = erros;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public List<Erros> getErros() {
        return erros;
    }
}
