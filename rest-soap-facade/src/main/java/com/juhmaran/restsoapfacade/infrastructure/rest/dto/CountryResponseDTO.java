package com.juhmaran.restsoapfacade.infrastructure.rest.dto;

import com.juhmaran.restsoapfacade.application.domain.model.Country;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Representa os dados de resposta para a consulta de um país.")
public record CountryResponseDTO(
  @Schema(description = "Nome oficial do país.", example = "Brasil")
  String name,
  @Schema(description = "Capital do país.", example = "Brasília")
  String capital,
  @Schema(description = "Código da moeda local (padrão ISO 4217).", example = "BRL")
  String currency
) {

  public static CountryResponseDTO fromDomain(Country country) {
    return new CountryResponseDTO(country.name(), country.capital(), country.currency());
  }

}
