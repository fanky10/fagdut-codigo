package ar.org.fagdut.android.codigo.ejercicio_base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ar.org.fagdut.android.codigo.ejercicio_base.data.CharactersStaticRepository;

public class SearchActivity extends AppCompatActivity {

    private EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        txtName = (EditText) findViewById(R.id.et_character_name);
    }

    public void clickSearch(View v) {
        String inputText = txtName.getText().toString();
        String[] names = CharactersStaticRepository.getData();

        String found = null;
        if (!TextUtils.isEmpty(inputText)) {
            for (String name : names) {
                if (name.toLowerCase().contains(inputText.toLowerCase())) {
                    found = name;
                    break;
                }
            }
        }

        if (found != null) {
            // launch detail activity
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.CHARACTER_NAME_KEY, found);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No encontrado", Toast.LENGTH_SHORT).show();
        }

    }
}
