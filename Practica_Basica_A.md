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
- Le asignamos un nombre: EjercicioBasicoB
- Dominio: android.fagdut.org.ar
- Localizamos el proyecto en el disco C:\
- Seleccionamos: Minimun Target SDK: 16
- Generamos el tipo de actividad de inicio: Empty Activity
- Dejamos el nombre por omisión: MainActivity

### B.1 Primer layout

Vamos a comenzar editando el archivo de layout, el activity_main.xml. 
En él tendremos ya la estructura creada por defecto, en la que destaca un TextView con un texto.
Comenzaremos a trabajar añadiendo Views y ViewGroups bajo éste. Lo que queremos hacer es un clásico checkbox para habilitar o no un ViewGroup, donde guardaremos una vist
a. Debido a que también queremos practicar los eventos de las Views, necesitamos también un TextView que acompañe al ya mencionado checkbox.

El código XML para poder añadir un CheckBox seguido de un TextView (main.xml) es el siguiente:
```xml
<?xml version="1.0" encoding="utf-8"?> 
<LinearLayout
 xmlns:android="http://schemas.android.com/apk/res/android" 
 android:orientation="vertical"
 android:layout_width="fill_parent"
 android:layout_height="fill_parent">
  <TextView 
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="@string/hello">
  </TextView>

  <CheckBox
    android:id="@+id/enableViews"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="@string/enableViews"
    android:checked="false">
  </CheckBox>
  <TextView
    android:id="@+id/eventsTextView"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:maxLines="2"
    android:minLines="2">
  </TextView>
</LinearLayout>
```



