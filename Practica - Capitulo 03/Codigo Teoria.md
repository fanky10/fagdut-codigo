# Control Spinner

Adaptador del control de spinner

## Definicion XML

```xml
<Spinner
android:id="@+id/CmbOpciones"
￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼android:layout_width="fill_parent"
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