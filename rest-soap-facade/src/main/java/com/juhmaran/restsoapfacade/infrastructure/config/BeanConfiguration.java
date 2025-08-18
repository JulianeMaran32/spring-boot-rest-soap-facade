package com.juhmaran.restsoapfacade.infrastructure.config;

import com.juhmaran.restsoapfacade.application.port.in.GetCountryUseCase;
import com.juhmaran.restsoapfacade.application.port.out.CountryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public GetCountryUseCase getCountryUseCase(CountryPort countryPort) {
    return countryPort::findCountryByName;
  }

}
