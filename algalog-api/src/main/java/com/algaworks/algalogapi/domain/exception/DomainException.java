package com.algaworks.algalogapi.domain.exception;

/**
 * DomainException
 */
public class DomainException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DomainException(String mensagem){
		super(mensagem);
	}
}
