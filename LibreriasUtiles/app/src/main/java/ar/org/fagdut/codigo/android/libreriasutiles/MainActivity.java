package ar.org.fagdut.codigo.android.libreriasutiles;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    Call<List<RepositorioModel>> call = gitHubService.listRepos("fanky10");
    call.enqueue(new Callback<List<RepositorioModel>>() {
      @Override
      public void onResponse(Call<List<RepositorioModel>> call, Response<List<RepositorioModel>> response) {
        actualizaDatos(response.body());
      }

      @Override
      public void onFailure(Call<List<RepositorioModel>> call, Throwable t) {
        Log.e(TAG,"on failure retrofit "+t.getMessage());
      }
    });
  }
  // ejecutado cuando recupera los datos
  private void actualizaDatos(List<RepositorioModel> repositorios) {
    Log.d(TAG,"repositorios "+repositorios);
    Toast.makeText(MainActivity.this, "Cantidad encontrada: "+repositorios.size(), Toast.LENGTH_SHORT).show();
    setListAdapter(new RepositoriosAdapter(this, repositorios));
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    // Get the item that was clicked
    RepositorioModel model = (RepositorioModel) this.getListAdapter().getItem(position);
    buscarBranches(model.mNombre);
  }



  private void buscarBranches(String repositorio) {
    Call<List<BranchModel>> call = gitHubService.listBranches("fanky10", repositorio);
    call.enqueue(new Callback<List<BranchModel>>() {
      @Override
      public void onResponse(Call<List<BranchModel>> call, Response<List<BranchModel>> response) {
        mostrarBranches(response.body());
      }

      @Override
      public void onFailure(Call<List<BranchModel>> call, Throwable t) {
        Log.e(TAG,"on failure retrofit "+t.getMessage());
      }
    });
  }

  private void mostrarBranches(List<BranchModel> branches) {
    // Use the Builder class for convenient dialog construction
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Branches: "+branches);
    // Create the AlertDialog object and return it
    builder.create().show();
  }


  // Retrofit service impl. puede ser movido a otras respectivas clases

  public interface GitHubService {
    @GET("users/{user}/repos") Call<List<RepositorioModel>> listRepos(@Path("user") String user);
    @GET("repos/{user}/{repos}/branches")
    Call<List<BranchModel>> listBranches(@Path("user") String user, @Path("repos") String repos);
  }
}
