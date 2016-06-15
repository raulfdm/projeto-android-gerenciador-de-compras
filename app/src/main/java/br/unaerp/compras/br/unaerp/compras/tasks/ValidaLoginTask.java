package br.unaerp.compras.br.unaerp.compras.tasks;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import br.unaerp.compras.br.unaerp.compras.adapter.LoginAdapter;
import br.unaerp.compras.br.unaerp.compras.atividades.LoginActivity;
import br.unaerp.compras.br.unaerp.compras.atividades.MainActivity;
import br.unaerp.compras.br.unaerp.compras.connection.WebClient;
import br.unaerp.compras.br.unaerp.compras.model.LoginModel;

/**
 * Created by raul on 12/6/2016.
 */
public class ValidaLoginTask extends AsyncTask<Object, Object, String> {

    private final LoginModel usuario;
    private final LoginActivity context;


    public ValidaLoginTask(LoginModel usuario, LoginActivity context) {
        this.usuario = usuario;
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        LoginAdapter login = new LoginAdapter();
        String json = login.converteJson(usuario);

        WebClient rest = new WebClient();
        String retorno = rest.logarSistema(json);
        Log.d("CONSOLE.LOG(", retorno.getClass().getName());
        return retorno;
    }

    @Override
    protected void onPostExecute(String retorno) {

        if(Boolean.valueOf(retorno)){
            Intent abreSistema = new Intent(context, MainActivity.class);
            context.startActivity(abreSistema);
            context.finish();
        }else{
            Toast.makeText(context, "Usuário ou Senha Inválidos", Toast.LENGTH_SHORT).show();
        }

    }
}
