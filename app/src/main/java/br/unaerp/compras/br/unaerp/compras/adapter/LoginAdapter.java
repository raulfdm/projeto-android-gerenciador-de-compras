package br.unaerp.compras.br.unaerp.compras.adapter;

import org.json.JSONException;
import org.json.JSONStringer;

import br.unaerp.compras.br.unaerp.compras.model.LoginModel;

/**
 * Created by raul on 12/6/2016.
 */
public class LoginAdapter {

    public String converteJson(LoginModel usuario) {
        JSONStringer js = new JSONStringer();

        try {
            js.object();
            js.key("usuario").value(usuario.getUsuario());
            js.key("senha").value(usuario.getSenha());
            js.endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
