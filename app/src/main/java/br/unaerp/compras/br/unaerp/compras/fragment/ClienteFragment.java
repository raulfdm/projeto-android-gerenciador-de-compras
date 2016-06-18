package br.unaerp.compras.br.unaerp.compras.fragment;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.Encript;
import br.unaerp.compras.br.unaerp.compras.atividades.MainActivity;
import br.unaerp.compras.br.unaerp.compras.dao.ClienteDAO;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioClienteActivity;
import br.unaerp.compras.br.unaerp.compras.interfaces.OnFragmentInteractionListener;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;
import br.unaerp.compras.br.unaerp.compras.tasks.EnviaClienteTask;

public class ClienteFragment extends Fragment  {

    private ListView listaClientes;
    private View viewPrincipal;
    private OnFragmentInteractionListener mListener;


    public ClienteFragment() {
        // Required empty public constructor
    }

    public static ClienteFragment newInstance() {
        ClienteFragment fragment = new ClienteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            mListener = (OnFragmentInteractionListener) super.getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(super.getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mListener != null) {
            mListener.onFragmentInteraction(getString(R.string.title_fragment_clientes));
        }

        this.viewPrincipal = inflater.inflate(R.layout.fragment_cliente,container,false);
        carregaListaClientes();

        com.github.clans.fab.FloatingActionButton adicionaCliente = (FloatingActionButton) viewPrincipal.findViewById(R.id.ativ_cliente_add);
        adicionaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClienteFragment.this.getActivity(), FormularioClienteActivity.class);
                startActivity(i);
            }
        });

        return viewPrincipal;
    }

    @Override
    public void onResume() {
        carregaListaClientes();
        registerForContextMenu(listaClientes);

        super.onResume();
    }

    private void carregaListaClientes() {
        ClienteDAO dao = new ClienteDAO(this.getActivity(),MainActivity.versaoBD);
        List<ClienteModel> clientes = dao.selectCliente();
        listaClientes = (ListView) viewPrincipal.findViewById(R.id.view_cliente_lista);
        ArrayAdapter<ClienteModel> adapter = new ArrayAdapter<ClienteModel>(this.getActivity(), android.R.layout.simple_list_item_1, clientes);
        listaClientes.setAdapter(adapter);

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                ClienteModel cliente = (ClienteModel) listaClientes.getItemAtPosition(position);
                Intent editaCliente = new Intent(ClienteFragment.this.getActivity(), FormularioClienteActivity.class);
                editaCliente.putExtra("cliente", cliente);
                startActivity(editaCliente);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        /*Instancia o objeto cliente*/
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final ClienteModel cliente = (ClienteModel) listaClientes.getItemAtPosition(info.position);

        /*LISTA DE MENUS*/
        MenuItem mandaEmail = menu.add("Enviar e-mail"); //Cria a opção no menu

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
                ClienteDAO dao = new ClienteDAO(ClienteFragment.this.getActivity(),MainActivity.versaoBD);
                dao.deleteCliente(cliente);
                dao.close();
                carregaListaClientes();
                return false;
            }
        });



        informacao.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            String nome = "Raul";
            String resultado = Encript.encripta(nome);
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ClienteFragment.this.getActivity(), resultado, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


}
