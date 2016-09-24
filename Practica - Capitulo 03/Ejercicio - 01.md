# Ejercicio 01

ListViews - Mostrar un array en un ListView y selección de elementos

# Descripción

El ejercicio consistirá en mostrar un array de String en un ListView, y obtener la selección de un elemento de la lista, mostrando el elemento seleccionado.

# Implementación

Un *adapter* (Adaptador) enlaza los datos con las vistas. Él es el responsable de crear vistas hijas para cada uno de los ítems y proporciona acceso al dato que se mostrará.

```java
setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array));
```


El método setListAdapter sirve para especificar el adaptador que queremos utilizar para nuestro ListActivity.

El ArrayAdapter es un adaptador definido, es una clase genérica que une clases que hereden de AdapterView con un array de objetos (un adaptador es un objeto que nos relaciona una cosa con otra, datos con “vistas”). Por omisión ArrayAdapter asocia el valor toString de cada objeto a un control TextView definido en un diseño (layout). Se pueden utilizar otros constructores para usar layouts más complejos, y se puede extender la clase ArrayAdapter para utilizar alternativas a TextView para cada elemento mediante la redefinición del método getView().

El constructor del ArrayAdapter obtiene como parámetros el contexto, un identificador de recurso de un layout que contiene un TextView a utilizar cuando se instancian las vistas (en éste caso se utiliza uno simple que provee android) y el array a mostrar.

El método encargado de detectar una pulsación sobre un elemento del ListActivity es onListItemClick(), cuya definición es la siguiente:

```java
@Override
protected void onListItemClick(ListView l, View v, int pos, long id) { super.onListItemClick(l, v, position, id);
......
}
```

Donde los parámetros son:

- **l** El ListView donde ocurrió el click
- **v** La vista (View) que fue pulsada 
- **pos** La posición de la vista en la lista
- **id** El identificador de la fila del item que fue pulsado

Para mostrar un mensaje flotante simple en pantalla se puede utilizar el TOAST, que permite realizarlo de una forma fácil:

```java
Toast.makeText(contexto, string, Toast.LENGTH_SHORT).show();
```

Para detectar una pulsación larga sobre un ítem de la lista, se realiza de forma diferente. Así bien, no existe un método del ListActivity, como existía para la pulsación normal con el método onListItemClick(). Para ello se utiliza un listener sobre el objeto ListView, no sobre el ListActivity (también la pulsación normal se puede detectar de ésta manera, pero la anterior resulta mucho más fácil).

```java
listview.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
    public boolean onItemLongClick(AdapterView<?> l, View v, int position, long id) {
        ...
        return true; // Si se realiza la pulsación larga
    } 
});
```

# Pasos a seguir

### 1. Crear un nuevo Proyecto con nombre Ejercicio0301

### 2. Agregar la vista ListView a la interfaz de usuario:

```xml
<ListView android:id="@+id/ListOpciones"
￼￼    android:layout_width="match_parent" 
    android:layout_height="match_parent"/>
```
    
### 3. Definir un array con nuestros datos de prueba:

```java
finalString[] datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
```

### 4. Crear el adaptador de tipo ArrayAdapter y lo asignamos al control mediante el metodo *setAdapter();*

```java
final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
ListView lstOpciones = (ListView)findViewById(R.id.ListOpciones); 
lstOpciones.setAdapter(adaptador);
```

### 5. Detectar una pulsación sobre el *ListView*, sobre un elemento de la lista creada tendremos que
implementar el evento *onItemClick*.

```java
lstOpciones.setOnItemClickListener(newOnItemClickListener() {
    @Override
    publicvoidonItemClick(AdapterView<?> a, View v, intpos, longid) {
        //Acciones necesarias al hacer click
        String keyword = adaptador.getItem(pos).toString(); 
        Toast.makeText(getApplicationContext(),”You Selected: ” + keyword, Toast.LENGTH_LONG).show();
    } 
});
```
