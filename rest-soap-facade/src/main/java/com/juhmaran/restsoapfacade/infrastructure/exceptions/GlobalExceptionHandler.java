package com.juhmaran.restsoapfacade.infrastructure.exceptions;

import com.juhmaran.restsoapfacade.infrastructure.exceptions.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.ws.client.WebServiceIOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CountryNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleCountryNotFoundException(CountryNotFoundException ex) {
    log.warn("Tentativa de buscar um país que não existe: {}", ex.getMessage());
    var errorResponse = new ErrorResponseDTO(
      HttpStatus.NOT_FOUND,
      ex.getMessage()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(WebServiceIOException.class)
  public ResponseEntity<ErrorResponseDTO> handleSoapConnectionException(WebServiceIOException ex) {
    log.error("Erro de I/O na comunicação com o serviço SOAP.", ex);
    var errorResponse = new ErrorResponseDTO(HttpStatus.SERVICE_UNAVAILABLE,
      "O serviço externo está temporariamente indisponível. Tente novamente mais tarde.");
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
  }

  @ExceptionHandler(SoapServiceException.class)
  public ResponseEntity<ErrorResponseDTO> handleSoapServiceException(SoapServiceException ex) {
    log.error("Erro ao comunicar com o serviço SOAP dependente.", ex);

    String details = String.format(
      "O serviço externo de países não pôde processar a requisição. Código de erro recebido: %d.",
      ex.getStatusCode()
    );

    var errorResponse = new ErrorResponseDTO(HttpStatus.BAD_GATEWAY, details);
    return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
    log.error("Ocorreu um erro inesperado na aplicação.", ex);
    var errorResponse = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR,
      "Ocorreu um erro inesperado no servidor. Contate o suporte.");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }

}
