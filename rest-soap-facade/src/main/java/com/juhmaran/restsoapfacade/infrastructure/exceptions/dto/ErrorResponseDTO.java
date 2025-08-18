package com.juhmaran.restsoapfacade.infrastructure.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Representa a estrutura padrão para respostas de erro da API.")
public record ErrorResponseDTO(
  @Schema(description = "Resumo do status HTTP.", example = "Not Found")
  String title,
  @Schema(description = "Código do status HTTP.", example = "404")
  int status,
  @Schema(description = "Descrição detalhada do erro ocorrido.", example = "País 'argentina' não encontrado.")
  String details,
  @Schema(description = "Data e hora em que o erro ocorreu (UTC).", example = "2025-08-17T21:40:44.4117904")
  LocalDateTime timestamp,
  @Schema(description = "Lista de erros de validação específicos (usado principalmente para erros 400 Bad Request).",
    nullable = true)
  List<ValidationError> errors
) {

  @Schema(description = "Detalha um erro de validação específico de um campo.")
  public record ValidationError(
    @Schema(description = "Nome do campo que falhou na validação.", example = "countryName")
    String field,
    @Schema(description = "Mensagem descrevendo o erro de validação.",
      example = "O nome não pode conter caracteres especiais.")
    String message) {
  }

  public ErrorResponseDTO(HttpStatus status, String details) {
    this(
      status.getReasonPhrase(),
      status.value(),
      details,
      LocalDateTime.now(),
      null
    );
  }

  public ErrorResponseDTO(HttpStatus status, String details, List<ValidationError> errors) {
    this(
      status.getReasonPhrase(),
      status.value(),
      details,
      LocalDateTime.now(),
      errors
    );
  }

}