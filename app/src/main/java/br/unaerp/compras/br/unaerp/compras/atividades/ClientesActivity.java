package br.unaerp.compras.br.unaerp.compras.atividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.dao.ClienteDAO;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioClienteActivity;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;

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
                Intent editaCliente = new Intent(ClientesActivity.this,FormularioClienteActivity.class);
                editaCliente.putExtra("cliente",cliente);
                startActivity(editaCliente);
            }
        });
        super.onResume();
    }

    //Criar menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                ClienteModel cliente = (ClienteModel) listaClientes.getItemAtPosition(info.position);
                ClienteDAO dao = new ClienteDAO(ClientesActivity.this);
                dao.deleteCliente(cliente);
                dao.close();
                carregaListaClientes();
                return false;
            }
        });
    }


}
