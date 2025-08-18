package com.juhmaran.restsoapfacade.infrastructure.soap.server;

import com.juhmaran.restsoapfacade.stubs.Country;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryRepository {

  private static final Map<String, Country> countries = new HashMap<>();

  @PostConstruct
  public void initData() {

    var brazil = new Country();
    brazil.setName("Brasil");
    brazil.setCapital("Brasília");
    brazil.setCurrency("BRL");
    countries.put("brazil", brazil);

    var japan = new Country();
    japan.setName("Japão");
    japan.setCapital("Tóquio");
    japan.setCurrency("JPY");
    countries.put("japan", japan);

  }

  public Country findCountry(String name) {
    if (name == null) {
      return null;
    }
    return countries.keySet().stream().filter(key -> key.equalsIgnoreCase(name))
      .findFirst().map(countries::get).orElse(null);
  }

}
