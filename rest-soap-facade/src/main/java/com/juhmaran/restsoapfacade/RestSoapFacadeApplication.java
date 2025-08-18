package com.juhmaran.restsoapfacade;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
  info = @Info(
    title = "REST SOAP Facade",
    version = "1.0.0",
    description = "Projeto de exemplo em Java 21 e Spring Boot 3.5 que demonstra o padrão Facade para criar uma " +
      "API REST moderna consumindo um serviço web SOAP legado.",
    license = @License(
      name = "MIT License",
      url = "https://mit-license.org/"),
    contact = @Contact(
      name = "Juliane",
      email = "juhvaliatimaran@gmail.com")
  ),
  servers = @Server(
    url = "http://localhost:8080",
    description = "Ambiente de Desenvolvimento Local")
)
@SpringBootApplication
public class RestSoapFacadeApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestSoapFacadeApplication.class, args);
  }

}
