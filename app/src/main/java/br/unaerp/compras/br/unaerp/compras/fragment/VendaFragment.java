package br.unaerp.compras.br.unaerp.compras.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioFornecedorActivity;
import br.unaerp.compras.br.unaerp.compras.interfaces.OnFragmentInteractionListener;


public class VendaFragment extends Fragment {


    private View viewPrincipal;
    private OnFragmentInteractionListener mListener;

    public VendaFragment() {
        // Required empty public constructor
    }


    public static VendaFragment newInstance() {
        VendaFragment fragment = new VendaFragment();
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
            mListener.onFragmentInteraction(getString(R.string.title_fragment_vendas));
        }

        this.viewPrincipal = inflater.inflate(R.layout.fragment_venda, container, false);

        com.github.clans.fab.FloatingActionButton adicionaFornecedor = (FloatingActionButton) viewPrincipal.findViewById(R.id.venda_add);
        adicionaFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VendaFragment.this.getActivity(), FormularioFornecedorActivity.class);
                startActivity(i);
            }
        });


        return viewPrincipal;
    }

}
