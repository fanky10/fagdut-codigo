package ar.org.fagdut.codigo.android.ejbasicob;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initConfig();
    }

    private void initConfig() {
        final String[] datos = new String[] {"Elem1","Elem2","Elem3","Elem4","Elem5"};

        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);

        listOpciones = (ListView) findViewById(R.id.ListOpciones);
        listOpciones.setAdapter(adaptador);

        listOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                String keyword = adaptador.getItem(pos).toString();
                Log.d("EJ_BASICO_B", "id =  " + id);
                Toast.makeText(MainActivity.this, "Has Seleccionado: " + keyword, Toast.LENGTH_LONG).show();

            }
        });
    }
}