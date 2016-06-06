package br.unaerp.compras.br.unaerp.compras.model;

/**
 * Created by Raul on 13/04/2016.
 */
public class LoginModel {

    private String usuario;
    private String senha;

    public LoginModel() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
