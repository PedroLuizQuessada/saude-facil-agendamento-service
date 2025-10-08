# pos-fiap-tech-challenge
Projeto desenvolvido ao longo do curso "Arquitetura e Desenvolvimento em Java" de pós graduação da FIAP.

# Objetivo
API para um serviço de agendamento de consultas. Containerização do projeto via Docker e Docker Compose. Persistência de dados via MySQL.

# Documentação interativa
Swagger disponível através do link: http://localhost:8080/saude-facil-agendamento-service/swagger-ui/index.html#/

# Collection do Postman para testes
Disponível através do arquivo: tech-challenge.postman_collection.json

# Execução
- clonar repositório do projeto
- acessar o diretório do projeto clonado via CMD
- executar o comando “docker compose up -d” via CMD

Obs.: Para executar a aplicação via IDE é necessário descomentar as propriedades spring.datasource.url, spring.datasource.username e spring.datasource.password no arquivo application-jpa.properties.<br>
Obs2.: Em um ambiente corporativo, os diretórios "secrets" e "keys" seriam adicionados no .gitignore, porém como o projeto possui caráter educacional, optou-se por mantê-los versionados para garantir maior praticidade para execução do serviço.

Ao finalizar o passo a passo a aplicação estará disponível na porta 8080 do localhost (http://localhost:8080/saude-facil-agendamento-service), seu banco de dados estará disponível na porta 3306 do localhost e a plataforma web de gerenciamento do RabbitMQ estará disponível na porta 15672 do localhost (http://localhost:15672/#/).
