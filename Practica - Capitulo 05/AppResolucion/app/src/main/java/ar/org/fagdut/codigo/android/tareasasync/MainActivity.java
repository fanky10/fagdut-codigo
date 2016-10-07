package ar.org.fagdut.codigo.android.tareasasync;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void calcular(View v) {
        EditText etEntrada = (EditText) findViewById(R.id.et_entrada);
        int number = Integer.parseInt(etEntrada.getText().toString());
        new FibonacciTask().execute(number);
    }


    public int fibonacci(int n) {
        if (n==0 || n==1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }


    class FibonacciTask extends AsyncTask<Integer, Void, Integer> {


        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Calculando...", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            return fibonacci(integers[0].intValue());
        }


        protected void onPostExecute(Integer result) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this, "Resultado: "+result, Toast.LENGTH_SHORT).show();
        }
    }
}
