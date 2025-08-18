package com.juhmaran.restsoapfacade.infrastructure.rest;

import com.juhmaran.restsoapfacade.application.domain.model.Country;
import com.juhmaran.restsoapfacade.application.port.in.GetCountryUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  GetCountryUseCase getCountryUseCase;

  @Test
  @DisplayName("GET /country/{name} - Deve retornar 200 OK com os dados do país quando encontrado")
  void getCountryInfo_whenCountryExists_shouldReturn200Ok() throws Exception {

    // Arrange
    var domainCountry = Country.builder()
      .name("Japão")
      .capital("Tóquio")
      .currency("JPY")
      .build();

    // Simulamos a camada de dados/SOAP para retornar nosso objeto de domínio
    when(getCountryUseCase.getCountry("japan")).thenReturn(Optional.of(domainCountry));

    // Act & Assert
    mockMvc.perform(get("/country/japan"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.name").value("Japão"))
      .andExpect(jsonPath("$.capital").value("Tóquio"))
      .andExpect(jsonPath("$.currency").value("JPY"));

  }

  @Test
  @DisplayName("GET /country/{name} - Deve retornar 404 Not Found quando o país não for encontrado")
  void getCountryInfo_whenCountryDoesNotExist_shouldReturn404NotFound() throws Exception {
    // Arrange
    // Simulamos a camada de dados/SOAP para retornar um Optional vazio
    when(getCountryUseCase.getCountry("Argentina")).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/country/Argentina"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.title").value("Not Found"))
      .andExpect(jsonPath("$.details").value("País 'Argentina' não encontrado."));
  }

}