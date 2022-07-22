package com.algaworks.algalogapi.controller;

import com.algaworks.algalogapi.domain.model.Cliente;
import com.algaworks.algalogapi.domain.repository.ClienteRepository;
import com.algaworks.algalogapi.domain.service.CatalogoClienteService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// Especifica que esta classe é um controlador de uma API rest
@RestController
@AllArgsConstructor // Gera um construtor com todas as propriedades como parametros, melhor para
// testar
@RequestMapping("/clientes") // diz que essa classe implementa subrotas da rota /clientes
public class ClientesController {

  // Com essa anotação o spring data jpa gera automaticamente uma classe que implementa
  // essa interface. Porém isso dificulta testes
  // @Autowired
  // private ClienteRepository clienteRepository;
  private ClienteRepository clienteRepository;
  private CatalogoClienteService catalogoClienteService;

  // Implementa um método que busca todos os clientes
  // Como esse getmapping não tem nenhuma subrota, ele é a rota index (/clientes/)
  @GetMapping
  public List<Cliente> listar() {
    return clienteRepository.findAll();
  }

  // Quando um mapping tem uma variável, ela deve ser envolvida em chaves
  @GetMapping("/{clienteId}") // e adiciona a anotação PathVariable (para dado que vem na url)
  public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {

    // Por padrão, findById retorna um Optional, por isso:
    return clienteRepository
        .findById(clienteId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping // RequestBody transforma o corpo da requisição em um objeto
  @ResponseStatus(HttpStatus.CREATED) // Diz que essa requisição retorna 201
  public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
    // save(entidade) -> salva a entidade e retorna ela automaticamente
    return catalogoClienteService.salvar(cliente);
  }

  @PutMapping("/{clienteId}")
  public ResponseEntity<Cliente> atualizar(
      @PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
    if (!clienteRepository.existsById(clienteId)) {
      return ResponseEntity.notFound().build();
    }

    cliente.setId(clienteId);
    // Cuidado, se não setar o Id do cliente diretamente, esse código gera um novo cliente
    cliente = catalogoClienteService.salvar(cliente);
    return ResponseEntity.ok(cliente);
  }

  @DeleteMapping("/{clienteId}")
  // O corpo da resposta não existe, logo, ResponseEntity  tem tipo void
  public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
    if (!clienteRepository.existsById(clienteId)) {
      return ResponseEntity.notFound().build();
    }

    catalogoClienteService.remover(clienteId);
    return ResponseEntity.noContent().build();
  }
}
