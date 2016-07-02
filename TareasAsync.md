# Tareas asincrónicas

## Clase AsyncTask 

AsyncTask es una clase del sdk de Android que permite un uso apropiado y sencillo del hilo UI (UI Thread).

Esta clase permite ralizar operaciones en segundo plano (background) y publicar los resultados en la UI Thread, sin tener que manipular Threads o Handlers.

AsyncTask esta diseñado para ser una clase de ayuda alrededor de Thread y Handler y no constituye un entoro de trabajo de hilos. 
Idealmente deberia ser utilizado para operaciones cortas (a lo sumo de algunos segundos). 
Si se necesitase de usar hilos que deben correr por períodos largos de tiempo, está altamente recomendado que se usen las variadas APIs que provee el paquete java.util.concurrent como puede ser Executor, ThreadPoolExecutor y FutureTask.

Una tarea asincrónica esta definida por una computación que corre en un hilo de segundo plano y que su resultado esta publicado en el hilo de UI. Una tarea asincrónica esta definida por 3 tipos genéricos llamados: Params, Progress y Resultado, y 4 pasos: onPreExecute, doInBackground, onProgressUpdate, onPostExecute.

