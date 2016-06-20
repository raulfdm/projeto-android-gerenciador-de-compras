package br.unaerp.compras.br.unaerp.compras.model;

import java.io.Serializable;

/**
 * Created by raul on 17/6/2016.
 */
public class ProdutoModel implements Serializable{

    private Long id;
    private String fornecedor;
    private String codigo;
    private String marca;
    private String descricao;
    private String tamanho;

    public ProdutoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return getDescricao() + " - "+getTamanho();
    }
}
