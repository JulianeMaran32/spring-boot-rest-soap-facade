package com.juhmaran.restsoapfacade.infrastructure.exceptions;

/**
 * Exceção específica para o caso de um país não ser encontrado,
 * que será mapeado para o status `404 Not Found`
 */
public class CountryNotFoundException extends RuntimeException {

  public CountryNotFoundException(String message) {
    super(message);
  }

}
