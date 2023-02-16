# Aplicação REST utilizando Spring3
O objetivo é usar o Spring Boot para desenvolver uma API Rest invocando as operações de CRUD utilizando o framework Spring na versão 3

[![Spring Badge](https://img.shields.io/badge/-Spring-greenlight?style=flat-square&logo=Spring&logoColor=white&link=https://maven.apache.org/)](https://spring.io/)
[![Maven Badge](https://img.shields.io/badge/-Maven-black?style=flat-square&logo=Apache-Maven&logoColor=white&link=https://maven.apache.org/)](https://maven.apache.org/)
[![JPA Badge](https://img.shields.io/badge/-JPA-blue?style=flat-square&logo=GitHub&logoColor=white&link=https://docs.jboss.org/author/display/AS71/JPA%20Reference%20Guide.html)](https://docs.jboss.org/author/display/AS71/JPA%20Reference%20Guide.html)
[![Hibernate Badge](https://img.shields.io/badge/-Hibernate-green?style=flat-square&logo=Hibernate&logoColor=white&link=https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/)](https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/)

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=status&message=Em%20Desenvolvimento&color=GREEN&style=flat-square)

## Indice

*   [Objetivos](#objetivos)
    *   [Objetivos Principais](#objetivos-principais)
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

> NOTA: Caso ocorra algum problema no versionamento de scripts pelo flyway, ele gera um registro apontando a sua não execução em sua tabela, então, após corrigir o problema será necessário excluir o registro para rodar novamente o projeto. Desta forma execute o comando *DELETE FROM flyway_schema_history WHERE success = 0;*


### Container de Banco de Dados (MySQL)
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

## Dados Retornados 
Para retornarmos dados foram utilizados objetos apropriados para esta exposição para evitar de expor dados desnescessários ou inapropriados. Desta forma, foram criados objetos DTO com o Record e com Page.
Com o Record já sabemos que ele é imutável e só apresenta os dados que acharmos pertinente. Já o Page fornece alguns elementos a fim do recebitador manuseie como achar necessário.

No método listar() do controller inserimos como parâmetro um Pageable com os seguintes paràmetros.
```java
    @GetMapping
    public ResponseEntity<Page<DadosListagemVeterinario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemVeterinario::new);

        return ResponseEntity.ok(page);
    }
```
Começando pelo parâmetro do método, a anotação **PageableDefault** nos permite customizar como será o retorno da página ao usuário, deste modo, o parâmetro size= 10 informa que será retornado 10 registro por página, já o parâmetro sort = {"nome"} ordena os registro através do valor obtido em nome.
Já a parametrização do método findAll() existe a implementação que recebe um objeto do tipo Pabeable na qual mapeamos um objeto DTO a partir do objeto de retorno do banco de dados e criamos um construtor neste DTO a fim que a referência ao método ***DadosListagemVeterinario::new*** funcione conforme esperamos para devolver o objeto **Page**.

> É possível traduzir ou nomear de outra forma os parâmetros do Page para o que acharmos necessários ao passa-los no arquivo de configuração ***application.properties***

```java
    spring.data.web.pageable.page-parameter=pagina
    spring.data.web.pageable.size-parameter=tamanho
    spring.data.web.sort.sort-parameter=ordem
```

Já para atualizar um registro podemos manipular os dados obtidos pelo banco de dados e verificar se os dados fornecidos pela classe *Record* estão presentes, para só neste caso, realizar a atualização do registro. Para isso implementamos o seguinte método no controller:

```java
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoVeterinario formAtualizar){
        var atualizarRegistro = repository.getReferenceById(formAtualizar.id());
        atualizarRegistro.validar(formAtualizar);

        return ResponseEntity.ok(new DadosDetalheVeterinario(atualizarRegistro));
    }
```
Com o método **getReferenceById** é obtida uma instância do registro da entidade (Veterinario) no banco, e a partir daí passamos os dados fornecidos no formulário para valida-lo antes de modificar o registo. E só esta etapa basta para atualizar o registro no banco de dados.

Para excluir um registro não será realizado o processo físico de eliminar um registro persistido em uma tabela de dados. O que será realizado será a exclusão lógica, na qual um parâmetro booleano informa se o registro está ativo ou não para ser retornado ao solicitante. Desta forma, foi modificada a tabela *veterinarios* a fim de inserir um novo parãmetro, denominado ativo, onde:
*   1 o registro pode ser retornado;
* E 0 o registro não pode ser retornado;

Este processo foi possível através do versionamento por script SQL realizado pelo Flyway. Desta forma, a classe de controller foi adicionado o seguinte método.
```java
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        // repository.deleteById(id); // Excluir o registro físico do banco de dados
        var registro = repository.getReferenceById(id);
        registro.inativar();
        return ResponseEntity.noContent().build();
    }
```

Assim toda vez que for selecionado a exclusão de um registro, ao passa-lo como parâmetro, deste será averiguado sua existência no banco de dados e caso encontrado, será alterado seu atributo **ativo** para *false*. Desta forma, foi modificada a instância também do objeto *Veterinario* que agora em seu construtor insere o valor *true* no parâmetro ativo ao cria-lo.

## Tratamento de Erros
O próprio Spring Boot nos ajuda para tratar como é devolvida a stack para o cliente. Normalmente, é retornado os seguintes parâmetros na stack:
*   Timestamp - Informa quando ocorreu o erro apresentando data e hora;
*   Status - Numeral que representa o código HTTP para aquele problema encontrado;
*   Error - Informa o tipo de erro encontrado conforme o código de status;
*   Trace - Informa todo o caminho realizado até encontrar o erro;
*   Message - Apresenta uma possível solução ou qual foi a origem do problema.

Desta forma, vemos como são muitas as informações retornadas, e muitas vezes não queremos que o nosso cliente final tenha ciência de toda esta informação. Principalmente o *trace* que não ajudará em nada o cliente sobre o problema ocorrido. Assim, podemos omiti-lo do retorno ao acrescentar no **application.properties** a seguinte linha:

```xml
    server.error.include-stacktrace=never
```

Por padrão, exceções não tratadas no código são interpretadas pelo **Spring Boot** como erro 500. O correto era utilizarmos a estrutura de *try/catch* para validar nosso código e tratar possíveis erros que venham a ocorrer, mas ao invés de duplicarmos o try-catch no código, podemos usar outro recurso do Spring para isolar esse tipo de tratamento de erros. 

Neste projeto, foi utilizada uma classe para concentrar os tratamentos e os retornos a serem retornado ao cliente utilizando o recurso do Spring, através da anotação ***RestControllerAdvice***.
```java
    @RestControllerAdvice
    public class ErrorHandling {
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity error404(){
            return ResponseEntity.notFound().build();
        }
    }
```

Assim, temos o tratamento do erro 500 - feito pelo próprio Spring, nós somente configuramos no application.properties - e a classe que trata o erro 404. Podemos ter mais métodos tratando outros códigos de erros.

Agora o erro 400, este erro indica que o servidor não conseguiu processar uma requisição por erro de validação nos dados enviados pelo cliente. 
Para trata-lo foi utilizada a seguinte estrutura na qual utiliza um objeto *Record* para auxilia-la na apresentação de uma mensagem ao cliente.
```java
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error404(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationError::new).toList());
    }
    
    private record ValidationError(String campo, String mensagem){
        public ValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
```
Desta forma, caso seja enviada uma requisição que não contenha um dos parâmetros exigidos para a requisição será apresentado o campo e uma mensagem informando sobre sua necessidade.

## Ferramenta para Teste (Insomnia)
Mas para testarmos a API, usaremos o Insomnia, sendo uma ferramenta usada para testes em API. Com ela, conseguimos simular a requisição para a API e verificar se as funcionalidades implementadas estão funcionando.
