# 📋 Sistema de cadastro de Clientes

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.4-brightgreen?style=for-the-badge&logo=spring&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.2.2-purple?style=for-the-badge&logo=bootstrap&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)

*Um sistema web para gerenciamento de clientes desenvolvido com Spring Boot*

</div>

---

## 🎯 Sobre o Projeto

Este é um sistema de gestão de clientes que desenvolvi durante meus estudos em **Engenharia da Computação e do curso técnico de informática**. O projeto combina conceitos de desenvolvimento web, banco de dados e arquitetura de software.

Como estudante com formação técnica em **Informática**, busquei criar uma aplicação robusta que demonstra conhecimento em:

- **Backend**: Spring Boot, Spring Data JPA, Spring Security
- **Frontend**: Thymeleaf, Bootstrap 5, JavaScript
- **Banco de Dados**: H2 (desenvolvimento) e SQL Server (produção)
- **Documentação**: OpenAPI/Swagger
- **Autenticação**: JWT (JSON Web Tokens)

## 🚀 Funcionalidades

### ✨ Principais Recursos
- **CRUD Completo** de clientes
- **Sistema de busca** por nome, CPF ou email
- **Validação de dados** com Bean Validation
- **Interface responsiva** com Bootstrap
- **API REST** documentada com Swagger
- **Autenticação JWT** para endpoints da API
- **Mensagens de feedback** para o usuário

### 📊 Dados dos Clientes
- Nome completo
- CPF 
- Data de nascimento
- Email (com validação)
- Cálculo automático da idade

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem principal
- **Spring Boot 2.7.4** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Segurança da aplicação
- **Spring Validation** - Validação de dados
- **H2 Database** - Banco em memória (desenvolvimento)
- **SQL Server** - Banco de produção
- **JWT** - Autenticação stateless

### Frontend
- **Thymeleaf** - Engine de templates
- **Bootstrap 5.2.2** - Framework CSS
- **jQuery 3.6.1** - Manipulação DOM
- **HTML5/CSS3** - Estrutura e estilização

### Ferramentas
- **Maven** - Gerenciamento de dependências
- **Spring Boot Actuator** - Monitoramento
- **OpenAPI/Swagger** - Documentação da API

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/example/listadeclientes/
│   │   ├── api/                    # Controllers da API REST
│   │   ├── controller/             # Controllers Web (MVC)
│   │   ├── modelo/                 # Entidades JPA
│   │   ├── repository/             # Repositórios Spring Data
│   │   └── security/               # Configurações de segurança
│   └── resources/
│       ├── static/                 # Arquivos estáticos (CSS, JS, imagens)
│       ├── templates/              # Templates Thymeleaf
│       └── application.properties  # Configurações da aplicação
└── test/                           # Testes unitários
```

## 🚀 Como Executar

### Pré-requisitos
- **Java 17+**
- **Maven 3.6+**
- **Git**
- **SQL Server** (opcional - pode usar H2 para testes)

### 1. Clone o repositório
```bash
git clone https://github.com/RebeckaNigro/MVP-Clientes.git
cd listadeclientes
```

### 2. Configure o banco de dados
**IMPORTANTE**: Antes de executar, configure o banco de dados:

- **Para SQL Server**: Certifique-se de que está rodando e crie o banco `Clientes_estagio`
- **Para H2**: Não precisa configurar nada, será criado automaticamente

### 3. Execute a aplicação
```bash
# Usando Maven Wrapper (recomendado)
./mvnw spring-boot:run

