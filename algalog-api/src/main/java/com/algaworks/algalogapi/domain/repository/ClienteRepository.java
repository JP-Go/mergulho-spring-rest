package com.algaworks.algalogapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalogapi.domain.model.Cliente;


// Informa que isso é um repositório Spring
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	// Cria um método de consulta (Spring query method)
	// Tem sintaxe definida:
	// <verboPrefixo: Find,Query,Etc>By<nome da prop pela qual buscar><sufixo>
	List<Cliente> findByNome(String nome);
	Optional<Cliente> findByEmail(String email);
	List<Cliente> findByNomeContaining(String nomeParcial);

}
