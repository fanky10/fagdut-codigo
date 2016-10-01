# Ejercicio 02

ListView - Mostrar un arreglo de elementos que tienen un layout propio

## Descripción

El ejercicio consistirá en mostrar un array de elementos: PersonajeModel en un ListView, y obtener la selección de un elemento de la lista, mostrando el elemento seleccionado.

## Implementación

Utilizaremos un adaptador propio para poder adaptar el modelo de datos existente a la vista que finalmente se utilizará.

## Pasos a seguir

### 1. Crear un nuevo Proyecto con nombre Ejercicio0302

### 2. Agregar la vista ListView a la interfaz de usuario:

```xml
<ListView android:id="@+id/listView"
￼￼    android:layout_width="match_parent" 
    android:layout_height="match_parent"/>
```
    
### 3. Definir el modelo a utilizar:

```java

public class PersonajeModel {
    String mNombre;
    int mResImagen;

    public PersonajeModel(String mNombre, int mResImagen) {
        this.mNombre = mNombre;
        this.mResImagen = mResImagen;
    }
}
```

### 4. Cargar los datos en un arreglo:

Dentro de MainActivity, como miembro estatico de la misma, inicializar los elementos.

```java
static PersonajeModel[] PERSONAJE_MODEL_LIST = new PersonajeModel[] {
            new PersonajeModel("Nombre",1),
            new PersonajeModel("Otro Personaje",1),
            new PersonajeModel("Otro mas",1)
};
```

### 5. Generar una vista que presente los datos:

```xml
<?xml version="1.0" encoding="utf-8"?>
<!--layout/personaje_item.xml-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:srcCompat="@mipmap/ic_launcher"
        android:id="@+id/imageView" />

    <TextView
        android:text="Personaje Nombre"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/textView" />
</LinearLayout>
```

### 6. Mostrar la vista generada mediante un Adapter propio:

```java
public class PersonajesAdapter extends ArrayAdapter<PersonajeModel> {
    public PersonajesAdapter(Context context, PersonajeModel[] objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // obtenemos el elemento de la posicion
        PersonajeModel personaje = getItem(position);

        // verificamos si hay una vista que esta siendo reusada, sino inflamos una nueva.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.personaje_item, parent, false);
        }

        return convertView;
    }
}

```

### 7. Finalmente asociar los datos.

```java
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // obtenemos el elemento de la posicion
        PersonajeModel personaje = getItem(position);

        // verificamos si hay una vista que esta siendo reusada, sino inflamos una nueva.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.personaje_item, parent, false);
        }

        // buscamos el elemento que queremos manipular sobre la vista raiz
        TextView txtNombre = (TextView) convertView.findViewById(R.id.textView);
        // utilizamos el metodo ya conocido
        txtNombre.setText(personaje.mNombre);

        // retornamos la vista raiz ya manipulada
        return convertView;
    }
```
