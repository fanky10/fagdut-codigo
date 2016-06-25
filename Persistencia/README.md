# Práctica Android de Persistencia

## 1 - Práctica de Shared Preferences.

Crearemos un nuevo proyecto con una actividad vacia:

- Nombre: Persistencia

- Dominio: practica.fagdut.example.com


En el archivo activity_main.xml pegar la siguiente estructura de xml que representa un TextView para el valor actual, un EditText para ingresar un nuevo valor y por ultimo un boton para persistir ese mismo valor.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:orientation="vertical">

    <TextView
        android:id="@+id/txt_valor_guardado"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Ingrese Nuevo Valor" />
    <EditText
        android:id="@+id/txt_nuevo_valor"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
    <Button
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:onClick="guardarNuevo"
        android:text="Guardar Cambios"/>
</LinearLayout>


```


Luego en la Actividad MainActivity.java


```java
public class MainActivity extends AppCompatActivity {

    private static final String KEY_PREFERENCIA_VALOR = "keyPreferenciaValor";
    SharedPreferences preferenciasCompartidas;
    TextView tvValorGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // cargamos el valor guardado al crear la actividad
        tvValorGuardado = (TextView) findViewById(R.id.txt_valor_guardado);
        preferenciasCompartidas = getSharedPreferences("PracticaPersistencia", Context.MODE_PRIVATE);
        // "Sin valor" es el valor por omision si no se encuentra ningun valor guardado
        String valorPersistido = preferenciasCompartidas.getString(KEY_PREFERENCIA_VALOR, "Sin Valor");
        tvValorGuardado.setText(valorPersistido);
    }
    // metodo ejecutado por el boton en onClick
    public void guardarNuevo(View v) {
        EditText et = (EditText) findViewById(R.id.txt_nuevo_valor);
        String nuevoValor = et.getText().toString();
        preferenciasCompartidas.edit().putString(KEY_PREFERENCIA_VALOR, nuevoValor).commit();
        tvValorGuardado.setText(nuevoValor);
    }
}
```

## 2.1 - Práctica de Persistencia en Base de datos SQLite.

Vamos a utilizar el mismo concepto, pero vamos a ingresar en cambio el registro en una tabla de la base de datos.

Crear una nueva clase que sera la encargada de manejar la base de datos:

```java
// implementamos SQLiteOpenHelper clase del sdk que nos permite usar la db
public class BaseDeDatos extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseDAL";
    // nombre a nivel de directorio en Android
    public static final String DATABASE_NAME = "main.db";
    // version de la bdd actual con fines de versionamiento
    public static final int CURRENT_DATABASE_VERSION = 1; 
    // unica tabla sin index ni de valor unico para guardar valores
    public static final String SQL_CREATE_TABLE = "CREATE TABLE valores (valor TEXT)";

    // instancia de la db
    protected SQLiteDatabase mDatabase;

    public BaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_DATABASE_VERSION);
    }

    public void open() throws SQLException {
        mDatabase = getWritableDatabase();
    }

// si no hay base de datos anterior, entonces se crea
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

// si cambia la version entonces se ejecuta un clean y la recreacion de datos.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " + newVersion);

        // clear all data by dropping tables.
        db.execSQL("DROP TABLE IF EXISTS valores");

        // recreate tables.
        db.execSQL(SQL_CREATE_TABLE);
    }
// guardamos un registro sencillo mediante el objeto content values
    public void guardar(String valor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("valor", valor);
        // nombre de tabla, columnHack, values
        mDatabase.insert("valores", null,contentValues);
    }

// devolvemos la lista de valores a partir de un cursor.
    public List<String> getValores() {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM valores", null);
        List<String> found = new ArrayList<String>();

        while (cursor.moveToNext()) {
            String valor = cursor.getString(cursor.getColumnIndex("valor"));
            found.add(valor);
        }

        return found;
    }
}
```


Crear una Actividad nueva que sera la encargada de comunicarse con la bdd

```java
public class DAOActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // utilizamos el mismo layout anterior
        setContentView(R.layout.activity_main);
    }
// el mismo evento pero con distinta funcionalidad
    public void guardarNuevo(View v) {
    // obtenemos el valor como ya es conocido
        EditText et = (EditText) findViewById(R.id.txt_nuevo_valor);
        String nuevoValor = et.getText().toString();
        
        // lo guardamos en la bdd
        BaseDeDatos bdd = new BaseDeDatos(this);
        // se abre la conexion
        bdd.open();
        // se utiliza la bdd
        bdd.guardar(nuevoValor);
        // se cierra
        bdd.close();
        // mostramos los valores
        mostrarValores(null);
    }

    public void mostrarValores(View v) {
        BaseDeDatos bdd = new BaseDeDatos(this);
        bdd.open();
        List<String> valores = bdd.getValores();
        bdd.close();
        // mostramos mediante un toast sencillo.
        Toast.makeText(DAOActivity.this, "Valores: "+valores, Toast.LENGTH_SHORT).show();
    }
}
```

Finalmente ponemos la actividad como MAIN

## 3 - Agregar Actividad de Lista.

Se deja al alumno implementar una actividad que muestre la lista de valores y sea disparada por el formulario alta de valor.
