# Librerías Útiles - Práctica.

En esta práctica aplicaremos varias cosas que ya conocemos, como la utilización de un Adaptador para una lista y la actividad ListActivity y comunicación entre actividades.

## 1 - Recuperando Lista de repositorios para un usuario en Github

### Agregar el permiso en AndroidManifest.xml

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### Agregar las dependencias en build.gradle del modulo app

```gradle
dependencies {
  // manejo de http requests
  compile 'com.squareup.retrofit2:retrofit:2.1.0'
  // implementacion especifica de GSon
  compile 'com.squareup.retrofit2:converter-gson:2.0.0'
  // libreria para mapear modelo de servidor a modelo de app.
  compile 'com.google.code.gson:gson:2.4'
}
```

## Crear Modelo de aplicación

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

## Modificando MainActivity

Agregar el siguiente codigo a MainActivity.

```java
  private static final String TAG = MainActivity.class.getName();

  GitHubService gitHubService;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    inicializaServicios();
  }
  
  
  private void inicializaServicios() {
    // Creamos el cliente que utilizaremos en la actividad.
    // primero instanciamos el constructor
    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl(getString(R.string.github_base_url))
        .addConverterFactory(GsonConverterFactory.create());
    // luego construimos lo que queremos.
    Retrofit retrofit = builder.build();
    gitHubService =  retrofit.create(GitHubService.class);
  }
  
  private void cargarDatos() {
    Call<List<RepositorioModel>> call = gitHubService.listRepos("fanky10");
    call.enqueue(new Callback<List<RepositorioModel>>() {
      @Override
      public void onResponse(Call<List<RepositorioModel>> call, Response<List<RepositorioModel>> response) {
        actualizaDatos(response.body());
      }

      @Override
      public void onFailure(Call<List<RepositorioModel>> call, Throwable t) {
        Log.e(TAG,"on failure retrofit "+t.getMessage());
      }
    });
  }
  
  // ejecutado cuando recupera los datos
  private void actualizaDatos(List<RepositorioModel> repositorios) {
    Log.d(TAG,"repositorios "+repositorios);
    Toast.makeText(MainActivity.this, "Cantidad encontrada: "+repositorios.size(), Toast.LENGTH_SHORT).show();
  }
  
  // clase de servicio
  public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<RepositorioModel>> listRepos(@Path("user") String user);
  }
```

Se deja al alumno que agregue un layout correspondiente y ejecute el metodo: cargarDatos() mediante un elemento UI como puede ser un botón.

## 2 - Mostrando la lista de elementos en una lista.

Se deja al alumno que utilice el conocimiento ya adquirido para mostrar la lista de resultados correspondiente.

## 3 - Recuperar Lista de contribuidores para un repositorio seleccionado

Se deja al alumno que utilice el conocimiento ya adquirido para mostrar la lista de resultados correspondiente en una nueva actividad.
