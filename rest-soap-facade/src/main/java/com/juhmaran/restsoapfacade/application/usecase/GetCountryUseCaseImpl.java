package com.juhmaran.restsoapfacade.application.usecase;

import com.juhmaran.restsoapfacade.application.domain.model.Country;
import com.juhmaran.restsoapfacade.application.port.in.GetCountryUseCase;
import com.juhmaran.restsoapfacade.application.port.out.CountryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetCountryUseCaseImpl implements GetCountryUseCase {

  private final CountryPort countryPort;

  @Override
  public Optional<Country> getCountry(String name) {
    return countryPort.findCountryByName(name);
  }

}
