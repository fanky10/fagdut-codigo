# Librerías Útiles en un proyecto Android

Hay pocas aplicaciones android que no utilicen algún tipo de comunicación con un servidor externo, ya sea para visualizar la actividad del usuario o simplemente enviar errores de la aplicación cuando esta falla.
En esta unidad repasaremos tres conceptos muy sencillos a la hora de crear una aplicación que persiste datos con un servidor externo.

## 1. Comunicación en el Servidor

En cualquier aplicación android que quiera utilizar el recurso de internet, primero debe establecer el permiso para poder hacerlo. El mismo se establece en el *AndroidManifest.xml* de la aplicación dentro del tag **```<manifest>```**

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

Para la comunicación con el servidor, se puede utilizar la clase Java [HttpClient](https://developer.android.com/reference/org/apache/http/client/HttpClient.html) pero sería muy engorroso tener que manejar todas las Exceptions lanzadas por el cliente solo para construir el objeto correctamente y luego otro conjunto de Exceptions solo para recuperar el contenido linea a lina mediante un [BufferedReader](https://developer.android.com/reference/java/io/BufferedReader.html).

Es por esto, que esta unidad se focaliza en la utilización de la libreria: [RetroFit](http://square.github.io/retrofit/)

### Agregar la dependencia en el archivo: build.gradle del módulo app

```gradle
dependencies {
  compile 'com.squareup.retrofit2:retrofit:2.1.0'
}
```

### Introducción a Retrofit.

Retrofit transforma el HttpClient en una Interface Java de la forma:
```java
public interface GitHubService {
  @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);
}
```

Luego la clase *Retrofit* genera una implementación de la interface *GithubService*

```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .build();

GitHubService service = retrofit.create(GitHubService.class);
```

Cada llamada del servicio *GithubService* que se ha creado puede realizar un request Http de forma síncrona o asíncrona.

```java
Call<List<Repo>> repos = service.listRepos("fanky10");
```

## 2. Mapeo a modelo de aplicación

## 3. Carga de Imágenes
