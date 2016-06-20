package br.unaerp.compras.br.unaerp.compras.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import br.unaerp.compras.br.unaerp.compras.atividades.MainActivity;
import br.unaerp.compras.br.unaerp.compras.dao.ClienteDAO;
import br.unaerp.compras.br.unaerp.compras.dao.FornecedorDAO;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioFornecedor;
import br.unaerp.compras.br.unaerp.compras.interfaces.OnFragmentInteractionListener;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;
import br.unaerp.compras.br.unaerp.compras.model.FornecedorModel;


public class FornecedorFragment extends Fragment {

    private ListView listaFornecedores;
    private View viewPrincipal;
    private OnFragmentInteractionListener mListener;

    public FornecedorFragment() {
    }


    public static FornecedorFragment newInstance() {
        FornecedorFragment fragment = new FornecedorFragment();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.onFragmentInteraction(getString(R.string.title_fragment_fornecedores));
        }

        this.viewPrincipal = inflater.inflate(R.layout.fragment_fornecedor, container, false);
        carregaListaFornecedores();

        com.github.clans.fab.FloatingActionButton adicionaFornecedor = (FloatingActionButton) viewPrincipal.findViewById(R.id.fornecedor_add);
        adicionaFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FornecedorFragment.this.getActivity(), FormularioFornecedor.class);
                startActivity(i);
            }
        });


        return viewPrincipal;
    }

    @Override
    public void onResume() {
        carregaListaFornecedores();
        registerForContextMenu(listaFornecedores);

        super.onResume();
    }

    private void carregaListaFornecedores() {
        FornecedorDAO dao = new FornecedorDAO(this.getActivity(), MainActivity.versaoBD);
        List<FornecedorModel> fornecedores = dao.selectFornecedor();
        listaFornecedores = (ListView) viewPrincipal.findViewById(R.id.view_fornecedor_lista);
        ArrayAdapter<FornecedorModel> adapter = new ArrayAdapter<FornecedorModel>(this.getActivity(), android.R.layout.simple_list_item_1, fornecedores);
        listaFornecedores.setAdapter(adapter);

        listaFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View view, int position, long id) {
                FornecedorModel fornecedor = (FornecedorModel) listaFornecedores.getItemAtPosition(position);
                Intent editaFornecedor = new Intent(FornecedorFragment.this.getActivity(), FormularioFornecedor.class);
                editaFornecedor.putExtra("fornecedor", fornecedor);
                startActivity(editaFornecedor);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final FornecedorModel fornecedor = (FornecedorModel) listaFornecedores.getItemAtPosition(info.position);

        /*LISTA DE MENUS*/
        final MenuItem mandaEmail = menu.add("Enviar e-mail");
        MenuItem acessaSite = menu.add("Acessar site");
        MenuItem deletar = menu.add("Deletar");

        /*Envia E-mail através do e-mail cadastrado*/
        mandaEmail.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(!(fornecedor.getEmail() == null)){
                    String emailFornecedor = "mailto:" + fornecedor.getEmail();
                    Intent intentEmail = new Intent(Intent.ACTION_SENDTO); //ativa a ação de enviar e-mail
                    intentEmail.setType("message/rfc822");
                    intentEmail.putExtra(Intent.EXTRA_EMAIL, emailFornecedor);
                    intentEmail.putExtra(Intent.EXTRA_SUBJECT, "");
                    intentEmail.setData(Uri.parse(emailFornecedor));
                    mandaEmail.setIntent(intentEmail);
                }else{
                    Toast.makeText(FornecedorFragment.this.getActivity(), "Fornecedor "+fornecedor.getNomeFantasia()+" sem email cadastrado", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        acessaSite.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(!(fornecedor.getSite() == null)){
                    Intent intentRecuperar = new Intent(Intent.ACTION_VIEW);
                    intentRecuperar.setData(Uri.parse("http://"+fornecedor.getSite()));
                    startActivity(intentRecuperar);
                }else {
                    Toast.makeText(FornecedorFragment.this.getActivity(), "Fornecedor "+fornecedor.getNomeFantasia()+" sem site cadastrado", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        /*Deletar contato*/
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FornecedorDAO dao = new FornecedorDAO(FornecedorFragment.this.getActivity(), MainActivity.versaoBD);
                dao.deleteFornecedor(fornecedor);
                dao.close();
                carregaListaFornecedores();
                return false;
            }
        });
    }

}
