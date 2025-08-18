package com.juhmaran.restsoapfacade.application.port.out;

import com.juhmaran.restsoapfacade.application.domain.model.Country;

import java.util.Optional;

/**
 * Porta de Saída (Output Port).
 * <p>
 * Define o contrato que a infraestrutura deve implementar para buscar dados de um país.
 */
public interface CountryPort {

  Optional<Country> findCountryByName(String name);

}
