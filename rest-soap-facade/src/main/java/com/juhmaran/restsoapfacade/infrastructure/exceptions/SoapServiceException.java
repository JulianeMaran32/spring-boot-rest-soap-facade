package com.juhmaran.restsoapfacade.infrastructure.exceptions;

import lombok.Getter;

/**
 * Exceção personalizada para representar erros durante a comunicação
 * com o serviço SOAP externo.
 */
@Getter
public class SoapServiceException extends RuntimeException {

  private final int statusCode;

  public SoapServiceException(String message, int statusCode, Throwable cause) {
    super(message, cause);
    this.statusCode = statusCode;
  }

  public SoapServiceException(String message, Throwable cause) {
    super(message, cause);
    this.statusCode = 500; // Padrão para erros não HTTP (ex: falha de conexão)
  }

}
