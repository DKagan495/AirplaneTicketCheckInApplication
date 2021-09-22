# AirplaneTicketCheckInApplication
Contains microservice for business and microservice for audit. Business-layer stack: Spring Boot, Data JPA, Security(JWT for authorization); ActiveMQ for messaging to audit-layer; DBMS: PostgreSQL(Relational) + Flyway Migration. Audit-layer stack contains Spring Boot, Data Mongo; DBMS: MongoDB(NoSQL).
