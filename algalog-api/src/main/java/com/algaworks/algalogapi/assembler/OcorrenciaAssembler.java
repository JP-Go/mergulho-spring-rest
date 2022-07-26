package com.algaworks.algalogapi.assembler;

import com.algaworks.algalogapi.domain.model.Ocorrencia;
import com.algaworks.algalogapi.model.OcorrenciaModel;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

    private ModelMapper mapper;

    public OcorrenciaModel toModel(Ocorrencia ocorrencia){
	return mapper.map(ocorrencia, OcorrenciaModel.class);
       
    }
}
