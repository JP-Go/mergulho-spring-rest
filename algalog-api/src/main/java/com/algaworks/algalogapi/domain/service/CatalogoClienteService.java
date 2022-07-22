package com.algaworks.algalogapi.domain.service;

import com.algaworks.algalogapi.domain.exception.DomainException;
import com.algaworks.algalogapi.domain.model.Cliente;
import com.algaworks.algalogapi.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CatalogoClienteService {

  private ClienteRepository clienteRepository;

  @Transactional // Informa que isso deve ser executado em uma transação
  // Se algo der errado, a operação é parada
  public Cliente salvar(Cliente cliente) {
    boolean emailEmUso =
        clienteRepository.findByEmail(cliente.getEmail())
				  .stream()
          .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

		if (emailEmUso){
			throw new DomainException("Já existe um cliente cadastrado com esse email.");
		}

    return clienteRepository.save(cliente);
  }

  @Transactional
  public void remover(Long clienteId) {
    clienteRepository.deleteById(clienteId);
  }
}
