# Aplicação REST utilizando Spring3
O objetivo é usar o Spring Boot para desenvolver uma API Rest invocando as operações de CRUD utilizando o framework Spring na versão 3

[![Maven Badge](https://img.shields.io/badge/-Maven-black?style=flat-square&logo=Apache-Maven&logoColor=white&link=https://maven.apache.org/)](https://maven.apache.org/)
[![JPA Badge](https://img.shields.io/badge/-JPA-blue?style=flat-square&logo=GitHub&logoColor=white&link=https://docs.jboss.org/author/display/AS71/JPA%20Reference%20Guide.html)](https://docs.jboss.org/author/display/AS71/JPA%20Reference%20Guide.html)
[![Hibernate Badge](https://img.shields.io/badge/-Hibernate-green?style=flat-square&logo=Hibernate&logoColor=white&link=https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/)](https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/)
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

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

### Objetivos Principais
*	Desenvolvimento de uma API Rest
*	CRUD (Create, Read, Update e Delete)
*	Validações
*	Paginação e ordenação




![Buscar Por Nome](https://github.com/willdkdevj/jpa_hibernate/blob/master/assets/buscarPrecoPorNome.png)


## Tratamento de Requisições - Dados Recebidos (Formulário)
As requisições que chegarem a API podem conter informações em seu corpo, comumente encaminhadas através de uma requisição do tipo *POST*, desta forma, trataremos estes dados enviados através do recurso de **Record** (*disponível na versão do Java15*). Este recurso funciona como se fosse uma classe imutável, para facilitar o dado que é trafegado através do conceito do *Data Transient Object - DTO*, tornando o código mais legível e simples. Já nos retornando todas as funcionalidades comuns nos POJO's tradicionais, pois seria necessário digitarmos os métodos getters e setters, criar construtor, e todas as outras verbosidades do Java que é suprimida com o uso do *Record*.

## Persistência com Hibernate (JPA/MySQL)
Usaremos o banco de dados MySQL para armazenar as informações da API e junto com ele utilizaremos a biblioteca Flyway. Isso para termos o controle do histórico de evolução do banco de dados, um conceito que chamamos de Migration.
A camada de persistência da nossa aplicação será feita com a JPA (Java Persistence API), com o Hibernate como implementação dessa especificação e usando os módulos do Spring Boot, para tornar esse processo o mais simples possível.



## Ferramenta para Teste (Insomnia)
Mas para testarmos a API, usaremos o Insomnia, sendo uma ferramenta usada para testes em API. Com ela, conseguimos simular a requisição para a API e verificar se as funcionalidades implementadas estão funcionando.
