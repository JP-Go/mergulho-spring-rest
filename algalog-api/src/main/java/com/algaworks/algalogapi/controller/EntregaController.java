package com.algaworks.algalogapi.controller;

import com.algaworks.algalogapi.domain.model.Entrega;
import com.algaworks.algalogapi.domain.repository.EntregaRepository;
import com.algaworks.algalogapi.domain.service.SolicitacaoEntregaService;
import com.algaworks.algalogapi.model.EntregaModel;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas")
public class EntregaController {

  private SolicitacaoEntregaService solicitacaoEntregaService;
  private EntregaRepository entregaRepository;
  private ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntregaModel solicitar(@Valid @RequestBody Entrega entrega) {
    return modelMapper.map(solicitacaoEntregaService.solicitar(entrega), EntregaModel.class);
  }

  @GetMapping
  public List<EntregaModel> listar() {
    return entregaRepository.findAll().stream()
        .map(entrega -> modelMapper.map(entrega, EntregaModel.class))
        .toList();
  }

  @GetMapping("/{entregaId}")
  public ResponseEntity<EntregaModel> buscarPorId(@PathVariable Long entregaId) {
    return entregaRepository
        .findById(entregaId)
        .map(entrega -> ResponseEntity.ok(modelMapper.map(entrega, EntregaModel.class)))
        .orElse(ResponseEntity.notFound().build());
  }
}
