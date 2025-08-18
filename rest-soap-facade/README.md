# Fachada REST para Serviço SOAP de Países

Este projeto implementa uma fachada (facade) REST em Spring Boot que atua como um intermediário para um serviço web
SOAP. A aplicação expõe um endpoint RESTful para buscar informações de países, consumindo os dados de um serviço SOAP
que ela mesma provê, demonstrando um padrão comum de modernização de legados.

## Arquitetura

O projeto é estruturado seguindo os princípios da **Arquitetura Hexagonal (Portas e Adaptadores)**, que visa isolar a
lógica de negócio (domínio) das dependências de infraestrutura (frameworks, bancos de dados, serviços externos).

- **`/application` (Domínio e Casos de Uso)**
    - **`domain/model`**: Contém as entidades centrais do negócio, como `Country`.
    - **`port/in`**: Define as interfaces dos casos de uso (`GetCountryUseCase`), que representam as funcionalidades
      expostas pela aplicação.
    - **`port/out`**: Define as interfaces das portas de saída (`CountryPort`), que abstraem a comunicação com sistemas
      externos (neste caso, o serviço SOAP).
    - **`usecase`**: Contém a implementação da lógica de negócio, orquestrando as interações entre as portas.

- **`/infrastructure` (Infraestrutura e Adaptadores)**
    - **`rest`**: Adaptador de entrada que expõe a funcionalidade como uma API REST (`CountryController`).
    - **`soap/client`**: Adaptador de saída que implementa a `CountryPort` para consumir o serviço web SOAP externo.
    - **`soap/server`**: Adaptador que expõe um serviço web SOAP (`CountryEndpoint`) para simular o sistema legado.
    - **`exceptions`**: Gerenciador global de exceções para padronizar as respostas de erro da API.
    - **`config`**: Configurações de beans do Spring, como o cliente SOAP.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.x**
- **Spring Web**: Para a criação da API REST.
- **Spring Web Services**: Para criar e consumir o serviço SOAP.
- **Maven**: Gerenciador de dependências e build.
- **JAXB**: Para o binding entre objetos Java e XML (geração dos stubs SOAP).
- **Lombok**: Para reduzir código boilerplate (getters, setters, construtores).
- **JUnit 5 & Mockito**: Para testes unitários e de integração.

## Pré-requisitos

- JDK 21 ou superior.
- Apache Maven 3.6 ou superior.

## Como Executar

1. Clone o repositório
    ```bash
    git clone <url-do-seu-repositorio>
    cd rest-soap-facade
    ```
2. Gere as classes JAXB (Stubs):
   O projeto utiliza um perfil Maven para gerar as classes Java a partir do `countries.xsd`. Execute o comando abaixo na
   raiz do projeto:

    ```bash
    mvn clean install -Pgenerate-stubs```
    *Este comando deve ser executado apenas uma vez ou sempre que o arquivo `countries.xsd` for alterado.*
    
3. Execute a aplicação:
   Após o build, você pode iniciar a aplicação com o seguinte comando:

   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`.

## Testes

Para rodar a suíte completa de testes unitários e de integração, utilize o comando:

```bash
mvn test
```

## Documentação da API

A API expõe um endpoint para consulta de informações de países.

### `GET /country/{name}`

Busca os dados de um país pelo seu nome (case-insensitive).

**Parâmetros de URL:**

| Parâmetro | Tipo   | Descrição                   | Exemplo  |
|:----------|:-------|:----------------------------|:---------|
| `name`    | String | Nome do país a ser buscado. | `brazil` |

**Exemplo de Requisição (cURL):**

```cURL
curl --location 'localhost:8080/country/brazil'
```

---

### Respostas Possíveis

#### `200 OK`

Retornado quando o país é encontrado com sucesso.

**Corpo da Resposta:**

```json
{
  "name": "Brasil",
  "capital": "Brasília",
  "currency": "BRL"
}
```

---

#### `404 Not Found`

Retornado quando o país solicitado não existe na base de dados.

**Corpo da Resposta:**

```json
{
  "title": "Not Found",
  "status": 404,
  "details": "País 'argentina' não encontrado.",
  "timestamp": "2025-08-17T21:40:15.1234567"
}
```

---

#### `500 Internal Server Error`

Retornado quando ocorre um erro inesperado e não tratado no servidor.

**Corpo da Resposta:**

```json
{
  "title": "Internal Server Error",
  "status": 500,
  "details": "Ocorreu um erro inesperado no servidor. Contate o suporte.",
  "timestamp": "2025-08-17T21:42:20.1234567"
}
```

---

#### `502 Bad Gateway`

Retornado quando a fachada não consegue se comunicar com o serviço SOAP de origem ou quando o serviço SOAP retorna um
erro.

**Corpo da Resposta:**

```json
{
  "title": "Bad Gateway",
  "status": 502,
  "details": "O serviço externo de países não pôde processar a requisição. Código de erro recebido: 500.",
  "timestamp": "2025-08-17T21:45:10.1234567"
}
```

## Serviço Web SOAP

A aplicação também expõe o serviço SOAP que ela mesma consome. O WSDL pode ser acessado em:

`http://localhost:8080/ws/paises.wsdl`

Isso permite testar o serviço SOAP de forma isolada usando ferramentas como o SoapUI ou o próprio Postman.
