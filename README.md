# ENCORAPEDIA

## DESCRIPCIÓN

Se realizo la automatización de la pagina encorapedia la cual su funcion es consultar reportes dadas la fecha de inicio y de finalización.

Se automatizo solo la función que se nos pidio que fue el siguiente path:
 - entrar a la liga de encorapedia
 - dar click en reportes
 - poner fechas de ininio y final
 - seleccionar el tipo de reporte "prices report"
 - seleccionar show full report
 - dar click en view report
 - Seguido seleccionar sort by price descending
 - Verificiar que la tabla este filtrada por price descending

## MODO DE INSTALACIÓN

### CLONADO DEL REPO

- Clona el repositorio desde esta liga: https://github.com/leonardoaviles/encorapedia.git

### INSTALACIÓN DE NPM PARA EL SERVIDOR LOCAL

- instala npm desde esta liga:  https://nodejs.org.
- seguido copea la carpeta site2 que se encuentra en la carpeta resources a otro lugar fuera.
- en consola pon los siguiente comandos:
1.- npm install http-server --save-dev
2.- npx http-server
- Habre un navegador y pon localhost:8080 y tendras tu servidor local.

### PARA EXPONER EL PUERTO 8080 QUE ESTA EN USO A INTERNET Y PODER VERLO DESDE SELENIUMGRID REALIZA LO SIGUIENTE

- Habre VS Code
- Seguido habre una terminal y diregete al apartado de puertos
- Click en Reenviar un puerto
- pon el puerto 8080, te habrira una pagina de github dale aceptar y cuando termines regresa a vs code.
- click derecho en visibilidad y elige visibilidad del puert como público.
- copea la url de la columna dirección reenviada
- abrela en un un navegador y listo.
- Solo cambia la url que se encuentra en la clase commonsActions a lade localhost, ya que esta por predeterminada la de doccker con selenium del siguiente paso

### PARA INICIAR EL SERVICIO DE SELENIUM GRID CON DOCKER REALIZA LO SIGUIENTE

- Habre una terminal en VS CODE, BASH, O INTELLIJ 
- Seguido localizate en la carpeta del proyecto ENCORAPEDIA
- Seguido pon este comando en la terminal: docker-compose -f selenium-grid.yml up

### PARA CORRER LAS PRUEBAS REALIZA LO SIGUIENTE

- Habre el archivo testng.xml, por defecto viene seteado a 2 navegadores chrome y edge con 2 hilos para cada uno, asu vez se pasan 2 variables la de browser y la de grid, la primera es para setear que browser se va a ocupar y la segunda es para determinar si se estan ejecutando las pruebas en local o en docker, ya que dependiendo si es false habre las pruebas en local y si es true en docker.