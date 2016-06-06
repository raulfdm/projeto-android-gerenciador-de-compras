package br.unaerp.compras.br.unaerp.compras.model;

import java.io.Serializable;

/**
 * Created by Raul on 10/04/2016.
 */
public class ClienteModel implements Serializable{

    private Long id;
    private String nome;
    private String sobrenome;
    private String apelido;
    private String telefone;
    private String celular;
    private String endereco;
    private String email;

    public ClienteModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        if(getApelido() == null || getApelido().isEmpty()){
            return getNome()+" "+getSobrenome();
        }else{
            return getNome()+" "+getSobrenome()+" "+"("+getApelido()+")";
        }

    }
}
