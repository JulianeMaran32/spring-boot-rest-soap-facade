package com.juhmaran.restsoapfacade.infrastructure.soap.server;

import com.juhmaran.restsoapfacade.stubs.GetCountryRequest;
import com.juhmaran.restsoapfacade.stubs.GetCountryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
@RequiredArgsConstructor
public class CountryEndpoint {

  private static final String NAMESPACE_URI = "http://www.example.com/soap/paises";

  private final CountryRepository countryRepository;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
  @ResponsePayload
  public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
    log.info("Endpoint SOAP: Recebida requisição para buscar o país: {}", request.getName());

    GetCountryResponse response = new GetCountryResponse();
    response.setCountry(countryRepository.findCountry(request.getName()));

    if (response.getCountry() != null) {
      log.info("Endpoint SOAP: País '{}' encontrado no repositório.", request.getName());
    } else {
      log.warn("Endpoint SOAP: País '{}' não encontrado no repositório.", request.getName());
    }

    return response;
  }

}
