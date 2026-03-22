# API de Clientes, Produtos e Vendas

Projeto Spring Boot com persistencia em H2 para cadastro de clientes, produtos e vendas.

## Estrutura

```text
com.seuprojeto.api
|-- controller
|-- dto
|-- exception
|-- model
|-- repository
|-- service
`-- ApiApplication.java
```

Pacotes principais:

- `controller`: endpoints REST.
- `service`: regras de negocio.
- `repository`: acesso ao banco com Spring Data JPA.
- `model`: entidades JPA.
- `dto`: objetos de entrada das requisicoes.
- `exception`: excecoes e tratamento global.

## Como executar

Pre-requisito:

- JDK instalado e `JAVA_HOME` apontando para esse JDK.
- Java 25, conforme configurado em `pom.xml`.

Executar com Maven Wrapper:

```powershell
.\mvnw spring-boot:run
```

Executar testes:

```powershell
.\mvnw test
```

Aplicacao disponivel em:

```text
http://localhost:8080
```

## Banco H2

Configuracao atual em [`src/main/resources/application.properties`](C:\Users\Usuario\Documents\Insper\arq_obj\template25\src\main\resources\application.properties):

- URL JDBC: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Senha: vazia
- Console H2 habilitado

Para abrir o console do banco no navegador:

```text
http://localhost:8080/h2-console
```

Preencha os campos assim:

- `JDBC URL`: `jdbc:h2:mem:testdb`
- `User Name`: `sa`
- `Password`: deixe em branco

Observacao:

- Como o banco e em memoria, os dados sao perdidos quando a aplicacao e encerrada.

## Endpoints

### Clientes

- `POST /clientes`
- `GET /clientes`
- `GET /clientes/{id}`
- `PUT /clientes/{id}`
- `DELETE /clientes/{id}`

Exemplo de body:

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com"
}
```

### Produtos

- `POST /produtos`
- `GET /produtos`
- `GET /produtos/{id}`
- `PUT /produtos/{id}`
- `DELETE /produtos/{id}`

Exemplo de body:

```json
{
  "nome": "Notebook",
  "preco": 3500.0,
  "estoque": 10
}
```

### Vendas

- `POST /vendas`
- `GET /vendas`
- `GET /vendas/{id}`
- `DELETE /vendas/{id}`

Exemplo de body:

```json
{
  "clienteId": 1,
  "produtoId": 1,
  "quantidade": 2,
  "data": "2026-03-21"
}
```

Regra de negocio:

- Ao criar uma venda, o estoque do produto e reduzido.
- Se a quantidade for invalida ou o estoque for insuficiente, a API retorna `400 Bad Request`.
- Se cliente, produto ou venda nao existirem, a API retorna `404 Not Found`.

## Collection da API

A collection Postman esta em [`docs/api-collection.postman_collection.json`](C:\Users\Usuario\Documents\Insper\arq_obj\template25\docs\api-collection.postman_collection.json).

Como importar no Postman:

1. Abra o Postman.
2. Clique em `Import`.
3. Selecione o arquivo da collection.
4. Execute as requisicoes usando a variavel `{{baseUrl}}`, configurada por padrao como `http://localhost:8080`.

Fluxo sugerido:

1. Criar um cliente.
2. Criar um produto.
3. Criar uma venda usando os IDs retornados.
4. Consultar listas e itens individuais.
