# Microservicio de Seguimiento de Envios
Este es un microservicio simple desarrollado con Spring Boot, que permite interactuar con una base de datos Oracle para gestionar información de seguimiento de envios.

## Características principales

✅ CRUD completo de usuarios  
✅ Base de datos en **Oracle**  
✅ API documentada con **HATEOAS** (hipervínculos en las respuestas)  
✅ **2 pruebas unitarias** desarrolladas con **JUnit 5**  
✅ Despliegue en **Docker** mediante **docker-compose** y ejecución en **Play With Docker**

---

## Endpoints disponibles

| Método | Ruta                    | Descripción                     |
|--------|------------------------|--------------------------------|
| GET    | /envios/{nseguimiento}  | Obtiene los envíos por ID     |
| GET    | /envios                 | Lista todos los envíos        |
| POST   | /envios                 | Crea un nuevo envio           |
| PUT    | /envios/{nseguimiento}  | Actualiza un envio existente  |
| DELETE | /envios/{nseguimiento}  | Elimina un envio por ID       |

> 📝 Las respuestas incluyen enlaces HATEOAS como `_links.self`, `_links.update`, etc.

## Pruebas unitarias

✔️ Se integró `spring-boot-starter-test` en el proyecto.  
✔️ Se configuraron las anotaciones `@Test`, `@BeforeEach`, `@AfterEach` en los test.  
✔️ Se desarrollaron **pruebas unitarias básicas**:
1. Validación de creación de usuario.
2. Validación de error en caso de datos inválidos.

Las pruebas se ejecutan con:

```bash
mvn clean install -DskipTests
```

## Cómo ejecutar en Docker Lab

1. Entrar a Play With Docker
- Ir a  https://labs.play-with-docker.com/
- Iniciar sesión con cuenta de Docker Hub
- Crear una instancia

2. Clonar el repositorio desde GitHub

En la terminal de la instancia:

```bash
git clone https://github.com/KrhisnaO/EXP3_S8_GESTIONENVIOS.git
ls
cd EXP3_S8_GESTIONENVIOS
```

3. Construir y levantar los contenedores

Ejecuta:

```bash
docker-compose up
```

Esto descargará las imágenes necesarias, construirá la aplicación y levantará los servicios.

🔗 Al finalizar, Docker Lab te mostrará una URL pública (parecida a http://ip172-18-0-...direct.labs.play-with-docker.com:8080).

👉 IMPORTANTE: añade /envios al final de esa URL para acceder al endpoint inicial.

4. Probar en Postman

- Copia la URL pública generada
- Abre Postman
- Usa la URL para probar los endpoints GET, POST, PUT, DELETE.

Ejemplo (GET todos los envíos):

GET http://ip172-18-0-...direct.labs.play-with-docker.com:8080/envios

