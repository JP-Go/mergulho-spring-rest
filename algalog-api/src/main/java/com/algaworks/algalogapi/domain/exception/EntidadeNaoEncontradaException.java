package com.algaworks.algalogapi.domain.exception;

public class EntidadeNaoEncontradaException extends DomainException {

	private static final long serialVersionId = 1L;

  public EntidadeNaoEncontradaException(String mensagem) {
    super(mensagem);
  }
}
