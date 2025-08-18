package com.juhmaran.restsoapfacade.infrastructure.soap.client;

import com.juhmaran.restsoapfacade.application.domain.model.Country;
import com.juhmaran.restsoapfacade.stubs.GetCountryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountrySoapAdapterTest {

  @Mock
  private CountrySoapClient soapClient;

  @InjectMocks
  private CountrySoapAdapter countrySoapAdapter;

  @Test
  @DisplayName("Deve mapear a resposta SOAP para o modelo de domínio quando o país existe")
  void findCountryByName_whenCountryExists_shouldMapToDomainModel() {

    // Arrange: Preparação do cenário
    // 1. Crie uma resposta SOAP de mentira (stub)
    var soapCountry = new com.juhmaran.restsoapfacade.stubs.Country();
    soapCountry.setName("Brasil");
    soapCountry.setCapital("Brasília");
    soapCountry.setCurrency("BRL");

    var soapResponse = new GetCountryResponse();
    soapResponse.setCountry(soapCountry);

    // 2. Programe o mock do cliente SOAP para retornar nossa resposta falsa
    when(soapClient.getCountry("brazil")).thenReturn(soapResponse);

    // Act: Execução da ação
    Optional<Country> result = countrySoapAdapter.findCountryByName("brazil");

    // Assert: Verificação do resultado
    assertThat(result).isPresent();
    assertThat(result.get().name()).isEqualTo("Brasil");
    assertThat(result.get().capital()).isEqualTo("Brasília");
    assertThat(result.get().currency()).isEqualTo("BRL");

  }

  @Test
  @DisplayName("Deve retornar um Optional vazio quando a resposta SOAP for nula")
  void findCountryByName_whenSoapResponseIsNull_shouldReturnEmptyOptional() {

    // Arrange: Programe o mock para retornar nulo
    when(soapClient.getCountry(anyString())).thenReturn(null);

    // Act
    Optional<Country> result = countrySoapAdapter.findCountryByName("PaísInexistente");

    // Assert
    assertThat(result).isNotPresent();

  }

  @Test
  @DisplayName("Deve retornar um Optional vazio quando o país na resposta SOAP for nulo")
  void findCountryByName_whenSoapCountryIsNull_shouldReturnEmptyOptional() {

    // Arrange: Programe o mock para retornar uma resposta sem o objeto Country
    var soapResponse = new GetCountryResponse();
    soapResponse.setCountry(null);
    when(soapClient.getCountry(anyString())).thenReturn(soapResponse);

    // Act
    Optional<Country> result = countrySoapAdapter.findCountryByName("PaísInexistente");

    // Assert
    assertThat(result).isNotPresent();

  }

}