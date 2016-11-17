package ar.org.fagdut.android.codigo.ejercicio_base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ar.org.fagdut.android.codigo.ejercicio_base.data.CharacterModel;
import ar.org.fagdut.android.codigo.ejercicio_base.data.CharactersStaticRepository;

public class DetailActivity extends AppCompatActivity {

    public static final String CHARACTER_NAME_PARAM = "characterNameParam";

    public static Intent getIntent(Context ctx, String search) {
        Intent intent = new Intent(ctx, DetailActivity.class);
        intent.putExtra(DetailActivity.CHARACTER_NAME_PARAM, search);
        return intent;
    }

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
            CharacterModel characterModel = new CharactersStaticRepository(this).findByName(nameSearch);
            if (characterModel != null) {
                nameFound = characterModel.getName();
            }
        }

        TextView txtCharacterName = (TextView) findViewById(R.id.txt_character_name);
        txtCharacterName.setText(nameFound);
    }
}
