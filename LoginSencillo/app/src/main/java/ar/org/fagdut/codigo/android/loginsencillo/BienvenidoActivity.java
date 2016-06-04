package ar.org.fagdut.codigo.android.loginsencillo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BienvenidoActivity extends AppCompatActivity {

    public static final String EXTRA_PARAM_NOMBRE = "extraParamNombre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        Bundle params = getIntent().getExtras();
        if (params != null && params.containsKey(EXTRA_PARAM_NOMBRE)) {
            TextView tvWelcome = (TextView) findViewById(R.id.textView);
            tvWelcome.setText("Bienvenido " + params.getString(EXTRA_PARAM_NOMBRE));
        }
    }
}
