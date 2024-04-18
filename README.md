
## Tencnologías usadas:
- Java version: 1.8
- Gradle version: 6.8.3
- Spring Boot version: 2.2.1.RELEASE
- H2 database
- JPA
- Lombok
- Slf4j
- JWT


## Definicion de la solucion
Se creo una API rest donde se expusieron 2 servicios para crear un usuario y otro para consultarlo.

## Servicio POST
1. El Path debe ser `http://localhost:8083/user`  y el método HTTP tipo **POST**: permite crear un usuario con las siguientes validaciones
    1. Se valida el formato del correo, que sea un correo valido. 
       **En caso de no cumplir esta validacion obtendremos el siguiente mensaje:**
        ```json
            {
             "mensaje" : "format emails is invalid"
            }
        ```       
    2. Se valida que el formato de la contraseña cumpla con ciertos caracteres, este formato se puede encontrar
     en el **application.properties** y es configurable. 
       **En caso de no cumplir esta validación obtendremos el siguiente mensaje:**
        ```json
            {
             "mensaje" : "format password is invalid"
            }
        ```   
     
   3. Se valida si el correo en la solicitud ya existe en base de datos.
        **En caso de no cumplir esta validación obtendremos el siguiente mensaje:**
        ```json
            {
              "mensaje" : "this email already exist"
            }
        ```
    **--------------------------------------------------------------------------------------------------------------**

   **Ejemplo de petición y respuesta exitosa**  
   Petición  path: `http://localhost:8083/user` método: **POST**
   ```json
    {
      "name": "Jairo Ayala",
      "email": "jairo910731@eee.co",
      "password": "LosEnanosCulean2",
      "phones": [
        {
          "number": "3136280783",
          "citycode": "57",
          "countrycode": "5"
        }
      ]
    }
    ```
   **Respuesta exitosa**
    ```json
    {
        "id": "47f2739f-429d-4d8c-b488-64abc5790d02",
        "created": "2024-04-18T00:01:30.099",
        "modified": "2024-04-18T00:01:30.099",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWlybyBBeWFsYSIsImlhdCI6MTcxMzQxNjQ5MCwiZXhwIjoxNzEzNDIwMDkwfQ.VkVgHTbkosLQLLjF_kEZbFh28ktlYy4tRdT9x5eCSTM",
        "last_login": "2024-04-18T00:01:30.099",
        "isactive": true
    }
    ```
   
## Servicio GET   
2. El path debe ser `http://localhost:8083/user/{id}` y el método HTTP tipo **GET**, donde la variable  **{id}** 
corresponde al identificador con el cual se almacenó el usuario en la base de datos, explicado en el primer punto.
   El siguiente es un ejemplo de petición y un ejemplo de cómo debería ser la respuesta en un caso exitoso  
   Petición  path: `http://localhost:8083/user/47f2739f-429d-4d8c-b488-64abc5790d02` método: **GET**
   ```json
        {
            "name": "Jairo Ayala",
            "email": "jairo910731@eee.co",
            "password": "LosEnanosCulean2",
            "phones": [
                {
                    "number": "3136280783",
                    "phone_id": "9d36e057-676a-474c-8b8a-6c027a399dc0",
                    "citycode": "57",
                    "countrycode": "5"
                }
            ],
            "user_id": "47f2739f-429d-4d8c-b488-64abc5790d02"
        }
    ```
   
   
## Script de la Base de datos

1. Este Script lo podemos encontrar en el archivo ubicado en la ruta :  `**/src/main/resources/schema.sql`.

2. De igual forma de adjunta aqui :

    ```roomsql
        DROP TABLE IF EXISTS users;

        DROP TABLE IF EXISTS phones;
        
        CREATE TABLE users (user_id VARCHAR(50) PRIMARY KEY,
         name VARCHAR(30),
         email VARCHAR(20),
         password VARCHAR(20),
         created_date TIMESTAMP,
         last_update_date TIMESTAMP,
         last_login TIMESTAMP,
         state BOOLEAN,
         token VARCHAR(100)
        );
        
        CREATE TABLE phones (phone_id VARCHAR(50) PRIMARY KEY,
         user_id VARCHAR(50),
         number VARCHAR(10),
         city_code VARCHAR(10),
         country_code VARCHAR(10),
         foreign key (user_id) references users(user_id)
        );
    ```

## Swagger

Cuando se levante el contexto de la aplicación con SpringBoot, abrir en un browser la siguiente URL :

**http://localhost:8083/ms-users/swagger-ui/index.html?configUrl=/ms-users/openapi/swagger-config**

Aquí se mostrara la `swagger-ui` y se podrán ver los servicios y modelos expuestos.
