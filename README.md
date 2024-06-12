# Desafio Votação - Luiz Cardoso

## Objetivo

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução para dispositivos móveis para gerenciar e participar dessas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por
  um tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado
  é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabilizar os votos e dar o resultado da votação na pauta

## Stack

* Spring Boot 3.3.0
* Spring Web Flux
* MongoDB Reactive
* Java 17


## Execute localmente

Este é um aplicativo [Spring Boot](https://spring.io/guides/gs/spring-boot) 

Você pode criar um arquivo jar e executá-lo na linha de comando (deve funcionar igualmente bem com Java 17 ou mais recente):

```bash
git clone https://github.com/luizcardosodev/desafio-votacao
cd desafio-votacao
./mvn package
java -jar .\target\bird-service-0.0.1-SNAPSHOT.jar 
```

Você pode acessar <http://localhost:8080/>.

Documentação do Swagger: https://localhost:8080/swagger-ui

### Pré-requisitos

Os seguintes itens devem ser instalados em seu sistema:

- Java 17 ou mais recente (full JDK, not a JRE)
- [Git command line tool](https://help.github.com/articles/set-up-git)
- Seu IDE preferido

  - [Spring Tools Suite](https://spring.io/tools) (STS)
  - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  - [VS Code](https://code.visualstudio.com)

