package ar.org.fagdut.android.codigo.ejercicio_base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void search(View v) {
        EditText etCharacterNameSearch = (EditText) findViewById(R.id.et_character_name);
        String search = etCharacterNameSearch.getText().toString();
        startActivity(DetailActivity.getIntent(this, search));
    }
}
