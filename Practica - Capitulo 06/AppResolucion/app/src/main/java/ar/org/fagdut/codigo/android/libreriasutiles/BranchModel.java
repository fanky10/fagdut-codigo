package ar.org.fagdut.codigo.android.libreriasutiles;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fanky on 7/30/16.
 */
public class BranchModel {
  @SerializedName("name") String mNombre;

  public String toString() {
    return mNombre;
  }
}
