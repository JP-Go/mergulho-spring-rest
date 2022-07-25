package com.algaworks.algalogapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algalogapi.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@ConvertGroup(from = Default.class,to = ValidationGroups.ClienteId.class)
	@NotNull
	@Valid
  private Cliente cliente;

  @Embedded // Introduz todos os dados do destinatário na tabela de entrega
	@NotNull
	@Valid
  private Destinatario destinatario;

	@NotNull
  private BigDecimal taxa;

  @Enumerated(EnumType.STRING) // informa que essa propriedade é uma enumeração
  // Armazena a propria string pq EnumType.String

  @JsonProperty(access = Access.READ_ONLY)
  private StatusEntrega status;

  @JsonProperty(access = Access.READ_ONLY)
  private OffsetDateTime dataPedido;

  @JsonProperty(access = Access.READ_ONLY)
  private OffsetDateTime dataFinalizacao;
}