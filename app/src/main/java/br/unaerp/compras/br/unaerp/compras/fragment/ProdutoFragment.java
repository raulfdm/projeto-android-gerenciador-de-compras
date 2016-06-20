package br.unaerp.compras.br.unaerp.compras.fragment;


import android.content.Intent;
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
import br.unaerp.compras.br.unaerp.compras.dao.ProdutoDAO;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioProduto;
import br.unaerp.compras.br.unaerp.compras.interfaces.OnFragmentInteractionListener;
import br.unaerp.compras.br.unaerp.compras.model.ProdutoModel;


public class ProdutoFragment extends Fragment {

    private ListView listaProdutos;
    private View viewPrincipal;
    private OnFragmentInteractionListener mListener;

    public ProdutoFragment() {
        // Required empty public constructor
    }

    public static ProdutoFragment newInstance() {
        ProdutoFragment fragment = new ProdutoFragment();
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
            mListener.onFragmentInteraction(getString(R.string.title_fragment_produtos));
        }

        this.viewPrincipal = inflater.inflate(R.layout.fragment_produto, container, false);
        carregaListaDeProdutos();

        com.github.clans.fab.FloatingActionButton adicionaFornecedor = (FloatingActionButton) viewPrincipal.findViewById(R.id.produto_add);
        adicionaFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProdutoFragment.this.getActivity(), FormularioProduto.class);
                startActivity(i);
            }
        });


        return viewPrincipal;
    }

    private void carregaListaDeProdutos() {
        ProdutoDAO dao = new ProdutoDAO(this.getActivity(), MainActivity.versaoBD);
        List<ProdutoModel> produtos = dao.selectProdutos();
        listaProdutos = (ListView) viewPrincipal.findViewById(R.id.view_produto_lista);
        ArrayAdapter<ProdutoModel> adapter = new ArrayAdapter<ProdutoModel>(this.getActivity(), android.R.layout.simple_list_item_1, produtos);
        listaProdutos.setAdapter(adapter);

        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                ProdutoModel produto = (ProdutoModel) listaProdutos.getItemAtPosition(position);
                Intent editaProduto = new Intent(ProdutoFragment.this.getActivity(), FormularioProduto.class);
                editaProduto.putExtra("produto", produto);
                startActivity(editaProduto);
            }
        });
    }

    @Override
    public void onResume() {
        carregaListaDeProdutos();
        registerForContextMenu(listaProdutos);

        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        /*Instancia o objeto cliente*/
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final ProdutoModel produto = (ProdutoModel) listaProdutos.getItemAtPosition(info.position);

        /*LISTA DE MENUS*/

        MenuItem deletar = menu.add("Deletar");

        /*Deletar produto*/
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ProdutoDAO dao = new ProdutoDAO(ProdutoFragment.this.getActivity(),MainActivity.versaoBD);
                String nomeProduto = produto.getDescricao();
                if(dao.deleteProduto(produto)){
                    Toast.makeText(ProdutoFragment.this.getActivity(), "Produto "+nomeProduto + " removido com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ProdutoFragment.this.getActivity(), "Erro ao excluir o produto", Toast.LENGTH_SHORT).show();
                }
                dao.close();
                carregaListaDeProdutos();
                return false;
            }
        });

    }

}
