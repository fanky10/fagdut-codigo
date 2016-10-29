/* (c) Disney. All rights reserved. */
package ar.org.fagdut.android.codigo.ejercicio_base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CharactersAdapter extends ArrayAdapter<String> {
    public CharactersAdapter(Context context, String[] objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String characterName = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.character_item, parent, false);
        }

        TextView txtName = (TextView) convertView.findViewById(R.id.txt_character_name);
        txtName.setText(characterName);

        return convertView;
    }
}