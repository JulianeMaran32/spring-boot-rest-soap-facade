package com.juhmaran.restsoapfacade.infrastructure.soap.client;

import com.juhmaran.restsoapfacade.infrastructure.exceptions.SoapServiceException;
import com.juhmaran.restsoapfacade.stubs.GetCountryRequest;
import com.juhmaran.restsoapfacade.stubs.GetCountryResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.WebServiceTransportException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Component
@Setter
@Slf4j
@RequiredArgsConstructor
public class CountrySoapClient extends WebServiceGatewaySupport {

  private String countryServiceUrl;

  public GetCountryResponse getCountry(String countryName) {
    var request = new GetCountryRequest();
    request.setName(countryName);

    log.info("Cliente SOAP: Enviando requisição para '{}' com o país: {}", countryServiceUrl, countryName);
    try {
      var response = (GetCountryResponse) getWebServiceTemplate()
        .marshalSendAndReceive(countryServiceUrl, request);
      log.info("Cliente SOAP: Resposta recebida com sucesso.");
      return response;
    } catch (WebServiceTransportException e) {
      // Extrai o status code da mensagem de erro (ex: "Not Found [404]")
      int statusCode = extractStatusCode(e.getMessage());
      String errorMessage = String.format("O serviço SOAP em '%s' retornou um erro HTTP %d.", countryServiceUrl, statusCode);
      log.error(errorMessage, e);
      // Lança nossa exceção personalizada com os detalhes
      throw new SoapServiceException(errorMessage, statusCode, e);
    } catch (Exception e) {
      // Para outros tipos de erro (ex: falha de conexão, timeout)
      String errorMessage = String.format("Falha na comunicação com o serviço SOAP em '%s'.", countryServiceUrl);
      log.error(errorMessage, e);
      throw new SoapServiceException(errorMessage, e);
    }
  }

  /**
   * Helper para extrair o código de status HTTP da mensagem da exceção.
   * A mensagem de WebServiceTransportException geralmente é no formato "Status Message [statusCode]".
   */
  private int extractStatusCode(String message) {
    if (message != null) {
      var pattern = Pattern.compile("\\[(\\d{3})\\]"); // Procura por 3 dígitos entre colchetes
      Matcher matcher = pattern.matcher(message);
      if (matcher.find()) {
        return Integer.parseInt(matcher.group(1));
      }
    }
    return 500; // Retorna 500 se não conseguir extrair
  }

}
