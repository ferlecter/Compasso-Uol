# ProductMS

Desenvolvimento de teste tecnico para vaga Compasso UOL

## MongoDB
Foi utilizado MongoDB como banco de dados NoSQL

Para instalar o MongoDB baixar no link [MongoDB](https://docs.mongodb.com/manual/administration/install-community/) . Escolher a versao para o Sistema Operacional e a arquitetura que o responsavel pela analise utiliza.

Para alterar o apontamento acessar /resource/application.properties

```bash
spring.data.mongodb.database=compassouol_db
spring.data.mongodb.port=27017
```
## Maven
O projeto utilizou Maven para gerenciamento de dependencias.

limpar e construir, entrar na pasta e executar os comandos
```bash
mvn clean install
```

## Spring Boot

Feito como Jar , para executar, entrar na pasta onde foi construido a aplicacao, geralmente em target utilizar o comanado abaixo

```bash
java -jar  ProductMS-0.0.1-SNAPSHOT.jar
```


