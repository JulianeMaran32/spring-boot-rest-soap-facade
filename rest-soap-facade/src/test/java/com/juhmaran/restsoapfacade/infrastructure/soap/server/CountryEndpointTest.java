package com.juhmaran.restsoapfacade.infrastructure.soap.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@SpringBootTest
class CountryEndpointTest {

  @Autowired
  ApplicationContext applicationContext;

  MockWebServiceClient mockClient;

  private Resource brazilRequest;
  private Resource brazilResponse;
  private Resource notFoundRequest;
  private Resource notFoundResponse;

  @BeforeEach
  void setUp() {
    mockClient = MockWebServiceClient.createClient(applicationContext);
    // Carrega os arquivos XML que usaremos para os testes
    brazilRequest = new ClassPathResource("soap-requests/brazilRequest.xml");
    brazilResponse = new ClassPathResource("soap-responses/brazilResponse.xml");
    notFoundRequest = new ClassPathResource("soap-requests/notFoundRequest.xml");
    notFoundResponse = new ClassPathResource("soap-responses/notFoundResponse.xml");
  }

  @Test
  @DisplayName("Deve retornar os detalhes do país quando uma requisição SOAP válida for recebida")
  void getCountryEndpoint_whenCountryExists_shouldReturnCountryPayload() throws IOException {
    var requestPayload = new StringSource(Files.readString(brazilRequest.getFile().toPath()));
    var responsePayload = new StringSource(Files.readString(brazilResponse.getFile().toPath()));

    mockClient.sendRequest(withPayload(requestPayload))
      .andExpect(payload(responsePayload));
  }

  @Test
  @DisplayName("Deve retornar um payload sem dados do país quando o país não existe")
  void getCountryEndpoint_whenCountryDoesNotExist_shouldReturnEmptyCountryPayload() throws IOException {
    var requestPayload = new StringSource(Files.readString(notFoundRequest.getFile().toPath()));
    var responsePayload = new StringSource(Files.readString(notFoundResponse.getFile().toPath()));

    mockClient.sendRequest(withPayload(requestPayload))
      .andExpect(payload(responsePayload));
  }

}