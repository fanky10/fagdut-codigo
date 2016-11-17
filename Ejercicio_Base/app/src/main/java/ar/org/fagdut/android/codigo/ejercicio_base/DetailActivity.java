package ar.org.fagdut.android.codigo.ejercicio_base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ar.org.fagdut.android.codigo.ejercicio_base.data.CharactersStaticRepository;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    private void init() {
        String characterName = CharactersStaticRepository.getData()[0];//find the first one
        TextView txtCharacterName = (TextView) findViewById(R.id.txt_character_name);
        txtCharacterName.setText(characterName);
    }
}
