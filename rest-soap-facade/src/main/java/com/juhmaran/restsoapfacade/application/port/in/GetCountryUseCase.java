package com.juhmaran.restsoapfacade.application.port.in;

import com.juhmaran.restsoapfacade.application.domain.model.Country;

import java.util.Optional;

/**
 * Porta de Entrada (Input Port).
 * <p>
 * Define o contrato para o caso de uso de obter informações de um país.
 */
public interface GetCountryUseCase {

  Optional<Country> getCountry(String name);

}
