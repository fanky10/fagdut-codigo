# Procesos e Hilos

En esta unidad haremos incapié en el concepto de tarea asincrónica, pero para ello necesitamos hacer un repaso sobre como funcionan los procesos e hilos dentro del SDK Android.

Cuando el componente de una aplicación Android comienza y la aplicación no tiene ningun otro componente corriendo, el sistema Android arranca un nuevo proceso Linux, para la aplicación con un solo hilo de ejecución. Por omision, todos los componentes de la misma aplicacion corren en el mismo proceso e hilo (llamado el hilo "main"). Si un componente de la aplicación comienza y ya existe un proceso para esa aplicación (porque otro componente de la aplicación ya existe), entonces el componente iniciado dentro de ese proceso utiliza el mismo hilo de ejecución. Sin embargo, puedes organizar para distintos componentes de tu aplicación que corran en procesos separados, puedes crear hilos adicionales para cualquier proceso.

## Hilos

Cuando una aplicación es lanzada, el sistema crea un hilo de ejecución para la aplicación llamado "main". Este hilo es muy importante porque carga los eventos de despacho a los widgets de interface de usuario apropiados, incluyendo eventos de dibujado. Este tambien es el hilo en donde tu aplicación interactúa con componentes de Android UI toolkit (componentes de los paquetes: android.widget y android.view). Esta hilo principal tambien es llamado algunas veces **UI thread**.

El sistema no crea un hilo separado para cada instancia de un componente. Todos los componentes que corren sobre el mismo proceso son instanciados en el UI thread, y las llamadas del sistema a cada componente son despachadas desde ese hilo. Concecuentemente, metodos que responden a llamadas del sistema (como puede ser onKeyDown() que reporta interacciones con el usuario o una callback del ciclo de vida) siempre corren en el UI thread del proceso.

Cuando tu aplicación realiza trabajo muy intensivo en respuesta a la interacción del usuario, este modelo de hilo unico puede llegar a mostrar una perfomance muy pobre excepto que implementes tu aplicación correctamente. Específicamente, si todo lo que esta pasando en el hilo UI, realiza operaciones largas como acceso a la red o llamadas a la base de datos bloqueará toda la UI. Cuando el hilo esta bloqueado, ningun evento puede ser despachado, inclusive eventos de dibujado. Desde la perspectiva del usuario, la aplicación parece que se colgó. Incluso peor, si el hilo de la aplicación esta bloqueado por mas de varios segundos (5 en realidad) el usuario se le presenta el no tan famoso diálogo "La aplicación no responde" (ANR). El usuario puede entonces decidir quitar la aplicación y desinstalarla si no está enojado.

Entonces surgen dos reglas:

1- No hay que bloquear el hilo de la UI.

2- No hay que acceder al Android UI toolkit desde fuera del hilo de la UI.

## Clase AsyncTask 

AsyncTask es una clase del sdk de Android que permite un uso apropiado y sencillo del hilo UI (UI Thread).

Esta clase permite ralizar operaciones en segundo plano (background) y publicar los resultados en la UI Thread, sin tener que manipular Threads o Handlers.

AsyncTask esta diseñado para ser una clase de ayuda alrededor de Thread y Handler y no constituye un entoro de trabajo de hilos. 
Idealmente deberia ser utilizado para operaciones cortas (a lo sumo de algunos segundos). 
Si se necesitase de usar hilos que deben correr por períodos largos de tiempo, está altamente recomendado que se usen las variadas APIs que provee el paquete java.util.concurrent como puede ser Executor, ThreadPoolExecutor y FutureTask.

Una tarea asincrónica esta definida por una computación que corre en un hilo de segundo plano y que su resultado esta publicado en el hilo de UI. Una tarea asincrónica esta definida por 3 tipos genéricos llamados: Params, Progress y Resultado, y 4 pasos: onPreExecute, doInBackground, onProgressUpdate, onPostExecute.

### Utilización

```java
private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
     protected Long doInBackground(URL... urls) {
         int count = urls.length;
         long totalSize = 0;
         for (int i = 0; i < count; i++) {
             totalSize += Downloader.downloadFile(urls[i]);
             publishProgress((int) ((i / (float) count) * 100));
             // Escape early if cancel() is called
             if (isCancelled()) break;
         }
         return totalSize;
     }

     protected void onProgressUpdate(Integer... progress) {
         setProgressPercent(progress[0]);
     }

     protected void onPostExecute(Long result) {
         showDialog("Downloaded " + result + " bytes");
     }
 }
```

