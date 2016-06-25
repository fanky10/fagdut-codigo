package ar.org.fagdut.codigo.android.persistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class DAOActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao);
    }

    public void guardarNuevo(View v) {
        EditText et = (EditText) findViewById(R.id.txt_nuevo_valor);
        String nuevoValor = et.getText().toString();
        BaseDeDatos bdd = new BaseDeDatos(this);
        bdd.open();
        bdd.guardar(nuevoValor);
        bdd.close();
    }

    public void mostrarValores(View v) {
        startActivity(new Intent(this,ListaActivity.class));
    }
}
