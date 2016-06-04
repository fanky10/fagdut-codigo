package ar.org.fagdut.codigo.android.loginsencillo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnIngresar;
    private EditText etUsuario, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = (Button) findViewById(R.id.btn_login);
        etUsuario = (EditText) findViewById(R.id.et_usuario);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarUsuario();
            }
        });
    }

    private void verificarUsuario() {
        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();

        if (usuario.equals("admin") && password.equals("admin")) {
            Toast.makeText(LoginActivity.this, "Login ok", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, BienvenidoActivity.class));
            finish();
        } else if (usuario.equals("android") && password.equals("android")) {
            Toast.makeText(LoginActivity.this, "Bienvenido Android!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "Verifique sus datos", Toast.LENGTH_SHORT).show();
        }
    }

}
