package com.algaworks.algalogapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogapi.assembler.EntregaAssembler;
import com.algaworks.algalogapi.domain.model.Entrega;
import com.algaworks.algalogapi.domain.repository.EntregaRepository;
import com.algaworks.algalogapi.domain.service.FinalizarEntregaService;
import com.algaworks.algalogapi.domain.service.SolicitacaoEntregaService;
import com.algaworks.algalogapi.model.EntregaModel;
import com.algaworks.algalogapi.model.input.EntregaInput;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas")
public class EntregaController {

  private SolicitacaoEntregaService solicitacaoEntregaService;
  private EntregaRepository entregaRepository;
  private EntregaAssembler entregaAssembler;
	private FinalizarEntregaService finalizarEntregaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
    return entregaAssembler.toModel(entregaSolicitada);
  }

  @GetMapping
  public List<EntregaModel> listar() {
		List<Entrega> entregas = entregaRepository.findAll();
    return entregaAssembler.toCollectionModel(entregas);
	}

  @GetMapping("/{entregaId}")
  public ResponseEntity<EntregaModel> buscarPorId(@PathVariable Long entregaId) {
    return entregaRepository
        .findById(entregaId)
        .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
        .orElse(ResponseEntity.notFound().build());
  }

	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId){
		finalizarEntregaService.finalizar(entregaId);
	}
}
