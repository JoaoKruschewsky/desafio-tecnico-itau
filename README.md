# URL Shortener API

## Sobre o projeto

Este projeto é uma API de encurtamento de URLs desenvolvida em **Java 21** utilizando **Spring Boot** e seguindo o conceito de **Arquitetura Hexagonal (Ports and Adapters)**.

A aplicação permite:

* Criar URLs encurtadas
* Redirecionar para a URL original
* Consultar detalhes da URL
* Ver estatísticas de cliques
* Listar URLs cadastradas

---

# Tecnologias utilizadas

* Java 21
* Spring Boot
* Gradle
* Docker
* JUnit
* Mockito
* Arquitetura Hexagonal

---

# Como rodar o projeto

## Rodar pela IDE

Execute a classe principal:

```
ShortnerApplication
```

A aplicação iniciará em:

```
http://localhost:8080
```

---

## Rodar com Docker

### Build da imagem

```
docker build -t apirunlocal .
```

### Rodar o container

```
docker run -p 8080:8080 apirunlocal
```

Você pode alterar o nome **apirunlocal** para qualquer nome de imagem que desejar.

---

# Executar os testes

Os testes estão localizados em:

```
app/src/test/java/com/example/demo/application/service
```

Eles podem ser executados diretamente pela IDE rodando cada teste individualmente.

---

# Arquitetura

O projeto utiliza **Arquitetura Hexagonal** com o objetivo de **desacoplar o domínio da infraestrutura**.

No ambiente de trabalho normalmente utilizamos a anotação `@Service` dentro da camada **application**, porém isso cria dependência com o framework.

Neste projeto optei por manter **Application e Domain como Java puro**, evitando anotações do Spring nessas camadas para manter o conceito da arquitetura hexagonal.

---

# Estratégia de geração do identifierUrl

A entidade possui:

* Um **ID primário gerado automaticamente pelo JPA**
* Um segundo identificador chamado **identifierUrl**

Exemplo de identificadores:

```
url1
url2
url3
url4
```

Processo utilizado:

1. Recupero a lista de `identifierUrl` existentes.
2. Pego o último item da lista.
3. Faço um `substring` para extrair o número após `url`.
4. Incremento o valor em **+1**.
5. Crio o novo identificador.

Exemplo:

```
url1
url2
url3
url4
```

Novo identificador:

```
url5
```

### Limitação conhecida

Existe um problema caso um registro intermediário seja removido.

Exemplo:

```
url1
url2
url3
url4
```

Se `url2` for removido, o algoritmo pode gerar novamente `url2`, causando conflito.

Essa melhoria foi deixada para uma possível evolução futura.

---

# Contagem de cliques

Cada URL possui um campo chamado:

```
clickCount
```

Fluxo:

1. Quando a URL é criada → `clickCount = 0`
2. Sempre que o endpoint de redirecionamento é chamado:

* o valor atual é recuperado
* incrementado em **+1**
* atualizado no banco

---

# Expiração de URLs

O campo `expirationDate` é recebido no **body da requisição**. Faco um substring e só pego a data.

Processo:

1. A data recebida é extraída
2. É convertida para um formato de data
3. É comparada com a data atual
4. Se a data estiver expirada → uma exceção é lançada

---

# Documentação da API

Base URL:

```
http://localhost:8080/url/v1
```

Todos os endpoints exigem o header:

```
x-api-key
```

Para testes, utilize a seguinte API Key:

```
80037aa6-21a5-4087-9124-fbbcaa18a866
```

```

---

# Criar URL curta

POST

```

/url/v1/urls

```

Header:

```

x-api-key: qualquer_valor

````

Body:

```json
{
  "originalUrl": "https://google.com",
  "expirationDate": "2026-03-23T23:59:59Z"
}
````

Response:

```json
{
  "shortUrl": "http://localhost:8080/url/v1/url1",
  "originalUrl": "https://google.com",
  "expirationDate": "2026-03-23T23:59:59Z"
}
```

---

# Redirecionar URL

GET

```
/url/v1/{identifierUrl}
```

Exemplo:

```
/url/v1/url1
```

Esse endpoint realiza o **redirect para a URL original**.

---

# Buscar detalhes da URL

GET

```
/url/v1/urls/{identifierUrl}
```

Response exemplo:

```json
{
  "shortUrl": "...",
  "originalUrl": "...",
  "expirationDate": "...",
  "createdAt": "...",
  "identifierUrl": "...",
  "countClick": 10
}
```

---

# Estatísticas da URL

GET

```
/url/v1/urls/{identifierUrl}/stats
```

Response:

```json
{
  "countClick": "10"
}
```

---

# Listar URLs

GET

```
/url/v1/urls/{quantityUrl}/all-urls
```

Exemplo:

```
/url/v1/urls/5/all-urls
```

Retorna as N URLs cadastradas, baseado no valor informado no path.
Essa abordagem funciona como uma paginação simples baseada em limite (limit), onde o cliente informa quantos registros deseja receber.
---

# Melhorias futuras

Possíveis evoluções do projeto:

* Melhor algoritmo para geração de `identifierUrl`
* Evoluir a listagem para paginação completa (page + size ou cursor), permitindo navegar entre páginas de resultado
* Autenticação real para `x-api-key`
* Melhor tratamento global de exceções
* Cache para contagem de cliques
