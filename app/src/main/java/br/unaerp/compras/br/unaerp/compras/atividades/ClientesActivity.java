package br.unaerp.compras.br.unaerp.compras.atividades;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.Encript;
import br.unaerp.compras.br.unaerp.compras.dao.ClienteDAO;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioClienteActivity;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;
import br.unaerp.compras.br.unaerp.compras.tasks.EnviaClienteTask;

public class ClientesActivity extends AppCompatActivity {

    private ListView listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_clientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        com.github.clans.fab.FloatingActionButton adicionaCliente = (FloatingActionButton) findViewById(R.id.ativ_cliente_add);
        adicionaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClientesActivity.this, FormularioClienteActivity.class);
                startActivity(i);
            }
        });


    }

    private void carregaListaClientes() {
        ClienteDAO dao = new ClienteDAO(this);
        List<ClienteModel> clientes = dao.selectCliente();

        listaClientes = (ListView) findViewById(R.id.view_cliente_lista);
        ArrayAdapter<ClienteModel> adapter = new ArrayAdapter<ClienteModel>(this, android.R.layout.simple_list_item_1, clientes);
        listaClientes.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(this, String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        carregaListaClientes();
        registerForContextMenu(listaClientes);

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                ClienteModel cliente = (ClienteModel) listaClientes.getItemAtPosition(position);
                Intent editaCliente = new Intent(ClientesActivity.this, FormularioClienteActivity.class);
                editaCliente.putExtra("cliente", cliente);
                startActivity(editaCliente);
            }
        });
        super.onResume();
    }

    //Criar menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        /*Instancia o objeto cliente*/
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final ClienteModel cliente = (ClienteModel) listaClientes.getItemAtPosition(info.position);

        /*LISTA DE MENUS*/
        MenuItem mandaEmail = menu.add("Enviar e-mail"); //Cria a opção no menu
        MenuItem enviarSv = menu.add("Enviar para o Servidor");
        MenuItem deletar = menu.add("Deletar");
        MenuItem informacao = menu.add("Informações");


        /*Envia E-mail através do e-mail cadastrado*/
        String emailCliente = "mailto:" + cliente.getEmail();
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO); //ativa a ação de enviar e-mail
        intentEmail.setType("message/rfc822");
        intentEmail.putExtra(Intent.EXTRA_EMAIL, emailCliente);
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "");
        intentEmail.setData(Uri.parse(emailCliente));
        mandaEmail.setIntent(intentEmail);

        /*Deletar contato*/
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ClienteDAO dao = new ClienteDAO(ClientesActivity.this);
                dao.deleteCliente(cliente);
                dao.close();
                carregaListaClientes();
                return false;
            }
        });


        enviarSv.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                EnviaClienteTask enviaClienteTask = new EnviaClienteTask(ClientesActivity.this, cliente);
                enviaClienteTask.execute();
                return Boolean.parseBoolean(null);
            }
        });

        informacao.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            String nome = "Raul";
            String resultado = Encript.encripta(nome);
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ClientesActivity.this, resultado, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


}
