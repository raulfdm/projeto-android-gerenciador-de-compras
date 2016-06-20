package br.unaerp.compras.br.unaerp.compras.model;

import android.support.v4.app.FragmentActivity;

/**
 * Created by raul on 19/6/2016.
 */
public class BackupModel {
    private FragmentActivity context;
    private ClienteModel cliente;
    private ProdutoModel produto;
    private FornecedorModel fornecedor;

    public BackupModel() {
    }

    public void setContext(FragmentActivity context) {
        this.context = context;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public void setFornecedor(FornecedorModel fornecedor) {
        this.fornecedor = fornecedor;
    }

    public FragmentActivity getContext() {
        return context;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public FornecedorModel getFornecedor() {
        return fornecedor;
    }
}
