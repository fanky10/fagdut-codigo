/* (c) Disney. All rights reserved. */
package ar.org.fagdut.android.codigo.ejercicio_base.data;

import android.content.Context;

import java.util.List;

public abstract class CharactersRepository {

    protected Context mContext;

    public CharactersRepository(Context context) {
        this.mContext = context;
    }

    public abstract List<CharacterModel> findAll() ;

    public abstract CharacterModel findOne(long id);

    public abstract CharacterModel findByName(String name);
}
