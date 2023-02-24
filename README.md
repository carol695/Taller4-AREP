# TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

Se construye un servidor Web (tipo Apache) en Java. El servidor debe ser capaz de entregar páginas html e imágenes tipo PNG. 
Igualmente el servidor debe proveer un framework IoC para la construcción de aplicaciones web a partir de POJOS. 
Usando el servidor se debe construir una aplicación Web de ejemplo. El servidor debe atender múltiples solicitudes no concurrentes.

Se desarrollaR un prototipo mínimo que demuestre capcidades reflexivas de JAVA y permita por lo menos cargar un bean (POJO) y 
derivar una aplicación Web a partir de él. 
 
**** 
## Empezando

### 🛠️ Abre y ejecuta el proyecto

**1. Para empezar se clona el repositorio colocando el siguiente comando**

```
git clone https://github.com/carol695/Taller4-AREP.git
```
**2. Ya clonado el repositorio abrimos el laboratorio utilizando cualquier de los siguientes IDE.**

* Intellij.
* eclipse.
* visual Studio code. 

**3. Luego de abrir el laboratorio, corremos el proyecto. Para este caso colocaremos lo siguiente:**

```
git clean package exec:java -D"exec.mainClass"="edu.escuelaing.arem.app.WebApss.FirstApp"
```

Una vez descargado el repositorio nos dirigimos al directorio raiz del proyecto y ejecutamos el comando:

```
mvn clean package exec:java
```

### Para el nombre del servicio podemos reemplazarlo con: 

* index: Archivo en html.
* estilos: Archivo en css.
* imagen: Imagen en formato jpg.
* javaScript: Formato en js. 

****
### :chart_with_downwards_trend: Prerrequisitos

-   [Git](https://git-scm.com/downloads) - Sistema de control de versiones
-   [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias
-   [Java 8](https://www.java.com/download/ie_manual.jsp) - Entorno de desarrollo
-   [Intellij Idea](https://www.jetbrains.com/es-es/idea/download/) (Opcional)

****

### :bulb: Construido con

* [Maven](https://maven.apache.org/) - Dependency Management

## :mag_right: Versionamiento

Para definir el versionamiento se pudo observar los tags del repositorio, y el versionaiento es 1.0 

## :woman: Actores

* **Carol Tatiana Cely Rodriguez** 

## :page_with_curl: Descripción

Hacemos uso del patron singleton para crear una sola instancia del servidor y una sola instancia de StaticFiles, además se usa anotaciones @Component y @RequestMapping, el @Component esta en las clases las cuales se escanearan por el HTTP server y guardara en una coleccion llave-valor las respuestas que debe dar el servidor web.


