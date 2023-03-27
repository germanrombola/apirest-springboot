# API-REST - Suma + incremento porcentual

## Introducción
Este servicio recibe dos números, los suma, y le aplica una suba de un porcentaje que obtiene de un servicio externo (por ejemplo, si el servicio recibe 5 y 5 como valores, y el porcentaje devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11). 

## Función

* Suma



## Instalación

* Clonar o copiar este repo
* Abrirlo con sprint boot
* Ejecutar run as  Srping boot App o compilarlo y ejecutar el Jar.

## Usos
Este servicio es a modo de demo, no tiene usos reales. 

## API endpoint
### Suma("/v1/suma?num1&num2")

num1 : primer número a sumar
num2 : segundo número a sumar

Retorna un jason con metadata informando acerca del resultado, y un objeto con los valores utilizados y el resultado.


## Tencologias utilizadas

* Java 1.8
* H2 databases
* Junit
* Mock
* Swagger
* Spring boot


## Autores
Ing. Germán Rombolá.


## Licencia
This project is available for use under the MIT License"# apirest-springboot" 
