# PetCare Management System

[![Java](https://img.shields.io/badge/Java-100%25-blue)](https://www.java.com/)
[![Build](https://img.shields.io/badge/Build-Maven-C71A36)](https://maven.apache.org/)
[![Architecture](https://img.shields.io/badge/Architecture-MVC-black)](#architecture)

## PT-BR

### Problema que resolve
O projeto resolve a desorganização de informações em rotinas de cuidado pet (tutores, pets, vacinas, pedidos e medicamentos), centralizando dados e melhorando controle e rastreabilidade.

### Diferenciais
- Estrutura MVC clara e escalável  
- Separação por módulos de negócio  
- Base pronta para evoluir para API REST e banco de dados  
- Desenvolvimento colaborativo com responsabilidades definidas

### Quem ajuda
- Clínicas veterinárias  
- Pet shops com atendimento clínico  
- Profissionais de cuidado animal  
- Tutores que precisam acompanhar histórico e vacinação dos pets

### Por que fizemos
Projeto acadêmico criado para aplicar Java, POO, arquitetura MVC e trabalho em equipe em um contexto realista de gestão pet.

### Equipe
- Camile Felix  
- Fabiana Souza  
- Erick Ferreira  
- Anna Beatriz  
- Emanoel Alexandri  

### Tecnologias
- Java  
- Maven  
- Arquitetura MVC

### Exemplos de uso
- Cadastro de tutores e pets  
- Registro e consulta de vacinas  
- Criação de pedidos com itens e medicamentos  
- Consulta de conteúdo educativo

### Uso de API (evolução)
Estrutura atual permite evolução para API REST, por exemplo:
- `GET /pets`
- `POST /tutores`
- `GET /vacinas/{petId}`
- `POST /pedidos`
- `GET /conteudos`

Tecnologias sugeridas para API: Spring Boot, Spring Web, Spring Data JPA, PostgreSQL/MySQL, Swagger/OpenAPI.

### Próximos projetos a partir deste
- Painel web para gestão clínica  
- App mobile para tutores  
- Notificações automáticas (vacinas/consultas)  
- Dashboard analítico  
- Versão multiunidade (várias clínicas)

### Melhorias recomendadas
- Persistência em banco de dados  
- Validações e tratamento de erros  
- Testes automatizados (unitários/integração)  
- Autenticação e controle de perfis  
- CI/CD para build e testes

---

## EN

### Problem solved
This project addresses fragmented pet care operations (tutors, pets, vaccines, orders, and medications) by centralizing data and improving operational traceability.

### Differentiators
- Clean and scalable MVC structure  
- Business-oriented module separation  
- Ready-to-evolve foundation for REST API and database integration  
- Collaborative development with clear ownership

### Who it helps
- Veterinary clinics  
- Pet shops with clinical workflows  
- Animal care professionals  
- Tutors needing better pet history and vaccine tracking

### Why we built it
An academic project designed to apply Java, OOP, MVC architecture, and teamwork in a realistic pet management scenario.

### Team
- Camile Felix  
- Fabiana Souza  
- Erick Ferreira  
- Anna Beatriz  
- Emanoel Alexandri  

### Technologies
- Java  
- Maven  
- MVC Architecture

### Usage examples
- Register tutors and pets  
- Record and view vaccination history  
- Create medication orders with items  
- Access educational pet-care content

### API usage (future-ready)
Current architecture can evolve into a REST API, e.g.:
- `GET /pets`
- `POST /tutors`
- `GET /vaccines/{petId}`
- `POST /orders`
- `GET /educational-content`

Suggested stack: Spring Boot, Spring Web, Spring Data JPA, PostgreSQL/MySQL, Swagger/OpenAPI.

### Next projects from this base
- Web dashboard for clinics  
- Mobile app for tutors  
- Automated reminders/notifications  
- Analytics dashboard  
- Multi-branch clinic platform

### Recommended improvements
- Database persistence  
- Stronger validation and error handling  
- Automated testing (unit/integration)  
- Authentication and role-based access  
- CI/CD pipeline

---

## Run
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="view.ProjetoFinal"
```
