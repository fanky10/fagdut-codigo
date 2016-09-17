# Android - Persistencia de Datos

### El concepto de persistencia de datos

La persistencia consiste en almacenar los datos en un medio secundario, no volátil para posterior reconstrucción y utilización; por lo tanto son independientes en el tiempo del proceso que los creó. Básicamente consiste en que los datos no se borren luego de que la aplicación se cierre.

## La Persistencia en Android

La persistencia en Android consiste en tres tipos de almacenamientos con un propósito muy específico.

### 1. Preferencias Compartidas o Shared Preferences

Con Shared Preferences podemos almacenar y recuperar en el formato clave-valor información como texto, booleanos y números; lo que lo convierte en potencial para almacenar configuraciones del usuario como: estilos, preferencias, etc.

#### Los modos de acceso posibles son:

- **MODE_PRIVATE**: Sólo nuestra aplicación tiene acceso a estas preferencias.

- **MODE_WORLD_READABLE**: Todas las aplicaciones pueden leer estas preferencias, pero sólo la nuestra puede modificarlas (deprecated desde el API 17).

- **MODE_WORLD_WRITEABLE**: Todas las aplicaciones pueden leer y modificar estas preferencias (deprecated desde el API 17).

Su uso es sencillo; si queremos recuperar una "preferencia" que hemos llamado "MiPreferencia" basta con emplear el siguiente código:

```java
SharedPreferences preferencia =
     getSharedPreferences("MiPreferencia",Context.MODE_PRIVATE);
````
Para obtener la información de la preferencia:

```java
boolean habilitarImagenes = preferencia.getBoolean("habilitar_imagenes", true);
```
#### Los parámetros consisten en:

El nombre de la preferencia que queremos recuperar.
Valor por defecto que será utilizado si la preferencia no existe en el sistema.
Para guardar o modificar información de la preferencia:

```java
preferencia.putBoolean("habilitar_imagenes", false);
```

### 2. Ficheros

Con los ficheros tenemos un caso parecido al anterior: existen ficheros privados (que se almacenan en la carpeta files/) y ficheros públicos (que se almacenan en la tarjeta externa SD).

Para acceder a los archivos alojados en la tarjeta interna, usaremos los métodos openFileInput() y openFileOutput(). 

La escritura sería de la siguiente forma:

```java
// Declaramos una constante con el nombre del fichero
private static final String CONFIG_FILENAME = "config.txt";
[...]
 
try {
    // Abrimos un fichero privado asociado con el paquete del contexto de la aplicacion
    // en modo de escritura.
    // El fichero estará en este caso en /data/data/org.danigarcia.android.examples.almacenamiento/files/config.txt
    FileOutputStream streamFichero = openFileOutput(CONFIG_FILENAME, Activity.MODE_PRIVATE);
 
    // Usamos un OutputStreamWriter para transformar una cadena de texto en un array de bytes
    // y almacenarlos en el archivo.
    OutputStreamWriter writer = new OutputStreamWriter(streamFichero);
 
    // Escribimos la cadena "Hola, mundo." y un retorno de carro.
    writer.write("Hola, mundo." + System.getProperty("line.separator"));
 
    // Forzamos la escritura y cerramos el OutputStreamWriter
    writer.flush();
    writer.close();
}
catch(IOException e) {
    Log.e(this.getClass().getName(), "escribirConfiguracion()", e);
}
```

La lectura se realizaría de forma similar:

```java
try {
    // Abrimos un fichero privado asociado con el paquete del contexto de la aplicación
    // en modo de solo lectura
    FileInputStream inputStream = openFileInput(CONFIG_FILENAME);
 
    // Construye un objeto que transformara el fichero en un flujo de caracteres, es decir,
    // convertira el array de bytes en una cadena de texto.
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
 
    // En lugar de utilizar directamente el InputStreamReader, creamos a partir de el
    // un BufferedReader, que encapsula el InputStreamReader y proporciona ya un buffer
    // para los datos de entrada. De este modo nos despreocupamos de mantener el buffer.
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
 
    // Leemos linea a linea
    String configLine = bufferedReader.readLine();
    while(configLine != null)
    {
        procesarLinea(configLine);                  // Usamos la linea de texto para lo que queramos
        configLine = bufferedReader.readLine();     // Leemos la siguiente linea
    }
} catch(IOException e) {
        Log.e(this.getClass().getName(), "leer()", e);
}
```

Para leer y escribir en un fichero almacenado en la tarjeta SD necesitaremos añadir los permisos adecuados en el manifiesto, que serán los siguientes:

```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

