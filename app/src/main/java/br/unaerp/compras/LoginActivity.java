package br.unaerp.compras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import br.unaerp.compras.br.unaerp.compras.dao.LoginDAO;
import br.unaerp.compras.br.unaerp.compras.helper.LoginHelper;
import br.unaerp.compras.br.unaerp.compras.model.LoginModel;

public class LoginActivity extends Activity {

    private LoginHelper helperLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        helperLogin = new LoginHelper(this);

        Button logar = (Button) findViewById(R.id.login_btn_entrar);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formValidado = helperLogin.validaFormLogin();
                LoginDAO db = new LoginDAO(LoginActivity.this);

                if(formValidado != null){
                    Toast.makeText(LoginActivity.this, "Por favor, preencha o "+formValidado, Toast.LENGTH_SHORT).show();
                }else{
                    LoginModel usuario = helperLogin.getUsuario();
                    boolean validado = db.validaAcesso(usuario);
                    if(validado){
                        Intent abreSistema = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(abreSistema);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
    }
    
}