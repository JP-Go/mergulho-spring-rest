package com.algaworks.algalogapi.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

// Informa ao expring que este é um componente que trata exceções
// Para todos os controllers da aplicação
@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    List<Problema.Campo> campos =
        ex.getBindingResult().getAllErrors().stream()
            .map(
                e -> {
                  String mensagem = messageSource.getMessage(e,LocaleContextHolder.getLocale());
                  String nome = ((FieldError) e).getField();
                  return new Problema.Campo(nome, mensagem);
                })
            .toList();

    Problema problema =
        new Problema(
            status.value(),
            LocalDateTime.now(),
            "Um ou mais campos estão invalidos! Preencha os dados corretamente e tente novamente.",
            campos);

    return super.handleExceptionInternal(ex, problema, headers, status, request);
  }
}