# Ou usando Maven instalado
mvn spring-boot:run
```

### 4. Acesse a aplicação
- **Interface Web**: http://localhost:8081
- **API Swagger**: http://localhost:8081/swagger-ui.html
- **H2 Console**: http://localhost:8081/h2-console

## 📚 Endpoints da API

### Web Interface
- `GET /` - Página inicial
- `GET /cliente/listar` - Lista todos os clientes
- `GET /cliente/novo` - Formulário de novo cliente
- `POST /cliente/salvar` - Salva novo cliente
- `GET /cliente/editar/{id}` - Formulário de edição
- `POST /cliente/editar/{id}` - Atualiza cliente
- `POST /cliente/deletar/{id}` - Remove cliente
- `GET /cliente/pesquisar` - Busca clientes

### API REST
- `GET /api/cliente/listar` - Lista clientes (JSON)
- `POST /api/cliente/salvar` - Cria cliente
- `GET /api/cliente/{id}` - Busca cliente por ID
- `PUT /api/cliente/editar/{id}` - Atualiza cliente
- `DELETE /api/cliente/deletar/{id}` - Remove cliente

## 🎨 Interface

A interface foi desenvolvida com foco na **experiência do usuário**, utilizando:

- **Design responsivo** que funciona em desktop e mobile
- **Feedback visual** com mensagens de sucesso/erro
- **Confirmação** antes de excluir registros
- **Busca em tempo real** por múltiplos campos
- **Validação client-side** e server-side

## 🔧 Configurações

### Banco de Dados

A aplicação está configurada para usar **SQL Server** por padrão. Você tem duas opções:

#### Opção 1: SQL Server (Configuração Atual)
A aplicação já está configurada para usar SQL Server. Certifique-se de que:

1. **SQL Server está instalado e rodando** na porta 1433
2. **Crie o banco de dados**:
   ```sql
   CREATE DATABASE [Clientes_estagio]
   ```
3. **Configure as credenciais** (já configuradas no projeto):
   ```properties
   # application.properties
   spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=Clientes_estagio;encrypt=true;trustServerCertificate=true
   spring.datasource.username=sa
   spring.datasource.password=Ch@ll3ng3
   spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
   ```

#### Opção 2: H2 (Para Testes Rápidos)
Se preferir usar H2 (banco em memória), comente as linhas do SQL Server e descomente:

```properties
# application.properties
# Comentar/remover as linhas do SQL Server acima e usar:
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Segurança
A aplicação inclui **Spring Security** configurado para:
- Proteção CSRF
- Autenticação JWT para API
- Configuração de CORS

## 🚨 Troubleshooting

### Problemas Comuns

#### Erro de Conexão com SQL Server
```
com.microsoft.sqlserver.jdbc.SQLServerException: Login failed for user 'sa'
```
**Solução**: 
1. Verifique se o SQL Server está rodando
2. Confirme se o usuário `sa` está habilitado
3. Verifique se a senha `Ch@ll3ng3` está correta
4. Certifique-se de que o banco `Clientes_estagio` existe

#### Erro de Porta em Uso
```
Port 8081 was already in use
```
**Solução**: 
1. Pare outros serviços na porta 8081
2. Ou altere a porta no `application.properties`: `server.port=8082`

#### Problema com H2 Console
Se estiver usando H2 e não conseguir acessar o console:
1. Verifique se `spring.h2.console.enabled=true` está no `application.properties`
2. Acesse: http://localhost:8081/h2-console
3. Use as credenciais: JDBC URL: `jdbc:h2:mem:testdb`, User: `sa`, Password: (vazio)


## 📈 Próximos Passos

Como estudante em constante evolução, planejo implementar:

- [ ] **Docker** para containerização
- [ ] **CI/CD** com GitHub Actions


## 🤝 Contribuições

Este é um projeto de estudo, mas sugestões e melhorias são sempre bem-vindas! 

Se você tem ideias para:
- Melhorar a performance
- Adicionar novas funcionalidades
- Refatorar código
- Melhorar a documentação

Fique à vontade para abrir uma issue ou pull request!

## 📞 Contato

**Desenvolvedor**: [Rebecka de Lima Nigro]  
**Curso**: Engenharia da Computação  
**Formação Técnica**: Informática  

---

