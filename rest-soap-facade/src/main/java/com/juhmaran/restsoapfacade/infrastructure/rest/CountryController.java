package com.juhmaran.restsoapfacade.infrastructure.rest;

import com.juhmaran.restsoapfacade.application.port.in.GetCountryUseCase;
import com.juhmaran.restsoapfacade.infrastructure.exceptions.CountryNotFoundException;
import com.juhmaran.restsoapfacade.infrastructure.exceptions.dto.ErrorResponseDTO;
import com.juhmaran.restsoapfacade.infrastructure.rest.dto.CountryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
@Tag(name = "Country", description = "Endpoint para consulta de informações sobre países.")
public class CountryController {

  private final GetCountryUseCase getCountryUseCase;

  @Operation(
    summary = "Busca um país pelo nome",
    description = "Retorna informações detalhadas de um país, como sua capital e moeda, com base no nome fornecido. " +
      "A busca não diferencia maiúsculas de minúsculas.",
    operationId = "getCountryByName"
  )
  @ApiResponse(responseCode = "200", description = "País encontrado com sucesso.",
    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = CountryResponseDTO.class)))
  @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os parâmetros enviados.",
    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ErrorResponseDTO.class)))
  @ApiResponse(responseCode = "404", description = "O país com o nome especificado não foi encontrado.",
    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ErrorResponseDTO.class)))
  @ApiResponse(responseCode = "500", description = "Erro interno inesperado no servidor.",
    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ErrorResponseDTO.class)))
  @ApiResponse(responseCode = "502", description = "O serviço externo de países está indisponível ou retornou um erro.",
    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      schema = @Schema(implementation = ErrorResponseDTO.class)))
  @GetMapping("/{name}")
  public ResponseEntity<CountryResponseDTO> getCountryInfo(@PathVariable String name) {
    log.info("Recebida requisição REST para buscar o país: {}", name);

    var country = getCountryUseCase.getCountry(name)
      .orElseThrow(() -> new CountryNotFoundException("País '" + name + "' não encontrado."));

    var responseDTO = CountryResponseDTO.fromDomain(country);
    log.info("País encontrado: {}. Retornando resposta.", responseDTO.name());

    return ResponseEntity.ok(responseDTO);
  }

}
