package br.unaerp.compras.br.unaerp.compras.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import br.unaerp.compras.br.unaerp.compras.adapter.ClienteAdapter;
import br.unaerp.compras.br.unaerp.compras.atividades.MainActivity;
import br.unaerp.compras.br.unaerp.compras.connection.WebClient;
import br.unaerp.compras.br.unaerp.compras.dao.ClienteDAO;
import br.unaerp.compras.br.unaerp.compras.model.BackupModel;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;
import br.unaerp.compras.br.unaerp.compras.model.FornecedorModel;
import br.unaerp.compras.br.unaerp.compras.model.ProdutoModel;

/**
 * Created by raul on 7/6/2016.
 */
public class BackupTask extends AsyncTask<Object,Object,String> {

    private BackupModel backup;
    private Context context;
    private ProgressDialog dialog;

    public BackupTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object[] params) {
        return enviaCliente();
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
//        Log.d("ERRO: ",resposta);
        if(resposta.equals("Sucesso")){
            Toast.makeText(context, "Sincronização concluída", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Erro na sincronização", Toast.LENGTH_SHORT).show();
        }


    }

    private String enviaCliente(){
        ClienteDAO clientedao = new ClienteDAO(context, MainActivity.versaoBD);
        List<ClienteModel> clientes = clientedao.selectCliente();
        clientedao.close();

        ClienteAdapter clienteAdapter = new ClienteAdapter();
        String json = clienteAdapter.converteJson(clientes);

        WebClient cliente = new WebClient();
        String resposta = cliente.post(json);
        //Log.d("CLIENTES JSON:",json);
        return resposta;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context,"Aguarde..","Enviando clientes...",true,true);
    }
}
