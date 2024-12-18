# Projeto Spring Boot - Sistema de Pautas e Votação

Este projeto é uma aplicação backend desenvolvida com Spring Boot, utilizando um banco de dados PostgreSQL em container Docker. O sistema permite a gestão de pautas, sessões e votos. A seguir estão as instruções detalhadas para rodar o ambiente local, executar a aplicação e realizar consultas básicas.

## Pré-requisitos

Antes de rodar a aplicação, é necessário garantir que as seguintes ferramentas estejam instaladas em seu ambiente de desenvolvimento:

1. **Java 17** - A versão 17 do Java deve estar instalada e configurada.

   - Verifique a instalação do Java:
     ```java -version```

2. **Maven** - Ferramenta de construção do projeto.

   - Verifique a instalação do Maven:
     ```mvn -version```

3. **Docker** - Para rodar o PostgreSQL em container.

   - Verifique a instalação do Docker:
     ```docker --version```

4. **PostgreSQL** - Banco de dados utilizado pela aplicação, que será configurado via Docker.

## Configuração do Docker para o PostgreSQL

A aplicação utiliza um banco de dados PostgreSQL que pode ser executado em um container Docker. O arquivo necessário para a configuração pode ser encontrado na pasta `postgres/`.

Você pode escolher entre dois métodos de execução:

### 1. Usando o script Bash

Na pasta `postgres/` do projeto, existe um arquivo chamado `docker.bash`. Para iniciar o PostgreSQL com o Docker usando esse arquivo, execute o seguinte comando no terminal:

```chmod +x postgres/docker.bash```
```./postgres/docker.bash```

Este script irá construir e rodar o container Docker com PostgreSQL.

### 2. Usando o Docker Compose

Alternativamente, você pode usar o arquivo `docker-compose.yml` na pasta `postgres/` para configurar o banco de dados. Para isso, execute o comando:

```docker-compose -f postgres/docker-compose.yml up -d```

Isso iniciará o PostgreSQL no Docker com a configuração adequada.

## Rodando a aplicação Spring Boot

Após configurar o PostgreSQL no Docker, você pode rodar a aplicação Spring Boot.

### 1. Clonando o repositório

Caso ainda não tenha feito isso, clone o repositório do projeto:

```git clone https://github.com/lealnetosena/sistema-de-votocao```
```cd seu-diretorio-do-projeto```

### 2. Instalando as dependências e rodando a aplicação

Use o Maven para compilar e executar a aplicação. No terminal, execute os seguintes comandos:

```mvn clean install```

```mvn spring-boot:run```

O comando `mvn clean install` irá compilar o projeto, baixar todas as dependências necessárias e gerar o arquivo executável. O comando `mvn spring-boot:run` inicia a aplicação Spring Boot.

Após isso, a aplicação estará disponível em http://localhost:8080.

### 3. Verificando o funcionamento da aplicação

Uma vez que a aplicação esteja rodando, você pode acessar os seguintes endpoints para testar seu funcionamento.

## Consultas Básicas

Aqui estão alguns exemplos de endpoints da API que você pode utilizar para interagir com a aplicação.

### 1. Obter todas as pautas

**GET** http://localhost:8080/api/pautas

Este endpoint retorna todas as pautas cadastradas no sistema. Exemplo de resposta:

```[
  {
    "id": 1,
    "descricao": "Pauta 1"
  },
  {
    "id": 2,
    "descricao": "Pauta 2"
  }
```

### 2. Cadastrar uma Sessão

**POST** http://localhost:8080/api/sessao

Para cadastrar uma nova sessão de votação, envie o seguinte corpo no formato JSON:

```
{
  "pautaId": 1,
  "tempoMinutos": 2
}
```

- `pautaId`: ID da pauta para a qual a sessão será criada.
- `tempoMinutos`: Tempo de duração da sessão em minutos.

Exemplo de resposta (sessão criada com sucesso):

```
{
  "id": 1,
  "pautaId": 1,
  "tempoMinutos": 2,
  "status": "Aberta"
}
```

### 3. Cadastrar um Voto

**POST** http://localhost:8080/api/voto

Para cadastrar um voto em uma sessão, envie o seguinte corpo no formato JSON:

```
{
  "pautaId": 1,
  "sessaoId": 6,
  "voto": 1
}
```

- `pautaId`: ID da pauta na qual o voto será registrado.
- `sessaoId`: ID da sessão onde o voto foi dado.
- `voto`: O valor do voto (1 para "sim", 0 para "não").

Exemplo de resposta (voto registrado com sucesso):

```
{
  "id": 1,
  "pautaId": 1,
  "sessaoId": 6,
  "voto": 1
}
```

## Considerações Finais

- O banco de dados PostgreSQL estará disponível em `localhost:5432` com as credenciais padrão.
- As configurações de conexão do banco de dados podem ser verificadas no arquivo `application.properties` ou `application.yml` dentro da aplicação.
- Após finalizar a utilização da aplicação, lembre-se de parar o container Docker com o seguinte comando:

```docker-compose -f postgres/docker-compose.yml down```

Ou, se estiver utilizando o script Bash:

```./postgres/docker.bash stop```

Com isso, você estará pronto para executar a aplicação e realizar as interações necessárias com os endpoints da API. Caso tenha mais dúvidas ou questões, consulte a documentação oficial do Spring Boot e Docker.