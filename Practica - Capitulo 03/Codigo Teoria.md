# Control Spinner

Implementando un adaptador en el control *Spinner*.

## Definicion XML

```xml
<Spinner
    android:id="@+id/CmbOpciones"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

## Utilizacion en una Activity (JAVA)

### 1. Creamos el adaptador

Simplemente creamos un adaptador a partir de: el contexto (this), el layout del spinner item y un arreglo de String.

```java
final String[] datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
```

### 2. Lo utilizamos

Obtenemos la instancia del control *Spinner* y le seteamos el adaptador.

```java
final Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
adaptador.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
cmbOpciones.setAdapter(adaptador);
```


# Control ListView

Implementando un adaptador en la vista *ListView*.

## Definicion XML

```xml
<ListView
    android:id="@+id/LstOpciones"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

## Utilizacion en una Activity (JAVA)

### 1. Creamos el adaptador

Simplemente creamos un adaptador a partir de: el contexto (this), el layout del spinner item y un arreglo de String.

```java
final String[] datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};

// prestar especial atencion en el layout provisto por android para list views:
ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
```

### 2. Lo utilizamos

Obtenemos la instancia de la vista *ListView* y le seteamos el adaptador.

```java
ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones)
lstOpciones.setAdapter(adaptador);
```

### 3. Escuchando eventos de click sobre la vista

```java
lstOpciones.setOnItemClickListener(new OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        //Acciones necesarias al hacer click
    }
});
```


# Escuchando el cambio de la lista de opciones

Con una nueva estructura de datos, un mapa de clave, valor con la lista de equipos y de clave la marca. Escuchamos la lista de opciones y mantenemos un elemento seleccionado para saber que lista renderizar.

## Modificamos la estructura de datos y escuchamos la seleccion

### 1. Agregamos una estructura de datos del tipo mapa y el seleccionado

```java
// class
    private Map<String,String[]> mTelefonosMap;
    private String marcaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTelefonosMap = new HashMap<>();
        mTelefonosMap.put("Motorola", new String[]{
                "Moto G", "Moto X"
        });
        mTelefonosMap.put("Samsung", new String[]{
                "Galaxy J2", "Galaxy S7"
        });
        mTelefonosMap.put("Sony", new String[]{
                "Xperia M4 Aqua", "Xperia M5"
        });
        marcaSeleccionada = "Motorola";


        adaptadorOpciones();
        adaptadorLista();
    }
```

### 2. Modificamos el metodo de adaptador de opciones

```java
    private void adaptadorOpciones() {
        final String[] datos = new String[]{"Motorola", "Samsung", "Sony"};
        final ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        final Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
        adaptador.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);

        cmbOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                marcaSeleccionada = datos[i];
                adaptadorLista();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
```

### 3. Luego el metodo donde se adapta la lista.

```java
    private void adaptadorLista() {
        final String[] datos = mTelefonosMap.get(marcaSeleccionada);

        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);
        
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Item seleccionado" + adaptador.getItem(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
```
