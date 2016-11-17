package ar.org.fagdut.android.codigo.ejercicio_base.data;

public class CharacterModel {
    private String mName;
    private int mImageResource;

    CharacterModel(String mName, int mImageResource) {
        this.mName = mName;
        this.mImageResource = mImageResource;
    }

    public String getName() {
        return mName;
    }

    public int getImageResource() {
        return mImageResource;
    }
}
