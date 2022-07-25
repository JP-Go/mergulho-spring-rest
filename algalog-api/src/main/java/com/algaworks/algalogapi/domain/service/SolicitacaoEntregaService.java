package com.algaworks.algalogapi.domain.service;

import com.algaworks.algalogapi.domain.model.Cliente;
import com.algaworks.algalogapi.domain.model.Entrega;
import com.algaworks.algalogapi.domain.model.StatusEntrega;
import com.algaworks.algalogapi.domain.repository.EntregaRepository;
import java.time.OffsetDateTime;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

  private EntregaRepository entregaRepository;
  private CatalogoClienteService catalogoClienteService;

  @Transactional
  public Entrega solicitar(Entrega entrega) {

    Cliente cliente = catalogoClienteService.buscarPorId(entrega.getCliente().getId());
    entrega.setCliente(cliente);
    entrega.setDataPedido(OffsetDateTime.now());
    entrega.setStatus(StatusEntrega.PENDENTE);

    return entregaRepository.save(entrega);
  }
}
