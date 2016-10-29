package ar.org.fagdut.android.codigo.ejercicio_base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToDetail(View v) {
        EditText etCharacterNameSearch = (EditText) findViewById(R.id.et_character_name);
        String search = etCharacterNameSearch.getText().toString();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.CHARACTER_NAME_PARAM, search);
        startActivity(intent);
    }
}
