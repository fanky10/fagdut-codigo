package ar.org.fagdut.android.codigo.ejercicio_base.data;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import ar.org.fagdut.android.codigo.ejercicio_base.R;

public class CharactersStaticRepository extends CharactersRepository {

    private static final CharacterModel[] CHARACTERS_LIST = new CharacterModel[]{
            new CharacterModel("The Hound", R.drawable.ic_hound),
            new CharacterModel("John Snow", R.drawable.ic_john_snow),
            new CharacterModel("Sam", R.drawable.ic_samwell)
    };

    public CharactersStaticRepository(Context context) {
        super(context);
    }

    @Override
    public List<CharacterModel> findAll() {
        return Arrays.asList(CHARACTERS_LIST);
    }

    @Override
    public CharacterModel findOne(long id) {
        throw new UnsupportedOperationException("not supported yet");
    }

    @Override
    public CharacterModel findByName(String name) {
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
