package com.algaworks.algalogapi.domain.model;

/*
	 Importante -> para poder comparar essas entidades é necessário implementar
	 os métodos hashCode() e equals(). Vamos utilizar o lombok para isso
*/
// Identifica uma coluna na tabela (relação: propriedade do objeto -> coluna da tabela)
import javax.persistence.Column;
// Identifica uma entidade do banco de dados (Uma tabela)
import javax.persistence.Entity;
// Informa que a propriedade é gerada automaticamente
import javax.persistence.GeneratedValue;
// Tipos de formas de geração de um valor para a propriedade
import javax.persistence.GenerationType;
// Identifica a chava primária (identifier)
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algalogapi.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Por padrão, o nome da tabela é o nome da classe
// Cria automaticamene hashCode() e equals()
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

  // Determina que somente esse campo vai ser usado para equals e hashCode
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Deixa que o banco cuida
	@NotNull(groups = ValidationGroups.ClienteId.class)
  private Long id;
  // Identifica o nome da coluna da tabela a qual essa propriedade se refere
  // Se o nome da propriedade e o nome da coluna são iguais, pode ser omitido
  @NotBlank
  @Size(max = 60)
  private String nome;

	@NotBlank
	@Email
	@Size(max = 255)
  private String email;

  @Column(name = "fone")
	@NotBlank
	@Size(max = 20)
  private String telefone;
}
