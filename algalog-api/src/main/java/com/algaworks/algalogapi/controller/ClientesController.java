package com.algaworks.algalogapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogapi.domain.model.Cliente;
import com.algaworks.algalogapi.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

// Especifica que esta classe é um controlador de uma API rest
@RestController
@AllArgsConstructor // Gera um construtor com todas as propriedades como parametros, melhor para testar
public class ClientesController {

	// Com essa anotação o spring data jpa gera automaticamente uma classe que implementa 
	// essa interface. Porém isso dificulta testes
	// @Autowired
	// private ClienteRepository clienteRepository;
	private ClienteRepository clienteRepository;

	// Implementa um método que busca todos os clientes
  @GetMapping("/clientes")
  public List<Cliente> listar() {
    return clienteRepository.findAll();
  }

}
