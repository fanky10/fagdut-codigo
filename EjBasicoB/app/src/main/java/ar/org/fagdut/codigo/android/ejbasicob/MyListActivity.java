package ar.org.fagdut.codigo.android.ejbasicob;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by fanky on 6/4/16.
 */
public class MyListActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an array of Strings, that will be put to our ListActivity
        String[] names = new String[]{"Linux", "Windows7", "Eclipse", "Suse",
                "Ubuntu", "Solaris", "Android", "iPhone"};

        // Create an ArrayAdapter, that will actually make the Strings above
        // appear in the ListView
        this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        Object o = this.getListAdapter().getItem(position);
        String keyword = o.toString();
        Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG)
                .show();
    }

}