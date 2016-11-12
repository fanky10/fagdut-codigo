package ar.org.fagdut.android.codigo.ejercicio_base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import ar.org.fagdut.android.codigo.ejercicio_base.data.CharacterModel;
import ar.org.fagdut.android.codigo.ejercicio_base.data.CharactersStaticRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        CharacterModel[] charactersList = CharactersStaticRepository.findAll();
        CharactersAdapter charactersAdapter = new CharactersAdapter(this, charactersList);

        listView.setAdapter(charactersAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_action_search) {
            goToSearch();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
