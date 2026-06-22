# Agenda Tatuador

# Sistema de Gestión para Estudios de Tatuajes

Sistema de microservicios desarrollado para apoyar la gestión de un estudio de tatuajes. Permite administrar clientes, ayudantes, tatuadores, estudios, tatuajes, pagos, inventario, sesiones y resultados.

## Integrantes

* Carolina Pavez
* Daniel Delgado

## Tecnologías utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL
* Docker y Docker Compose
* OpenFeign
* Flyway
* Eureka Server
* API Gateway
* Swagger / OpenAPI
* Postman

## Microservicios

El sistema está compuesto por los siguientes microservicios:

* **cliente:** administra la información de los clientes.
* **ayudante:** registra y gestiona a los ayudantes del estudio.
* **estudio:** administra la información y ubicación de los estudios.
* **vendedor:** gestiona a los vendedores relacionados con los insumos.
* **pago:** registra boletas, montos y métodos de pago.
* **tatuador:** administra los datos y especialidades de los tatuadores.
* **tatuaje:** registra información sobre los tatuajes, como descripción, zona y estilo.
* **inventario:** administra los insumos, cantidades y precios.
* **sesion:** registra las sesiones de tatuaje y relaciona cliente, ayudante, estudio, pago, tatuador y tatuaje.
* **resultado:** registra valoraciones y comentarios asociados a clientes y tatuajes.

Además, el proyecto incluye:

* **Eureka Server:** permite registrar y descubrir los microservicios.
* **API Gateway:** centraliza las solicitudes hacia los distintos microservicios.
* **MySQL:** utiliza una base de datos independiente para cada microservicio.

## Ejecución del proyecto

Antes de iniciar el proyecto, verificar que Docker Desktop esté abierto y en funcionamiento.

Abrir PowerShell dentro de la carpeta principal del proyecto, donde se encuentra el archivo `docker-compose.yml`.

Ejecutar:

```
docker compose up --build
```

Este comando crea las imágenes necesarias y levanta todos los contenedores del sistema.

Para ejecutar los contenedores en segundo plano, utilizar:

```
docker compose up -d --build
```

Para comprobar que los contenedores están activos:

```
docker compose ps
```

Para detener los contenedores:

```
docker compose down
```

## Acceso a los servicios

* Eureka Server:

  http://localhost:8761


* Swagger centralizado:

  http://localhost:8080/swagger-ui.html

## Pruebas con Postman

Una vez que los contenedores estén iniciados, se pueden probar los endpoints mediante Postman.

Los endpoints deben ser consumidos utilizando el API Gateway, por ejemplo:

* Obtener clientes:

  GET http://localhost:8080/api/v1/clientes

* Obtener ayudantes:

  GET http://localhost:8080/api/v1/ayudantes

* Obtener estudios:

  GET http://localhost:8080/api/v1/estudios

* Obtener vendedores:

  GET http://localhost:8080/api/v1/vendedores

* Obtener pagos:

  GET http://localhost:8080/api/v1/pagos

* Obtener tatuadores:

  GET http://localhost:8080/api/v1/tatuadores

* Obtener tatuajes:

  GET http://localhost:8080/api/v1/tatuajes

* Obtener sesiones:

  GET http://localhost:8080/api/v1/sesiones

* Obtener inventario:

  GET http://localhost:8080/api/v1/inventarios

* Obtener resultados:

  GET http://localhost:8080/api/v1/resultados

También es posible revisar y probar los endpoints desde Swagger, seleccionando cada microservicio en la documentación centralizada.
