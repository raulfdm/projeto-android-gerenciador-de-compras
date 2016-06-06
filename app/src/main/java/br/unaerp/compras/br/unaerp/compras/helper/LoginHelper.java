package br.unaerp.compras.br.unaerp.compras.helper;

import android.widget.EditText;

import br.unaerp.compras.LoginActivity;
import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.model.LoginModel;

/**
 * Created by Raul on 13/04/2016.
 */
public class LoginHelper {

    private final EditText campoUsuario;
    private final EditText campoSenha;
    private  LoginModel login;

    public LoginHelper(LoginActivity activity){
        campoUsuario = (EditText) activity.findViewById(R.id.login_nome);
        campoSenha = (EditText) activity.findViewById(R.id.login_senha);
        login = new LoginModel();
    }

    public LoginModel getUsuario(){
        login.setUsuario(campoUsuario.getText().toString());
        login.setSenha(campoSenha.getText().toString());

        return login;
    }

    public String validaFormLogin(){
        if(campoUsuario.getText().toString() == null || campoUsuario.getText().toString().isEmpty()){
            return "Usuário ou E-mail";
        }else if(campoSenha.getText().toString() == null || campoSenha.getText().toString().isEmpty()){
            return "Senha";
        }

        return null;
    }
}
