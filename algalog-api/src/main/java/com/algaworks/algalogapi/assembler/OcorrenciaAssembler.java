package com.algaworks.algalogapi.assembler;

import com.algaworks.algalogapi.domain.model.Ocorrencia;
import com.algaworks.algalogapi.model.OcorrenciaModel;
import lombok.AllArgsConstructor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

  private ModelMapper mapper;

  public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
    return mapper.map(ocorrencia, OcorrenciaModel.class);
  }

	public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream().map(this::toModel).toList();
	}
}
