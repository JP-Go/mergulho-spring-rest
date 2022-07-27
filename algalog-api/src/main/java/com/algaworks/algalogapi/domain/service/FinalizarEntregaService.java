package com.algaworks.algalogapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalogapi.domain.model.Entrega;
import com.algaworks.algalogapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizarEntregaService {

	private BuscaEntregaService buscaEntregaService;
	private EntregaRepository entregaRepository;

	@Transactional
	public void finalizar(Long entregaId){
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
}
