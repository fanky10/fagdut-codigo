# Múltiples Actividades

## Descripción

El alumno se familiarizará con los cambios de actividades, agregar funciones a los objetos y el paso de datos entre actividades mediante *Bundle*

## Precondiciones

- Crear una actividad MainActivity con un Button con android:id="@+id/btn_lanzar_actividad" y un EditTextView con android:id="@+id/txt_parametro_actividad"
- Crear una segunda actividad ResultadoActivity con un TextView con android:id="@+id/txt_texto_actividad"

## 1.1 Lanzando una actividad con parametros

### MainActivity

Obtener la instancia del boton y agregar el escuchador del click

```java
Button boton = (Button) findViewById(R.id.btn_lanzar_actividad);
boton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        lanzarActividad();
    }
});

private void lanzarActividad() {
    Intent intento = new Intent(this, ResultadoActivity.class);
    // par clave valor
    intento.putExtra("textoActividad","Prueba");
    startActivity(intento);
}

```

### ResultadoActivity

Obtener la instancia del TextView y setear el correspondiente valor

```java
// onCreate
String textoActividad = "Sin texto";
Bundle bundle = getIntent().getExtras(); 
if (bundle != null){
    textoActividad = bundle.getString("textoActividad"); 
}

TextView txtTextoActividad = (TextView) findViewById(R.id.txt_texto_actividad);
txtTextoActividad.setText(textoActividad);
```

## 1.2 Obtener texto ingresado del usuario

### MainActivity

Modificamos el metodo: lanzarActividad, de tal forma que el parametro enviado sea lo que ingrese el usuario.

```java
private void lanzarActividad() {
    EditTextView txtParametroActividad = (EditTextView) findViewById(R.id.txt_parametro_actividad);
    String texto = txtParametroActividad.getText();
    Intent intento = new Intent(this, ResultadoActivity.class);
    // par clave valor
    intento.putExtra("textoActividad",texto);
    startActivity(intento);
}

```
