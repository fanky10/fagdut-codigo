package ar.org.fagdut.codigo.android.libreriasutiles;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends ListActivity {

  private static final String TAG = MainActivity.class.getName();

  GitHubService gitHubService;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Create an array of Strings, that will be put to our ListActivity
    String[] names = new String[]{"Loading data"};

    // Create an ArrayAdapter, that will actually make the Strings above
    // appear in the ListView
    this.setListAdapter(new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, names));

    inicializaServicios();

    cargarDatos();

  }

  private void inicializaServicios() {
    // Creamos el cliente que utilizaremos en la actividad.
    // primero instanciamos el constructor
    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl(getString(R.string.github_base_url))
        .addConverterFactory(GsonConverterFactory.create());
    // luego construimos lo que queremos.
    Retrofit retrofit = builder.build();
    gitHubService =  retrofit.create(GitHubService.class);
  }

  private void cargarDatos() {
    Call<List<RepoModel>> call = gitHubService.listRepos("fanky10");
    call.enqueue(new Callback<List<RepoModel>>() {
      @Override
      public void onResponse(Call<List<RepoModel>> call, Response<List<RepoModel>> response) {
        Log.d(TAG,"on response retrofit "+response.body());
        Toast.makeText(MainActivity.this, "Cantidad encontrada: "+response.body().size(), Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onFailure(Call<List<RepoModel>> call, Throwable t) {
        Log.e(TAG,"on failure retrofit "+t.getMessage());
      }
    });
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    // Get the item that was clicked
    Object o = this.getListAdapter().getItem(position);
    String keyword = o.toString();
    Toast.makeText(this, "Has Seleccionado " + keyword, Toast.LENGTH_LONG)
        .show();
  }

  // Retrofit service impl. puede ser movido a otras respectivas clases

  public interface GitHubService {
    @GET("users/{user}/repos") Call<List<RepoModel>> listRepos(@Path("user") String user);
  }

  public class RepoModel {
    @SerializedName("name")
    @Expose
    String mName;

    @SerializedName("private")
    @Expose
    Boolean mPrivate;

    @SerializedName("description")
    @Expose
    String mDescription;
  }

}
