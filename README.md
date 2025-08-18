# Spring Boot: API REST como Fachada para um Serviço SOAP

Este projeto é um exemplo prático que demonstra como implementar o padrão de projeto **Facade**. Criamos uma API REST moderna e amigável utilizando **Spring Boot 3.5** que consome, nos bastidores, um serviço web SOAP legado (simulado dentro da própria aplicação para fins didáticos).

Este padrão é extremamente útil em cenários de modernização de sistemas, onde é preciso expor uma interface simples (JSON/REST) para clientes novos, sem modificar o serviço SOAP existente.

## Conceitos Chave Demonstrados

* **API REST**: Criação de endpoints RESTful com Spring Web (`@RestController`).
* **Consumo de SOAP**: Utilização do `WebServiceTemplate` do Spring Web Services para chamar um serviço SOAP.
* **Mock de Serviço SOAP**: Exposição de um serviço SOAP simples dentro da mesma aplicação para facilitar os testes.
* **Abordagem Contract-First**: Geração de classes Java (JAXB) a partir de um schema XSD.
* **Padrão DTO (Data Transfer Object)**: Mapeamento da resposta SOAP (XML) para um objeto JSON limpo na camada REST.

## Pré-requisitos

* **Java 21** ou superior.
* **Maven 3.9.x** ou superior.
* Uma IDE de sua preferência (IntelliJ IDEA, VS Code, etc.).

## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/spring-boot-rest-soap-facade.git
   cd spring-boot-rest-soap-facade
   ```

2. **Compile o projeto com Maven:**
   Este passo é **essencial**, pois ele aciona o plugin JAXB para gerar as classes Java a partir do arquivo
   `countries.xsd`.
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```
   A aplicação estará rodando em `http://localhost:8080`.

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.0**
* **Spring Web** (para API REST)
* **Spring Web Services** (para SOAP)
* **Maven**
* **JAXB** (para binding XML-Java)
* **WSDL4J**

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Mais informações

Saiba mais no [README.md](https://github.com/JulianeMaran32/spring-boot-rest-soap-facade/blob/main/rest-soap-facade/README.md) da aplicação.
