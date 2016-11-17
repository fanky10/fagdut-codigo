package ar.org.fagdut.android.codigo.ejercicio_base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ar.org.fagdut.android.codigo.ejercicio_base.data.CharacterModel;
import ar.org.fagdut.android.codigo.ejercicio_base.data.CharactersStaticRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ListView listView = (ListView) findViewById(R.id.listView);
        List<CharacterModel> charactersList = new CharactersStaticRepository(this).findAll();
        final CharactersAdapter charactersAdapter = new CharactersAdapter(this, charactersList);

        listView.setAdapter(charactersAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToDetail(charactersAdapter.getItem(i));
            }
        });
    }

    private void goToDetail(CharacterModel characterModel) {
        String search = characterModel.getName();
        startActivity(DetailActivity.getIntent(this, search));
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
