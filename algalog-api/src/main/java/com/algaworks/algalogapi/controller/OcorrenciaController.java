package com.algaworks.algalogapi.controller;

import com.algaworks.algalogapi.assembler.OcorrenciaAssembler;
import com.algaworks.algalogapi.domain.model.Ocorrencia;
import com.algaworks.algalogapi.domain.service.RegistroOcorrenciaService;
import com.algaworks.algalogapi.model.OcorrenciaModel;
import com.algaworks.algalogapi.model.input.OcorrenciaInput;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

  private RegistroOcorrenciaService registroOcorrenciaService;
  private OcorrenciaAssembler ocorrenciaAssembler;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OcorrenciaModel registrar(
      @PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {

    Ocorrencia novaOcorrencia =
        registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

    return ocorrenciaAssembler.toModel(novaOcorrencia);
  }
}
