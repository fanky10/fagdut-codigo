# Ejercicio 01

Tareas Async 

## Descripción

Este ejercicio consistirá en mostrar un calculo pesado (el numero de Fibonacci) primero de forma sincrona y luego de forma async.

## Implementación

Mostraremos por pantalla un input de texto (EditText) con un boton para calcular el numero ingresado y lo mostraremos en una caja de texto sencilla (TextView).

La funcion a utilizar para calcular el numero de Fibonacci:

```java
    int fibonacci(int n) {
        if (n==0 || n==1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
```

## Pasos a seguir

### 1. Creamos un proyecto con el layout y una logica sencilla

- Nombre: Ejercicio0501
- Domain: codigo.android.fagdut.org.ar
- MinSDK: 18
- Empty Activity 
- MainActivity
- activity_main

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
        android:onClick="calcular"/>

    <TextView
        android:id="@+id/txt_resultado"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="Resultado"/>
</LinearLayout>

```

```java
// MainActivity.java
    // onCreate no tiene cambios
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // agregamos el calculo de forma sincrona
    public void calcular(View v) {
        EditText etEntrada = (EditText) findViewById(R.id.et_entrada);
        int numero = Integer.parseInt(etEntrada.getText().toString());
        TextView txtResultado = (TextView) findViewById(R.id.txt_resultado);
        // esto se esta ejecutando en el mismo hilo de rendering del componente UI
        int resultado = fibonacci(numero);
        txtResultado.setText("Resultado: " + resultado);
    }
    // funcion de calculo
    int fibonacci(int n) {
        if (n == 0 || n == 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

```

