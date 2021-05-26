package com.app.moneyapi.exception.handler;

import com.app.moneyapi.dto.error.Erros;
import com.app.moneyapi.dto.error.RequestError;
import com.app.moneyapi.exception.NonExistentOrInactivePersonException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class MoneyApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMsg = messageSource.getMessage("invalid.body", null, LocaleContextHolder.getLocale());
        String errorMsg = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

        List<Erros> errors = Arrays.asList(new Erros(userMsg, errorMsg));

        return handleExceptionInternal(ex, new RequestError(errors), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erros> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String userMsg = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String errorMsg = fieldError.toString();
            errors.add(new Erros(userMsg, errorMsg));
        });

        return handleExceptionInternal(ex, new RequestError(errors), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String userMsg = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
        String errorMsg = ex.toString();
        List<Erros> errors = Arrays.asList(new Erros(userMsg, errorMsg));

        return handleExceptionInternal(ex, new RequestError(errors), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String userMsg = messageSource.getMessage("resource.operation-not-allowed", null, LocaleContextHolder.getLocale());
        String errorMsg = ExceptionUtils.getRootCauseMessage(ex);
        List<Erros> errors = Arrays.asList(new Erros(userMsg, errorMsg));

        return handleExceptionInternal(ex, new RequestError(errors), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({NonExistentOrInactivePersonException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(NonExistentOrInactivePersonException ex, WebRequest request) {
        String userMsg = messageSource.getMessage("people.nonexistent-or-inactive", null, LocaleContextHolder.getLocale());
        String errorMsg = ex.toString();
        List<Erros> errors = Arrays.asList(new Erros(userMsg, errorMsg));

        return handleExceptionInternal(ex, new RequestError(errors), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
