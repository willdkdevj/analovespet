# Aplicação REST utilizando Spring3
O objetivo é usar o Spring Boot para desenvolver uma API Rest invocando as operações de CRUD utilizando o framework Spring na versão 3

[![Spring Badge](https://img.shields.io/badge/-Spring-greenlight?style=flat-square&logo=Spring&logoColor=white&link=https://maven.apache.org/)](https://spring.io/)
[![Maven Badge](https://img.shields.io/badge/-Maven-black?style=flat-square&logo=Apache-Maven&logoColor=white&link=https://maven.apache.org/)](https://maven.apache.org/)
[![JPA Badge](https://img.shields.io/badge/-JPA-blue?style=flat-square&logo=GitHub&logoColor=white&link=https://docs.jboss.org/author/display/AS71/JPA%20Reference%20Guide.html)](https://docs.jboss.org/author/display/AS71/JPA%20Reference%20Guide.html)
[![Hibernate Badge](https://img.shields.io/badge/-Hibernate-green?style=flat-square&logo=Hibernate&logoColor=white&link=https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/)](https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/)
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=status&message=Em%20Desenvolvimento&color=GREEN&style=flat-square)

## Indice

*   [Por que utilizar a especificação JPA](#por-que-utilizar-a-especificação-jpa)
*   [O framework Hibernate](#o-framework-hibernate)
*   [A Especificação JPA](#a-especificação-jpa)
*   [Hibernate + JPA](#hibernate--jpa)
*   [Projeto Maven e suas dependências](#projeto-maven-e-suas-dependências)
    *   [O Arquivo Persistence (XML)](#o-arquivo-persistence-xml)
    *   [Entidades (Entity)](#entidades-entity)
    *   [Transaction (Camada DAO)](#transaction-camada-dao)
*   [Relacionamentos Entre Tabelas (Mapeamento)](#relacionamentos-entre-tabelas-mapeamento)
*   [Ciclo de Vida das Entidades](#ciclo-de-vida-das-entidades)
*   [Consultas (JPQL)](#consultas-jpql)
*   [Ansioso (EAGER) e Preguiçoso (LAZY)](#ansioso-eager-e-preguiçoso-lazy)
*   [Criteria API](#criteria-api)


## Objetivos
O objetivo é utilizar o Spring Boot para desenvolver uma API Rest, com algumas funcionalidades. O intuito é criar um **CRUD**, sendo as quatro operações fundamentais das aplicações: cadastro, listagem, atualização e exclusão de informações. Também como será aplicado validações das informações que chegam na API, usando o **Bean Validation**. Além disso, será utilizado o conceito de paginação e ordenação das informações que a API retornará ao solicitante.
for-the-badge

### Objetivos Principais
*	Desenvolvimento de uma API Rest
*	CRUD (Create, Read, Update e Delete)
*	Validações
*	Paginação e ordenação

![Buscar Por Nome](https://github.com/willdkdevj/analovespet/blob/master/assets/spring3.jpg)


## Tratamento de Requisições - Dados Recebidos (Formulário)
As requisições que chegarem a API podem conter informações em seu corpo, comumente encaminhadas através de uma requisição do tipo *POST*, desta forma, trataremos estes dados enviados através do recurso de **Record** (*disponível na versão do Java15*). Este recurso funciona como se fosse uma classe imutável, para facilitar o dado que é trafegado através do conceito do *Data Transfer Object - DTO*, tornando o código mais legível e simples. Já nos retornando todas as funcionalidades comuns nos POJO's tradicionais, pois seria necessário digitarmos os métodos getters e setters, criar construtor, e todas as outras verbosidades do Java que é suprimida com o uso do *Record*.

### Validação de Conteúdo (Validation-Constraints)
Foi aplicado também a dependência do jakarta.validation.constraints justamente para validar os dados que são recebidos pela API. Desta forma, a partir de sua implementação foi utilizadas anotações em nossas classes *Record* para checagem dos valores recebidos.
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
```
As anotações utilizadas foram:
*   NotNull - Verifica se o valor atribuído ao atributo está nulo;
*   NotBlank - Verifica se o valor atribuido ao atributo está nulo ou em branco;
*   Email - Valida se o valor fornecido ao atributo condiz com a sintaxe de e-mail;
*   Pattern - Verifica se o valor atribuido ao atributo sem as características fornecidas pelo regex;
*   Valid - Informa que o atributo também é uma classe DTO e que também precisa ser validada;

## Persistência com Hibernate (JPA/MySQL)
Foi utilizado o banco de dados **MySQL** para armazenar as informações da API e junto com ele foi implementado a biblioteca Flyway a fim de obter o controle do histórico de evolução do banco de dados, um conceito que é denominado *Migration*. Para isso, foi acrescentado a dependência do JPA, MySQL e do Flyway além de utilizar o **application.properties** a fim de passar as credenciais do banco.
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-mysql</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
```

```java
    spring.datasource.url=jdbc:mysql://localhost:3306/analovespet
    spring.datasource.username=root
    spring.datasource.password=root
```
A camada de persistência da aplicação será feita com a **JPA** (Java Persistence API), com o ***Hibernate*** como implementação dessa especificação e usando os módulos do Spring Boot, para tornar esse processo o mais simples possível.

``` xml
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-mysql</artifactId>
    </dependency>
```
Para cada mudança que quisermos executar no banco de dados, precisamos criar um arquivo com a extenção ***sql*** no projeto e, nele, escrever o trecho do comando SQL que será executado no banco de dados.

Também é necessário salvá-los em um diretório específico, na pasta em "main > resources". Com o atalho, "Alt + Insert", selecionamos a opção "Directory" e digitamos o nome da pasta: ***"db/migration"***.

> NOTA: é importante sempre parar o projeto ao criar os arquivos de migrations, para evitar que o Flyway os execute antes da hora, com o código ainda incompleto, causando com isso problemas.

Esse tipo de arquivo sempre começará com "V", seguido pelo número que repersenta a ordem de criação dos arquivos e, depois de dois underlines, um nome descritivo.

Será necessário atribuir mais uma anotação ao método do Controller, o @Transactional do **org.springframework.transaction.annotation**. Como esse é um método de escrita, que consiste em um *insert* no banco de dados, precisamos ter uma transação ativa com ele.

### Conteainer de Banco de Dados (MySQL)
Foi utilizado o container **Docker** para disponíbilidar um sistema de gerenciamento de dados **MySQL** para ser utilizado pela aplicação. Desta forma, através do comando abaixo foi disponibilizado a versão *lastest* do SGBD, na qual foi mapeada a porta 3306 do container com a porta 3306 do sistema hospedeiro. Além de fornecido um nome ao contairner a fim de ser identificado com a aplicação através da tag *--name* **mysql-analovespet**.
```bash
    docker run -e MYSQL_ROOT_PASSWORD=root --name mysql-analovespet -d -p 3306:3306 mysql
```

A partir daí foi acessado o container em execução para criar uma instância de *database* a fim de alocar as tabelas a serem migradas pelo *Flyway*, com os seguintes comandos:

```bash
    docker exec -it mysql-analovespet /bin/bash
```
> Comando executado para acessar o terminal do container

```bash
    mysql -u root -p
```
> Comando executado para acessar o SGBD (MySQL)

```bash
    CREATE DATABASE analovespet;
```
> Comando (SQL) a fim de criar uma database para armazenar as tabelas

## Dados Retornados (Pageable)


> É possível traduzir ou nomear de outra forma os parâmetros do Page para o que acharmos necessários ao passa-los no arquivo de configuração ***application.properties***

```java
    spring.data.web.pageable.page-parameter=pagina
    spring.data.web.pageable.size-parameter=tamanho
    spring.data.web.sort.sort-parameter=ordem
```

## Ferramenta para Teste (Insomnia)
Mas para testarmos a API, usaremos o Insomnia, sendo uma ferramenta usada para testes em API. Com ela, conseguimos simular a requisição para a API e verificar se as funcionalidades implementadas estão funcionando.
