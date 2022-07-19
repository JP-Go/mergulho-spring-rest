package com.algaworks.algalogapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogapi.domain.model.Cliente;

@RestController
public class ClientesController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar(){
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("João");
		cliente1.setTelefone("99 99999-9999");
		cliente1.setEmail("joaodascouves@algaworks.com");

		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setTelefone("99 99999-9998");
		cliente2.setEmail("mariadasilva@algaworks.com");
		
		return Arrays.asList(cliente1,cliente2);
	}
}
