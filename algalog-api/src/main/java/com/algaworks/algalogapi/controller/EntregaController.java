package com.algaworks.algalogapi.controller;

import com.algaworks.algalogapi.domain.model.Entrega;
import com.algaworks.algalogapi.domain.repository.EntregaRepository;
import com.algaworks.algalogapi.domain.service.SolicitacaoEntregaService;
import com.algaworks.algalogapi.model.DestinatarioModel;
import com.algaworks.algalogapi.model.EntregaModel;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Entrega solicitar(@Valid @RequestBody Entrega entrega) {

    return solicitacaoEntregaService.solicitar(entrega);
  }

  @GetMapping
  public List<EntregaModel> listar() {
    return entregaRepository.findAll().stream()
        .map(
            entrega -> {
              EntregaModel model = new EntregaModel();
              model.setId(entrega.getId());
              model.setNomeCliente(entrega.getCliente().getNome());
              model.setDestinatario(new DestinatarioModel());
              model.getDestinatario().setNome(entrega.getDestinatario().getNome());
              model.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
              model.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
              model.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
              model.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
              model.setTaxa(entrega.getTaxa());
              model.setStatus(entrega.getStatus());
              model.setDataPedido(entrega.getDataPedido());
              model.setDataFinalizacao(entrega.getDataFinalizacao());
              return model;
            })
        .toList();
  }

  @GetMapping("/{entregaId}")
  public ResponseEntity<EntregaModel> buscarPorId(@PathVariable Long entregaId) {
    return entregaRepository
        .findById(entregaId)
        .map(
            entrega -> {
              EntregaModel model = new EntregaModel();
              model.setId(entrega.getId());
              model.setNomeCliente(entrega.getCliente().getNome());
              model.setDestinatario(new DestinatarioModel());
              model.getDestinatario().setNome(entrega.getDestinatario().getNome());
              model.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
              model.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
              model.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
              model.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
              model.setTaxa(entrega.getTaxa());
              model.setStatus(entrega.getStatus());
              model.setDataPedido(entrega.getDataPedido());
              model.setDataFinalizacao(entrega.getDataFinalizacao());

              return ResponseEntity.ok(model);
            })
        .orElse(ResponseEntity.notFound().build());
  }
}
