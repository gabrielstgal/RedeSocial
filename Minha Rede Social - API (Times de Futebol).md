# **Minha Rede Social \- API (Times de Futebol)**

## **Descrição do Projeto**

Esta API gerencia o ciclo de vida completo de uma rede social, focada em interações de usuários (posts, amizades, curtidas, comentários). A arquitetura é baseada em Spring Boot, utilizando o padrão de design limpo (Controller \-\> Service \-\> Repository) com segurança JWT e validações centralizadas.

## **Requisitos Técnicos**

O projeto exige a seguinte stack para execução:

* **Java 17**  
* **Apache Maven**  
* **Banco de dados PostgreSQL**  
* **Frameworks:** Spring Boot, Spring Data JPA, Spring Security (OAuth2/JWT).

  ## **Configuração do Banco de Dados**

1. **Criação do Banco:** O nome do banco de dados utilizado é RedeSocial. Você deve criá-lo no seu PostgreSQL.  
2. **Scripts Iniciais:** A API requer que as tabelas sejam criadas antecipadamente. Execute o conteúdo do arquivo data.sql (que contém o CREATE TABLE para usuario, amizade, postagem, etc.) na query do seu DB RedeSocial.  
3. **Configuração de Credenciais:** Configure suas credenciais no arquivo src/main/resources/application.yml.  
   * Exemplo de configuração: url: jdbc:postgresql://localhost:5432/RedeSocial, username: SEU\_USUARIO, password: SUA\_SENHA.

   ## **Instalação e Execução**

Para iniciar o servidor da API:

1. **Instalação de Dependências:** Acesse a pasta do projeto da API e utilize o Maven: mvn clean install.  
2. **Execução da API:** Inicie o Spring Boot: mvn spring-boot:run.  
3. A API estará disponível em http://localhost:8080.

   ## **Endpoints Principais (API)**

Todos os endpoints estão mapeados para o caminho base /usuario ou /postagens. Rotas protegidas exigem o cabeçalho Authorization: Bearer \<JWT Token\>.

* **POST /usuario** — Cadastro de novo usuário.  
* **POST /login** — Realiza a autenticação e retorna o token JWT.  
* **GET /usuario/me** — Exibe os dados do perfil do usuário logado (Requisito 3).  
* **PUT /usuario/me** — Edita o nome, apelido e imagem de perfil (Requisito 7).  
* **GET /usuario?text={termo}** — Busca usuários para contato por nome ou e-mail (Requisito 4).  
* **GET /usuario/amigos** — Lista todos os amigos com status ACEITA (Requisito 5).  
* **GET /usuario/solicitacoes** — Lista os pedidos de amizade recebidos (Requisito 3).  
* **POST /usuario/{idSolicitado}** — Envia solicitação de amizade.  
* **POST /usuario/aceitar/{idSolicitante}** — Aceita o pedido de amizade.  
* **DELETE /usuario/rejeitar/{idSolicitante}** — Rejeita o pedido de amizade.  
* **DELETE /usuario/amigos/{idAmigo}** — Desfaz uma amizade existente (Requisito 5).  
* **POST /postagens** — Inclui uma nova postagem (Requisito 3).  
* **GET /postagens** — Lista o feed principal (próprio \+ amigos \+ públicos), paginado e ordenado (Requisito 3).  
* **PUT /postagens/{idPostagem}** — Altera a permissão (Público/Privado) de uma postagem existente.  
* **POST /postagens/{idPostagem}/curtir** — Adiciona uma curtida.  
* **DELETE /postagens/{idPostagem}/curtir** — Remove uma curtida.  
* **POST /postagens/{idPostagem}/comentarios** — Adiciona um novo comentário.

  ## **Observações Importantes**

* **Segurança:** A aplicação usa **JWT** e o SecurityConfig garante que a maioria das rotas esteja protegida.  
* **Validação:** Todas as regras de negócio foram extraídas dos services para a camada validator para fins de organização e testes.  
* **Faltantes:** Para o Requisito 5 e Requisito 6 serem 100% completos, a API ainda precisa implementar o **filtro de busca** na rota GET /usuario/amigos?text=... e o fluxo de **visualização de perfil de terceiros** (GET /usuario/{idUsuario}).  
* 

