package ar.org.fagdut.android.codigo.ejercicio_base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String CHARACTER_NAME_KEY = "characterNameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    private void init() {
        String statusMessage = "Nombre no encontrado";
        if (getIntent() != null && getIntent().hasExtra(CHARACTER_NAME_KEY)) {
            statusMessage = "Nombre encontrado: "+getIntent().getStringExtra(CHARACTER_NAME_KEY);
        }

        TextView txtCharacterName = (TextView) findViewById(R.id.txt_character_name);
        txtCharacterName.setText(statusMessage);
    }
}
