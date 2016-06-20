package br.unaerp.compras.br.unaerp.compras.atividades;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.unaerp.compras.R;
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
        TextView recuperarSenha = (TextView) findViewById(R.id.recuperarSenha);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formValidado = helperLogin.validaFormLogin();

                if(formValidado != null){
                    Toast.makeText(LoginActivity.this, "Por favor, preencha o "+formValidado, Toast.LENGTH_SHORT).show();
                }else {
                    LoginDAO dao = new LoginDAO(LoginActivity.this,MainActivity.versaoBD);
                    LoginModel retorno = dao.validaAcesso(helperLogin.getUsuario());
                    if(retorno.getNomeCompleto().length()>0){
                        Intent abreSistema = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(abreSistema);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Usuário ou Senha Inválidos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        recuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRecuperar = new Intent(Intent.ACTION_VIEW);
                intentRecuperar.setData(Uri.parse("http://google.com"));
                startActivity(intentRecuperar);
            }
        });
    }
    
}