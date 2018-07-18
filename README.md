# Teste de Backend TEXOIT

Este é meu teste de Backend realizado exclusivamente para a TEXOIT.

## Pré-requisitos

* Java 1.8 ou superior
* Maven 3.5 ou superior

## Criado com

* Eclipse
* Spring Boot e JPA
* Maven
* Base de dados SQLite

## Arquivos de configuração

* Maven: pom.xml
* Spring: application.properties

## Instalação de dependências do projeto

Para baixar as depedências do projeto basta executar o comando a seguir no diretório raíz do mesmo.

```
mvn clean install
```

## Executando a aplicação

Basta executar o arquivo App.java, que o Spring Boot irá se encarregar do restante, ou executar o seguinte comando na pasta raíz do projeto.

```
mvn spring-boot:run
```

## Deploy da aplicação

Para criar o deploy da aplicação execute o seguinte comando na pasta raíz do projeto.

```
mvn package
```

Para executar o deploy utilize o seguinte comando na pasta raíz do projeto.

```
java -jar target/xpto-0.0.1-SNAPSHOT.jar
```
