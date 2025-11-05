API de Coleta de Resíduos – ESG

Projeto Spring Boot usado como trabalho acadêmico para demonstrar:

autenticação com JWT (login e registro);

controle de usuários com roles (ADMIN e USER);

cadastro e consulta de pontos de coleta (endereço + material);

integração com Docker;

migrações de banco de dados com Flyway.

Este documento mostra como construir e executar o projeto em um contêiner Docker e onde ficam os scripts de migração.

1. Pré-requisitos

Docker Desktop instalado e em execução.

O arquivo src/main/resources/application.properties deve estar configurado com o banco Oracle da faculdade (URL, usuário e senha).

Exemplo de application.properties (ajuste para o seu RM/senha):

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521/ORCL
spring.datasource.username=RM560930
spring.datasource.password=SUASENHA
spring.jpa.hibernate.ddl-auto=none
spring.flyway.enabled=true


Com isso, o container já sabe como conectar no banco sem precisar de variáveis de ambiente.

2. Build da imagem Docker

Na raiz do projeto (onde estão pom.xml e Dockerfile), execute:

docker build -t coleta-residuos-api .


O que esse comando faz:

baixa uma imagem com Maven e Java;

compila o projeto;

gera uma imagem final só com o .jar.

3. Executando o contêiner

Como o datasource já está no application.properties, o comando de execução fica simples:

docker run --name coleta-residuos-api -p 8080:8080 coleta-residuos-api


Depois que o container subir, a API estará disponível em:

http://localhost:8080

4. Migrações de banco (Flyway)

Os scripts de criação/alteração de tabelas ficam em:

src/main/resources/db/migration


Inclua no .zip os arquivos, por exemplo:

V1__create_tbl_users.sql

V2__create_tbl_collection_points.sql

V3__alter_default_user_role.sql

Quando a aplicação inicia dentro do contêiner, o Flyway executa essas migrações automaticamente e cria/atualiza as tabelas necessárias.

5. Endpoints principais

Dependendo dos nomes que você usou nos controllers, os endpoints ficam assim:

POST /auth/register – cadastrar usuário

POST /auth/login – autenticar e obter o token JWT

GET /api/pontos (ou /api/collection-point) – listar pontos de coleta

GET /api/pontos/material/{material} – listar por material

POST /api/pontos – criar ponto de coleta (rota geralmente protegida, só ADMIN)

Use o token JWT no header:

Authorization: Bearer <seu_token>