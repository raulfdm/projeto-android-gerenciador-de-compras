package br.unaerp.compras.br.unaerp.compras.adapter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;

/**
 * Created by raul on 6/6/2016.
 */
public class ClienteAdapter {
    public String converteJson(List<ClienteModel> clientes) {
        JSONStringer js = new JSONStringer();

        try {
            js.array();

            for (ClienteModel cliente : clientes) {
                js.object();
                js.key("_id").value(cliente.getId());
                js.key("nome").value(cliente.getNome());
                js.key("sobrenome").value(cliente.getSobrenome());
                js.key("apelido").value(cliente.getApelido());
                js.key("telefone").value(cliente.getTelefone());
                js.key("celular").value(cliente.getCelular());
                js.key("endereco").value(cliente.getEndereco());
                js.key("email").value(cliente.getEmail());
                js.endObject();
            }

            js.endArray();
            return js.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
