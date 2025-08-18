package com.juhmaran.restsoapfacade.infrastructure.soap.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

  // 1. Injete a URL aqui na classe de configuração.
  @Value("${soap.client.country-service-url}")
  private String countryServiceUrl;

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // 2. Garanta que o contextPath está correto.
    marshaller.setContextPath("com.juhmaran.restsoapfacade.stubs");
    return marshaller;
  }

  @Bean
  public CountrySoapClient countryClient(Jaxb2Marshaller marshaller) {
    var client = new CountrySoapClient();
    client.setDefaultUri("http://localhost:8080/ws");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);

    // 3. Configure a URL no cliente usando o setter que criamos.
    client.setCountryServiceUrl(countryServiceUrl);

    return client;
  }

}
