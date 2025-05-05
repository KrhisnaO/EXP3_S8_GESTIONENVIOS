# Microservicio de Seguimiento de Envios
Este es un microservicio simple desarrollado con Spring Boot, que permite interactuar con una base de datos Oracle para gestionar información de seguimiento de envios.

## Características

- Base de datos en **Oracle**.
- **CRUD** de películas: almacenamiento y consulta de envios.
- **Rutas REST** disponibles:
  - `GET /envios/{nseguimiento}`: Obtiene los detalles de una envios por su ID.
  - `GET /envios`: Obtiene la lista de todas los envios.
  - `POST /envios`: Crea un nuevo envio.
  - `PUT /envios/{nseguimiento}`: Actualiza los detalles de un envio por su ID.
  - `DELETE /envios/{nseguimiento}`: Elimina un envio por su ID.

## Requisitos

- **Java 17** o superior.
- **Spring Boot 2.x** o superior.
- **Oracle Database** (con Wallet configurado).
- **Maven** como gestor de dependencias.

## Configuración de la Base de Datos

### Configuración de Oracle Wallet

1. Descarga el Oracle Wallet desde la consola de Oracle Cloud.
2. Coloca el archivo `tnsnames.ora` y las credenciales del Wallet en la carpeta especificada en la configuración de la base de datos, como se muestra a continuación:
   
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@bbddfs_tp?TNS_ADMIN=/path/to/Wallet

### El microservicio estará disponible en http://localhost:8080.

## Uso

1. Obtener una envio por ID:
Realiza una solicitud GET a la siguiente URL para obtener los detalles de un envio usando su ID:

GET http://localhost:8080/envios/{nseguimiento}

Ejemplo:

GET http://localhost:8080/envios/1

3. Obtener todas las envíos:
Realiza una solicitud GET a la siguiente URL para obtener la lista de todos las envios registrados en la base de datos:

GET http://localhost:8080/envios

5. Crear un nuevo envio

POST http://localhost:8080/envios

8. Actualizar un envio existente:

PUT http://localhost:8080/envios/{nseguimiento}

Ejemplo:

PUT http://localhost:8080/envios/1

10. Eliminar un envio:
    
DELETE http://localhost:8080/envios/{nseguimiento}

Ejemplo:

DELETE http://localhost:8080/envios/1
