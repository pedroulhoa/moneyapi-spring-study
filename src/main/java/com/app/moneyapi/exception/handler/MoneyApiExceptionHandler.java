package com.app.moneyapi.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MoneyApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msgUsuario = messageSource.getMessage("body.invalido", null, LocaleContextHolder.getLocale());
        String msgErro = ex.getCause().getMessage();

        return handleExceptionInternal(ex, new Erro(msgUsuario, msgErro), headers, HttpStatus.BAD_REQUEST, request);
    }

    public static class Erro {
        private LocalDateTime data;
        private String msgUsuario;
        private String msgErro;

        public Erro(String msgUsuario, String msgErro) {
            this.msgUsuario = msgUsuario;
            this.msgErro = msgErro;
            this.data = LocalDateTime.now();
        }

        public String getMsgUsuario() {
            return msgUsuario;
        }

        public String getMsgErro() {
            return msgErro;
        }

        public LocalDateTime getData() {
            return data;
        }
    }

}
