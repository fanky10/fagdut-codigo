package ar.org.fagdut.codigo.android.persistencia;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

public class ListaActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseDeDatos bdd = new BaseDeDatos(this);
        bdd.open();
        List<String> valores = bdd.getValores();
        bdd.close();

        // Create an ArrayAdapter, that will actually make the Strings above
        // appear in the ListView
        this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, valores.toArray(new String[0])));
    }

}
