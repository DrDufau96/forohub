# ForoHub

ForoHub es una aplicación de backend desarrollada en Java utilizando Spring Boot. Proporciona un sistema de autenticación basado en JWT y permite gestionar usuarios, tópicos y otros recursos relacionados con foros en línea. Este proyecto incluye varias funcionalidades para la autenticación y autorización, además de CRUD para los recursos.

## Características
- **Autenticación JWT:** Los usuarios pueden autenticarse y obtener tokens JWT para realizar solicitudes protegidas.
- **Gestión de usuarios:** Permite registrar y autenticar usuarios.
- **Gestión de tópicos:** CRUD para administrar tópicos (crear, leer, actualizar y eliminar).
- **Seguridad:** Configuración de seguridad con Spring Security.
- **Persistencia:** Uso de JPA y MySQL para la gestión de datos.

---

## Configuración del Proyecto

### Prerrequisitos
- **Java JDK 21 o superior**
- **Maven**
- **MySQL**
- **IntelliJ IDEA** 

### Configuración de la base de datos
1. Asegúrate de tener una instancia de MySQL en ejecución.
2. Crea una base de datos llamada `forohub`.
3. Configura las credenciales de acceso en el archivo `application.properties` ubicado en `src/main/resources/`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   ```

---

## Ejecución del Proyecto

### Desde IntelliJ IDEA
1. Importa el proyecto como un proyecto Maven.
2. Ejecuta la clase principal `ForohubApplication` ubicada en `src/main/java/com/aluracursos/forohub/ForohubApplication.java`.

### Desde la Terminal
1. Navega al directorio raíz del proyecto.
2. Ejecuta el siguiente comando:
   ```bash
   mvn spring-boot:run
   ```

---

## Endpoints Principales

### Autenticación
- **POST /auth**
  - Descripción: Autentica un usuario y devuelve un token JWT.
  - Cuerpo de la solicitud:
    ```json
    {
      "login": "nombreDeUsuario",
      "clave": "contraseña"
    }
    ```
  - Respuesta:
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

- **POST /token**
  - Descripción: Genera un token estático para pruebas.

### Usuarios
- **POST /usuario**
  - Descripción: Registra un nuevo usuario.
  - Cuerpo de la solicitud:
    ```json
    {
      "login": "nombreDeUsuario",
      "clave": "contraseña"
    }
    ```

### Tópicos
- **POST /topicos**
  - Descripción: Crea un nuevo tópico (requiere autenticación).
  - Encabezado:
    ```
    Authorization: Bearer {token}
    ```
  - Cuerpo de la solicitud:
    ```json
    {
      "titulo": "Título del tópico",
      "mensaje": "Mensaje del tópico",
      "status": "Activo",
      "curso": "Curso relacionado"
    }
    ```

- **GET /topicos**
  - Descripción: Lista todos los tópicos con paginación.

- **PUT /topicos/{id}**
  - Descripción: Actualiza un tópico existente.

- **DELETE /topicos/{id}**
  - Descripción: Elimina un tópico por ID.

---

## Estructura del Proyecto
- **`config`**: Configuración de seguridad y otros aspectos del proyecto.
- **`controller`**: Controladores que gestionan las solicitudes HTTP.
- **`model`**: Clases que representan los datos del sistema.
- **`repository`**: Interfaces para la interacción con la base de datos.
- **`service`**: Lógica de negocio y servicios auxiliares.
- **`infra`**: Filtros y utilidades para el manejo de seguridad.

---

## Dependencias Principales
- **Spring Boot Starter Web**
- **Spring Boot Starter Security**
- **Spring Boot Starter Data JPA**
- **MySQL Connector**
- **JWT (io.jsonwebtoken)**

---

## Notas Adicionales
- Asegúrate de configurar correctamente el archivo `application.properties` antes de ejecutar el proyecto.
- Usa herramientas como Postman o Insomnia para probar los endpoints.

---

## Autor
Proyecto creado como parte de una práctica de desarrollo backend. Sientete libre de contribuir o personalizarlo según tus necesidades.
