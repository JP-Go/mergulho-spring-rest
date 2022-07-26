package com.algaworks.algalogapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalogapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalogapi.domain.model.Entrega;
import com.algaworks.algalogapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega buscar(Long entregaId){
		return  entregaRepository.findById(entregaId).orElseThrow(
					() -> new EntidadeNaoEncontradaException("Entrega não existente")
				);
	}
}
