# Practica Basica A

## Vistas y Layouts

En el primer ejercicio, construimos una aplicación sencilla entre todos paso a paso.
En este segundo ejercicio el objetivo es que empecemos a tomar un poco de soltura escribiendo código para aplicaciones Android, tanto XML como Java.

### A. Descripción
En este ejercicio nos concentraremos en modificar solamente tres archivos de un proyecto Android: la Actividad (Java), la interfaz (XML) y los textos (XML). 
Esto lo conseguiremos añadiendo vistas (Views) a una aplicación por medio de XML, y luego nos conectaremos a ellas a través de código Java para poder actuar cuando se produzcan eventos, como la pulsación de un b
tón. En el camino, iremos descubriendo nuevas vistas, además de nuevas propiedades y listeners.

### B. Implementación
- Comenzaremos este ejercicio creando un nuevo proyecto Android desde Android Studio.
- Le asignamos un nombre: EjercicioBasicoA
- Dominio: android.fagdut.org.ar
- Localizamos el proyecto en el disco C:\
- Seleccionamos: Minimun Target SDK: 16
- Generamos el tipo de actividad de inicio: Empty Activity
- Dejamos el nombre por omisión: MainActivity

### B.1 Primer layout

Formulario sencillo con validacion:

Vamos a comenzar editando el archivo de layout, el activity_main.xml. 
En él tendremos ya la estructura creada por defecto, en la que destaca un TextView con un texto.

Modificaremos el xml para obtener una estructura como la siguiente:
Donde mostraremos un texto informativo, un campo de ingreso de datos y un boton.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="ar.org.fagdut.android.ejerciciobasicoa.MainActivity"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Ingrese un numero"
        android:textAppearance="?android:attr/textAppearanceLarge"/>
    <EditText
        android:id="@+id/txtIngreso"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="0"/>
    <Button
        android:id="@+id/btnAceptar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Aceptar"/>
    <TextView
        android:id="@+id/txtError"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Numero invalido"
        android:visibility="gone"/>
</LinearLayout>
```

Luego agregaremos el codigo Java:

Guardamos las referencias de las vistas:

```java
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        txtIngreso = (TextView) findViewById(R.id.txtIngreso);
        txtError = (TextView) findViewById(R.id.txtError);

```
Agregamos un metodo de validacion:

```java
private boolean esNumero(String text) {
     boolean isValid = false;
     try{
         int number = Integer.parseInt(text);
         isValid = true;
     }catch (NumberFormatException nfe) {

     }
     return isValid;
 }
```

Finalmente hacemos el binding del boton aceptar:

```java
btnAceptar.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         aceptar();
     }
 });
 
 public void aceptar() {
     boolean esNumero = esNumero(txtIngreso.getText().toString());
     if (esNumero) {
         txtError.setVisibility(View.GONE);
     } else {
         txtError.setVisibility(View.VISIBLE);
     }
 }
```
