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

### 1. Agregamos un escuchador

```java

    private Map<String,String[]> mTelefonosMap;

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


        initOpciones();
        initList("Motorola");
    }

    private void initOpciones() {
        final String[] datos = new String[]{"Motorola", "Samsung", "Sony"};
        final ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        final Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
        adaptador.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);

        cmbOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initList(datos[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initList(String marca) {
        final String[] datos = mTelefonosMap.get(marca);

        // prestar especial atencion en el layout provisto por android para list views:
        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);
    }
```
