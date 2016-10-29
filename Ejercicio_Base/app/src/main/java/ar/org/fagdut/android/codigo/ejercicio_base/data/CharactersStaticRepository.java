/* (c) Disney. All rights reserved. */
package ar.org.fagdut.android.codigo.ejercicio_base.data;

import ar.org.fagdut.android.codigo.ejercicio_base.R;

public class CharactersStaticRepository {
    private static final CharacterModel[] CHARACTERS_LIST = new CharacterModel[]{
            new CharacterModel("The Hound", R.drawable.ic_hound),
            new CharacterModel("John Snow", R.drawable.ic_john_snow),
            new CharacterModel("Sam", R.drawable.ic_samwell)
    };

    public static CharacterModel[] findAll() {
        return CHARACTERS_LIST;
    }

    public static CharacterModel findOne(String name) {
        CharacterModel found = null;

        for (CharacterModel characterModel : findAll()) {
            if (characterModel.getName().toLowerCase().contains(name.toLowerCase())) {
                found = characterModel;
                break;
            }
        }

        return found;
    }
}
