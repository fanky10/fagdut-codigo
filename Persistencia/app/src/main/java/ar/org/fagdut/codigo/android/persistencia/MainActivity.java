package ar.org.fagdut.codigo.android.persistencia;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_PREFERENCIA_VALOR = "keyPreferenciaValor";
    SharedPreferences preferenciasCompartidas;
    TextView tvValorGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvValorGuardado = (TextView) findViewById(R.id.txt_valor_guardado);
        preferenciasCompartidas = getSharedPreferences("PracticaPersistencia", Context.MODE_PRIVATE);
        String valorPersistido = preferenciasCompartidas.getString(KEY_PREFERENCIA_VALOR, "Sin Valor");
        tvValorGuardado.setText(valorPersistido);
    }

    public void guardarNuevo(View v) {
        EditText et = (EditText) findViewById(R.id.txt_nuevo_valor);
        String nuevoValor = et.getText().toString();
        preferenciasCompartidas.edit().putString(KEY_PREFERENCIA_VALOR, nuevoValor).commit();
        tvValorGuardado.setText(nuevoValor);
        et.setText("");
    }
}
