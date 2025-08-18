package com.juhmaran.restsoapfacade.application.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "País")
public record Country(
  @Schema(description = "Nome do País", example = "Brasil")
  String name,
  @Schema(description = "Nome da Capital do País", example = "Brasília")
  String capital,
  @Schema(description = "Moeda", example = "BRL")
  String currency
) {
}
