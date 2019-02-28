package br.edu.ifpr.stiehl.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtLogin;
    private EditText txtSenha;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(this);

//        txtLogin.setText("admin");
    }

    @Override
    public void onClick(View v) {
        String login = txtLogin.getText().toString();
        String senha = txtSenha.getText().toString();

//        Log.d("BOTAO", "Login: " + login);
//        Log.d("BOTAO", "Senha: " + senha);

        String resultado;
        if (login.equals("admin") && senha.equals("ifpr"))
            resultado = "Login com sucesso!";
        else
            resultado = "Falha de Login!";

        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
    }
}
