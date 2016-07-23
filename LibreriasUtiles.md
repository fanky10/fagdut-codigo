# Librerías Útiles en un proyecto Android

Hay pocas aplicaciones android que no utilicen algún tipo de comunicación con un servidor externo, ya sea para visualizar la actividad del usuario o simplemente enviar errores de la aplicación cuando esta falla.
En esta unidad repasaremos tres conceptos muy sencillos a la hora de crear una aplicación que persiste datos con un servidor externo.

Las mismas se pueden agregar de diversas formas, pero la más común es simplemente modificando el build.gradle del módulo app:

```gradle
apply plugin: "..."

android { ... }

dependencies {
  // otras dependencias del proyecto como puede ser:
  
  testCompile 'junit:junit:4.12'
  compile 'com.android.support:appcompat-v7:24.0.0'
  
  // mas dependencias
  ...
}
```

## 1. Comunicación en el Servidor

En cualquier aplicación android que quiera utilizar el recurso de internet, primero debe establecer el permiso para poder hacerlo. El mismo se establece en el *AndroidManifest.xml*

```xml
<manifest ...>
...

<uses-permission android:name="android.permission.INTERNET" />

<application ... />
...
</manifest>
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

Actualmente, mediante el crecimiento exponencial que tuvo el estándar [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) muchas aplicaciones se encontraron con la situación que los distintos servicios expuestos por terceros, no se complementaban completamente con el modelo de negocio en particular que ellos usaban.

En este ejemplo, veremos como el objeto JSON devuelto por la API de Github nos brinda mucha más información de la que requerimos.

*Modelo "Repositorio" de Github*

```json
{
  "id": 11532546,
  "name": "Android-Aimant",
  "full_name": "fanky10/Android-Aimant",
  "owner": {
    "login": "fanky10",
    "id": 619168,
    "avatar_url": "https://avatars.githubusercontent.com/u/619168?v=3",
    "gravatar_id": "",
    "url": "https://api.github.com/users/fanky10",
    "html_url": "https://github.com/fanky10",
    "followers_url": "https://api.github.com/users/fanky10/followers",
    "following_url": "https://api.github.com/users/fanky10/following{/other_user}",
    "gists_url": "https://api.github.com/users/fanky10/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/fanky10/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/fanky10/subscriptions",
    "organizations_url": "https://api.github.com/users/fanky10/orgs",
    "repos_url": "https://api.github.com/users/fanky10/repos",
    "events_url": "https://api.github.com/users/fanky10/events{/privacy}",
    "received_events_url": "https://api.github.com/users/fanky10/received_events",
    "type": "User",
    "site_admin": false
  },
  "private": false,
  "html_url": "https://github.com/fanky10/Android-Aimant",
  "description": "Simple Android Application ",
  "fork": false,
  "url": "https://api.github.com/repos/fanky10/Android-Aimant",
  "forks_url": "https://api.github.com/repos/fanky10/Android-Aimant/forks",
  "keys_url": "https://api.github.com/repos/fanky10/Android-Aimant/keys{/key_id}",
  "collaborators_url": "https://api.github.com/repos/fanky10/Android-Aimant/collaborators{/collaborator}",
  "teams_url": "https://api.github.com/repos/fanky10/Android-Aimant/teams",
  "hooks_url": "https://api.github.com/repos/fanky10/Android-Aimant/hooks",
  "issue_events_url": "https://api.github.com/repos/fanky10/Android-Aimant/issues/events{/number}",
  "events_url": "https://api.github.com/repos/fanky10/Android-Aimant/events",
  "assignees_url": "https://api.github.com/repos/fanky10/Android-Aimant/assignees{/user}",
  "branches_url": "https://api.github.com/repos/fanky10/Android-Aimant/branches{/branch}",
  "tags_url": "https://api.github.com/repos/fanky10/Android-Aimant/tags",
  "blobs_url": "https://api.github.com/repos/fanky10/Android-Aimant/git/blobs{/sha}",
  "git_tags_url": "https://api.github.com/repos/fanky10/Android-Aimant/git/tags{/sha}",
  "git_refs_url": "https://api.github.com/repos/fanky10/Android-Aimant/git/refs{/sha}",
  "trees_url": "https://api.github.com/repos/fanky10/Android-Aimant/git/trees{/sha}",
  "statuses_url": "https://api.github.com/repos/fanky10/Android-Aimant/statuses/{sha}",
  "languages_url": "https://api.github.com/repos/fanky10/Android-Aimant/languages",
  "stargazers_url": "https://api.github.com/repos/fanky10/Android-Aimant/stargazers",
  "contributors_url": "https://api.github.com/repos/fanky10/Android-Aimant/contributors",
  "subscribers_url": "https://api.github.com/repos/fanky10/Android-Aimant/subscribers",
  "subscription_url": "https://api.github.com/repos/fanky10/Android-Aimant/subscription",
  "commits_url": "https://api.github.com/repos/fanky10/Android-Aimant/commits{/sha}",
  "git_commits_url": "https://api.github.com/repos/fanky10/Android-Aimant/git/commits{/sha}",
  "comments_url": "https://api.github.com/repos/fanky10/Android-Aimant/comments{/number}",
  "issue_comment_url": "https://api.github.com/repos/fanky10/Android-Aimant/issues/comments{/number}",
  "contents_url": "https://api.github.com/repos/fanky10/Android-Aimant/contents/{+path}",
  "compare_url": "https://api.github.com/repos/fanky10/Android-Aimant/compare/{base}...{head}",
  "merges_url": "https://api.github.com/repos/fanky10/Android-Aimant/merges",
  "archive_url": "https://api.github.com/repos/fanky10/Android-Aimant/{archive_format}{/ref}",
  "downloads_url": "https://api.github.com/repos/fanky10/Android-Aimant/downloads",
  "issues_url": "https://api.github.com/repos/fanky10/Android-Aimant/issues{/number}",
  "pulls_url": "https://api.github.com/repos/fanky10/Android-Aimant/pulls{/number}",
  "milestones_url": "https://api.github.com/repos/fanky10/Android-Aimant/milestones{/number}",
  "notifications_url": "https://api.github.com/repos/fanky10/Android-Aimant/notifications{?since,all,participating}",
  "labels_url": "https://api.github.com/repos/fanky10/Android-Aimant/labels{/name}",
  "releases_url": "https://api.github.com/repos/fanky10/Android-Aimant/releases{/id}",
  "deployments_url": "https://api.github.com/repos/fanky10/Android-Aimant/deployments",
  "created_at": "2013-07-19T16:53:17Z",
  "updated_at": "2013-10-12T18:57:17Z",
  "pushed_at": "2013-09-08T00:14:14Z",
  "git_url": "git://github.com/fanky10/Android-Aimant.git",
  "ssh_url": "git@github.com:fanky10/Android-Aimant.git",
  "clone_url": "https://github.com/fanky10/Android-Aimant.git",
  "svn_url": "https://github.com/fanky10/Android-Aimant",
  "homepage": null,
  "size": 5052,
  "stargazers_count": 0,
  "watchers_count": 0,
  "language": "Java",
  "has_issues": true,
  "has_downloads": true,
  "has_wiki": true,
  "has_pages": false,
  "forks_count": 0,
  "mirror_url": null,
  "open_issues_count": 0,
  "forks": 0,
  "open_issues": 0,
  "watchers": 0,
  "default_branch": "master"
}
```

*Modelo "Repositorio" de la app*

```java
public class Repositorio {
    String mNombre;
    Boolean mPrivado;
    String mDescripcion;
}
```

Es por eso que nos encontramos en la situación que no podemos "mapear" de forma directa los datos que viene del servidor con el modelo de negocio que utiliza la aplicación.

Es por eso, que una vez mas requerimos de la ayuda de una librería de utilidad para que nos facilite la transformación de un modelo a otro, la utilizaremos es: [GSON](https://github.com/google/gson)

Agregamos la dependencia mediante:

```gradle
dependencies {
  // libreria para mapear modelo de servidor a modelo de app.
  compile 'com.google.code.gson:gson:2.4'
}
```

Y la utilización es basnte sencilla, con la [Annotation](https://en.wikipedia.org/wiki/Java_annotation) **@SerializedName** le "diremos" que atributo del modelo del servidor se corresponde con que atributo del modelo de nuestra aplicación:

```java
public class Repositorio {
    @SerializedName("name")
    String mNombre;

    @SerializedName("private")
    Boolean mEsPrivado;

    @SerializedName("description")
    String mDescripcion;

    public String toString() {
      return String.format("nombre: %s / privado: %s / desc: %s", mNombre, mEsPrivado, mDescripcion);
    }
}
```
