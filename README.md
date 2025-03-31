# 🏆 Torneios API

API desenvolvida como parte de um projeto de pós-graduação, com o objetivo de gerenciar torneios esportivos, equipes,
jogadores e partidas. A aplicação é construída utilizando **Java 21**, **Spring Boot 3**, **PostgreSQL**, **JPA** e *
*Flyway**.

## 🚀 Funcionalidades

- Cadastro de torneios com diferentes formatos:
    - Eliminação Simples
    - Fase de Grupos
- Registro de equipes e jogadores
- Geração automática das partidas
- Atualização de resultados das partidas
- Consulta de dados com filtros e estrutura organizada

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.3
- Spring Data JPA
- PostgreSQL
- Flyway (migrations)
- Maven
- Lombok
- Swagger (Springdoc OpenAPI)

## 📁 Estrutura do Projeto

```bash
torneios-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br.com.ufg.joaoguedes.torneios_api/
│   │   │       ├── controllers/
│   │   │       ├── models/
│   │   │       ├── repositories/
│   │   │       ├── services/
│   │   │       └── enums/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/ # scripts Flyway
├── pom.xml
└── README.md
```

## 🧪 Como Executar

1. Clone o repositório:
    ```
   git clone https://github.com/UFG-Pos/torneio-api
    cd torneios-api
   ```
2. Configure o banco PostgreSQL e ajuste o application.properties:
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/torneios_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=none
    ```
3. Execute a aplicação utilizando a IDE IntelliJ.

## Autor

João Pedro José Santos da Silva Guedes - Pós-Graduação em TI - Universidade Federal de Goiás (UFG)