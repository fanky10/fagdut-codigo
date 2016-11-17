/* (c) Disney. All rights reserved. */
package ar.org.fagdut.android.codigo.ejercicio_base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ar.org.fagdut.android.codigo.ejercicio_base.data.CharacterModel;

public class CharactersAdapter extends ArrayAdapter<CharacterModel> {

    public CharactersAdapter(Context context, List<CharacterModel> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CharacterModel characterModel = getItem(position);
        String characterName = characterModel.getName();
        int resourceId = characterModel.getImageResource();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.character_item, parent, false);
        }

        TextView txtName = (TextView) convertView.findViewById(R.id.txt_character_name);
        txtName.setText(characterName);

        ImageView ivCharacter = (ImageView) convertView.findViewById(R.id.imageView);
        ivCharacter.setImageResource(resourceId);

        return convertView;
    }
}