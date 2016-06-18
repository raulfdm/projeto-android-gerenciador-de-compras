package br.unaerp.compras.br.unaerp.compras.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.interfaces.OnFragmentInteractionListener;

public class DashboardFragment extends Fragment {
    private ListView listaFornecedores;
    private View viewPrincipal;
    private OnFragmentInteractionListener mListener;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
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
            mListener.onFragmentInteraction(getString(R.string.title_fragment_dashboard));
        }

        this.viewPrincipal = inflater.inflate(R.layout.fragment_dashboard, container, false);

        return viewPrincipal;
    }

}
