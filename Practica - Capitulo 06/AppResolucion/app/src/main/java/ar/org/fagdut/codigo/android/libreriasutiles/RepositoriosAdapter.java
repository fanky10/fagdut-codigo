package ar.org.fagdut.codigo.android.libreriasutiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by fanky on 7/23/16.
 */
public class RepositoriosAdapter extends ArrayAdapter<RepositorioModel> {
  Context mContext;
  public RepositoriosAdapter(Context context, List<RepositorioModel> repositorios) {
    super(context, R.layout.repositorio_item_layout, repositorios);
    mContext = context;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View item = inflater.inflate(R.layout.repositorio_item_layout, null);

    TextView lblNombre = (TextView) item.findViewById(R.id.txt_nombre);
    lblNombre.setText(getItem(position).mNombre);

    TextView lblDesc = (TextView) item.findViewById(R.id.txt_descripcion);
    lblDesc.setText(getItem(position).mDescripcion);

    return item;
  }
}
