# spring-boot
## Technical:

1. Spring Boot 3.1.0
2. Java 17
3. Thymeleaf
4. Bootstrap v.4.3.1
### Roles:

- ADMIN : Full privileges, but cannot delete own account or change own role.
- USER : Read-only, does not have access to user information.
- MANAGER : Read-only, can create, update users (cannot delete) .

## Installation:
Make a copy of the dist.env file, naming it .env, then provide the security information

By activating the development profile, the following fixtures are automatically loaded:
### Users:

#### Admin:
- username: admin
- password: Passw0rd!

#### User:
- username: user
- password: Passw0rd!

#### Manager:
- username: manager
- password: Passw0rd!

## Access to the database:
- H2: http://localhost:8080/h2-console
- login: sa
- password: password

