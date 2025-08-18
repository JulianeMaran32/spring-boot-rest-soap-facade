package com.juhmaran.restsoapfacade.infrastructure.soap.client;

import com.juhmaran.restsoapfacade.application.domain.model.Country;
import com.juhmaran.restsoapfacade.application.port.out.CountryPort;
import com.juhmaran.restsoapfacade.stubs.GetCountryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Esta classe implementa CountryPort.java, agindo como uma porta entre o domínio e o cliente SOAP.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CountrySoapAdapter implements CountryPort {

  private final CountrySoapClient soapClient;

  @Override
  public Optional<Country> findCountryByName(String name) {
    log.info("Adaptador SOAP: Buscando país '{}' no serviço externo.", name);

    // Chama o cliente SOAP real
    GetCountryResponse soapResponse = soapClient.getCountry(name);

    if (soapResponse == null || soapResponse.getCountry() == null) {
      log.warn("Adaptador SOAP: Serviço externo não retornou dados para o país '{}'.", name);
      return Optional.empty();
    }

    log.info("Adaptador SOAP: Dados recebidos com sucesso para o país '{}'. Mapeando para o domínio.", name);
    // Mapeia do DTO do stub SOAP para o nosso modelo de domínio puro
    return Optional.of(soapResponse.getCountry())
      .map(soapCountry -> new Country(
        soapCountry.getName(),
        soapCountry.getCapital(),
        soapCountry.getCurrency()
      ));
  }

}
