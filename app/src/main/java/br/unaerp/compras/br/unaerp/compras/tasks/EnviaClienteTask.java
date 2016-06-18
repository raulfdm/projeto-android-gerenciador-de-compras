package br.unaerp.compras.br.unaerp.compras.tasks;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import br.unaerp.compras.br.unaerp.compras.adapter.ClienteAdapter;
import br.unaerp.compras.br.unaerp.compras.connection.WebClient;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;

/**
 * Created by raul on 7/6/2016.
 */
public class EnviaClienteTask extends AsyncTask<Object,Object,String> {

    private FragmentActivity context;
    private ClienteModel cliente;

    public EnviaClienteTask(FragmentActivity context, ClienteModel cliente) {
        this.context = context;
        this.cliente = cliente;

    }

    @Override
    protected String doInBackground(Object[] params) {
        ClienteAdapter adapter = new ClienteAdapter();

        String json = adapter.converteJson(cliente);

        WebClient client = new WebClient();
        String resposta = client.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        Toast.makeText(context, resposta, Toast.LENGTH_SHORT).show();
    }
}
