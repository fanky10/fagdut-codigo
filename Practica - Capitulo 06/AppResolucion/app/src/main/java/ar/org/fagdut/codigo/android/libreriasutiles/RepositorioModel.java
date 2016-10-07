package ar.org.fagdut.codigo.android.libreriasutiles;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fanky on 7/23/16.
 */
public class RepositorioModel {
  @SerializedName("name") String mNombre;

  @SerializedName("private") Boolean mEsPrivado;

  @SerializedName("description") String mDescripcion;

  public String toString() {
    return String.format("nombre: %s / privado: %s / desc: %s", mNombre, mEsPrivado, mDescripcion);
  }
}
