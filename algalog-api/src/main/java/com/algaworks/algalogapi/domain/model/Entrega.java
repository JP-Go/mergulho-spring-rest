package com.algaworks.algalogapi.domain.model;

import com.algaworks.algalogapi.domain.ValidationGroups;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @ManyToOne // Clientes podem ter múltiplas entregas
  @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
  private Cliente cliente;

  @Embedded // Introduz todos os dados do destinatário na tabela de entrega
  private Destinatario destinatario;

  private BigDecimal taxa;

  // Armazena a propria string pq EnumType.String
  @Enumerated(EnumType.STRING) // informa que essa propriedade é uma enumeração
  private StatusEntrega status;

  private OffsetDateTime dataPedido;

  private OffsetDateTime dataFinalizacao;

  // Uma Entrega pode ter várias ocorrencias
  @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
  private List<Ocorrencia> ocorrencias = new ArrayList<>();

  public Ocorrencia adicionarOcorrencia(String descricao) {
    Ocorrencia ocorrencia = new Ocorrencia();
    ocorrencia.setDescricao(descricao);
    ocorrencia.setDataOcorrencia(OffsetDateTime.now());
    ocorrencia.setEntrega(this);
    this.getOcorrencias().add(ocorrencia);

    return ocorrencia;
  }
}
