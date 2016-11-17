# Ejercicio 02

Tareas Async 

## Descripción

Este ejercicio consistirá en mostrar un calculo pesado (el numero de Fibonacci) de forma asincrona para ver la correcta utilización del componente de SW proveido por el SDK AsyncTask.

## Implementación

Utilizaremos la misma logica UI que teniamos en el ejercicio anterior, pero con la correcta utilizacion de AsyncTasks.

La tarea async a utilizar para calcular el numero de Fibonacci:

```java
    class FibonacciTask extends AsyncTask<Integer, Void, Integer> {


        protected void onPreExecute() {
            // antes de ejecutar la tarea
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            // la tarea ejecutada en segundo plano
            return fibonacci(integers[0].intValue());
        }


        protected void onPostExecute(Integer result) {
            // mostrando el resultado con un toast.
            Toast.makeText(MainActivity.this, "Resultado: "+result, Toast.LENGTH_SHORT).show();
        }
    }
```

## Pasos a seguir

### 1. Utilizaremos la misma actividad que antes solo que ejecutaremos el metodo: calcularAsync

Modificamos MainActivity para que tenga un nuevo metodo que ejecute la tarea async y creamos la misma como una clase dentro de MainActivity.

```java
// MainActivity.java
    // agregamos como atributo de clase (o miembro de clase)
    private TextView txtResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtResultado = (TextView) findViewById(R.id.txt_resultado);
    }
    
    // calculo de forma sincrona
    public void calcular(View v) {
        EditText etEntrada = (EditText) findViewById(R.id.et_entrada);
        int numero = Integer.parseInt(etEntrada.getText().toString());
        int resultado = fibonacci(numero);
        txtResultado.setText("Resultado: " + resultado);
    }
    
    // agregamos el metodo que hace la llamada a la tarea async
    public void calcularAsync(View v) {
        EditText etEntrada = (EditText) findViewById(R.id.et_entrada);
        int number = Integer.parseInt(etEntrada.getText().toString());
        new FibonacciTask().execute(number);
    }

    // funcion de calculo no cambia
    int fibonacci(int n) {
        if (n == 0 || n == 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // finalmente la definicion de la tarea async
    class FibonacciTask extends AsyncTask<Integer, Void, Integer> {

        protected void onPreExecute() {
            txtResultado.setText("Calculando...");
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            return fibonacci(integers[0].intValue());
        }


        protected void onPostExecute(Integer resultado) {
            txtResultado.setText("Resultado: " + resultado);
        }
    }

```

Finalmente cambiamos el viejo metodo sync al nuevo async.

```xml
<!-- activity_main.xml -->
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="ar.org.fagdut.codigo.android.tareasasync.MainActivity"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Ingrese un numero" />

    <EditText
        android:id="@+id/et_entrada"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Fibbonacci"
        android:onClick="calcularAsync"/><!-- cambiamos solo el metodo a llamar -->

    <TextView
        android:id="@+id/txt_resultado"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="Resultado"/>
</LinearLayout>

```
