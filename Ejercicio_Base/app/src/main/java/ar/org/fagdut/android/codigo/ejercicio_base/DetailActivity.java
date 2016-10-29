package ar.org.fagdut.android.codigo.ejercicio_base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ar.org.fagdut.android.codigo.ejercicio_base.data.CharactersStaticRepository;

public class DetailActivity extends AppCompatActivity {

    public static final String CHARACTER_NAME_PARAM = "characterNameParam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    private void init() {
        String nameFound = getString(R.string.character_not_found).toLowerCase();

        if (getIntent().hasExtra(CHARACTER_NAME_PARAM)) {
            String nameSearch = getIntent().getStringExtra(CHARACTER_NAME_PARAM);
            for (String data: CharactersStaticRepository.getData()) {
                if (data.toLowerCase().contains(nameSearch)) {
                    nameFound = data;
                    break;
                }
            }
        }

        TextView txtCharacterName = (TextView) findViewById(R.id.txt_character_name);
        txtCharacterName.setText(nameFound);
    }
}