A continuación realizaremos la escritura en el fichero de forma parecida a como hicimos en el ejemplo anterior:

```java
// Obtenemos la ruta de la tarjeta SD
File sdCardPath = Environment.getExternalStorageDirectory();
 
// Creamos el fichero pasándole la ruta y el nombre del fichero
// En este caso, será /mnt/sdcard/sdcard/config.txt
File ficheroExt = new File(sdCardPath.getAbsolutePath(), CONFIG_FILENAME);
 
// Abrimos el flujo de salida del fichero para escribir en él.
// El booleano que se pasa como segundo parámetro indicará si queremos añadir nuevo texto (true) o no (false).
FileOutputStream streamFicheroExt = new FileOutputStream(ficheroExt, false);
 
// Obtenemos un OutputStreamWriter para transformar el texto en un array de bytes
OutputStreamWriter writerExt = new OutputStreamWriter(streamFicheroExt);
 
// Escribimos la cadena "Hola, mundo." y un retorno de carro.
writerExt.write("Hola, mundo." + System.getProperty("line.separator"));
 
// Forzamos la escritura y cerramos el OutputStreamWriter
writerExt.flush();
writerExt.close();
```

La lectura se realizará de una forma muy similar

```java
// Obtenemos la ruta de la tarjeta SD
File sdCardPath = Environment.getExternalStorageDirectory();
 
// Creamos el fichero pasándole la ruta y el nombre del fichero
// En este caso, será /mnt/sdcard/sdcard/config.txt
File ficheroExt = new File(sdCardPath.getAbsolutePath(), CONFIG_FILENAME);
 
try {
    // Abrimos un fichero privado asociado con el paquete del contexto de la aplicación
    // en modo de solo lectura
    FileInputStream inputStreamExt = openFileInput(ficheroExt);
 
    // Construye un objeto que transformara el fichero en un flujo de caracteres, es decir,
    // convertira el array de bytes en una cadena de texto.
    InputStreamReader inputStreamReaderExt = new InputStreamReader(inputStreamExt);
 
    // En lugar de utilizar directamente el InputStreamReader, creamos a partir de el
    // un BufferedReader, que encapsula el InputStreamReader y proporciona ya un buffer
    // para los datos de entrada. De este modo nos despreocupamos de mantener el buffer.
    BufferedReader bufferedReaderExt = new BufferedReader(inputStreamReaderExt);
 
    // Leemos linea a linea
    String configLine = bufferedReaderExt.readLine();
    while(configLine != null)
    {
        procesarLinea(configLine);                      // Usamos la linea de texto para lo que queramos
        configLine = bufferedReaderExt.readLine();      // Leemos la siguiente linea
    }
} catch(IOException e) {
        Log.e(this.getClass().getName(), "leer()", e);
}
```

### 3. Base de datos

SQLite es un sistema gestor de bases de datos relacional contenida en una relativamente pequeña biblioteca escrita en C. A diferencia de los sistemas de gestión de bases de datos cliente-servidor, el motor de SQLite no es un proceso independiente con el que el programa principal se comunica. En lugar de eso, la biblioteca SQLite se enlaza con el programa pasando a ser parte integral del mismo.

#### Algunas características interesantes de SQLite

- El conjunto de la base de datos (definiciones, tablas, índices, y los propios datos), son guardados como un sólo fichero estándar en la máquina host.

- SQLite usa un sistema de tipos inusual. En lugar de asignar un tipo a una columna como en la mayor parte de los sistemas de bases de datos SQL, los tipos se asignan a los valores individuales. Por ejemplo, se puede insertar una cadena de texto en una columna de tipo entero (a pesar de que SQLite tratará en primera instancia de convertir la cadena en un entero).

