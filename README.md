# ExpensesApp

Aplicación web para gestionar usuarios y gastos, desarrollada como parte del curso de Spring Boot. Permite realizar operaciones CRUD sobre usuarios y gastos, generar reportes parametrizados, 
y un recordatorio de contraseña por email. La autenticación se ha simplificado para permitir acceso con cualquier usuario registrado en la base de datos.

## Tecnologías Utilizadas
- **Backend**: Spring Boot, Spring Data JPA
- **Base de Datos**: PostgreSQL
- **Frontend**: Thymeleaf, Bootstrap 5
- **Otros**: JavaMailSender para emails

## Requisitos Previos
- Java 17 o superior
- Maven 3.6 o superior
- PostgreSQL 13 o superior
- Servidor SMTP (por ejemplo, Gmail) para el envío de emails
- Git

Configura la base de datos:
Crea una base de datos en PostgreSQL llamada expenses_db.
Ejecuta el script database.sql ubicado en la raíz del proyecto para crear las tablas e insertar datos iniciales.
Configura las propiedades:
Copia application.properties.example a src/main/resources/application.properties.

**********************************************************************************
spring.datasource.url=jdbc:postgresql://localhost:5432/expenses_db
spring.datasource.username=postgres
spring.datasource.password=su_contraseña
spring.jpa.hibernate.ddl-auto=validate
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tu_email@gmail.com
spring.mail.password=tu_contraseña_de_app
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

**************************************************************************
Accede a la aplicación:
Abre un navegador y ve a http://localhost:8080/login.
Ingresa un usuario existente 



-- Crear tablas
CREATE TABLE usuario (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE gastos (
    id BIGSERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    valor_total_sin_iva DECIMAL(10,2) NOT NULL,
    iva_total DECIMAL(10,2) NOT NULL,
    valor_total_con_iva DECIMAL(10,2) NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL,
    lugar VARCHAR(100),
    descripcion TEXT,
    FOREIGN KEY (nombre_usuario) REFERENCES usuario(username)
);

Funcionalidades

- Login simplificado: Ingresa un username registrado (por ejemplo, user1, newuser2) para acceder.
- CRUD de usuarios: Crear, leer, actualizar y eliminar usuarios en /usuarios/list.
- CRUD de gastos: Crear, leer, actualizar y eliminar gastos en /gastos/list.
- Reportes parametrizados:
  - Usuarios por dominio de email (/usuarios/report/email?domain=gmail.com).
  - Gastos por rango de fechas (/gastos/report/fecha?startDate=2025-06-01&endDate=2025-06-03).
  - Gastos por usuario (/gastos/report/usuario?nombreUsuario=user1).
- Recordatorio de contraseña: Enviar un enlace simulado de restablecimiento por email (/forgot-password).


1. Clona el repositorio: git clone https://github.com/tu-usuario/ExpensesApp.git
2. Crea una base de datos en PostgreSQL llamada expenses_db y ejecuta database.sql.
3. Configura application.properties con las credenciales de PostgreSQL y SMTP.
4. Compila y ejecuta: mvn clean install && mvn spring-boot:run
5. Accede a http://localhost:8080/login e ingresa un usuario existente.
