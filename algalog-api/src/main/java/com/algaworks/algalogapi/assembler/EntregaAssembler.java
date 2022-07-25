package com.algaworks.algalogapi.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalogapi.domain.model.Entrega;
import com.algaworks.algalogapi.model.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {

	private ModelMapper mapper;


	public EntregaModel toModel(Entrega entrega){
		return mapper.map(entrega, EntregaModel.class);
	}

	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream().map(this::toModel).toList();
	}
	
}
