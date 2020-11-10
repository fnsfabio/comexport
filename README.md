# Teste COMEXPORT

### Objeto
Expor o conhecimento para avaliação com base na STACK tecnológica proposta

* Utilização de REST
* Aplicação de JUNIT
* Utilização de banco de dados MYSQL
    * Sugestão de procedimento:
        * Execução do MYSQL via CONTAINER: docker run --name comexportdb -e MYSQL_ROOT_PASSWORD=qwerzxcv -d mysql:5.6.50
        * Acessar o CONTAINER para criação do DATABASE: comexportdb
        * Verificar configuração do container e URL de conexão com o banco: docker inspect --format '{{ .NetworkSettings.IPAddress }}' [id do CONTAINER]
* Acesso via API SWAGGER
    * http://localhost:8080/swagger-ui.html
* Segurança utilizando SPRING BOOT SECURITY
    * Usuário ROLE_USER: rose, senha: rose
    * Usuário ROLE_ADMIN: frank, senha: frank