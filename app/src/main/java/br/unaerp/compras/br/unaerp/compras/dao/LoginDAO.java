package br.unaerp.compras.br.unaerp.compras.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.unaerp.compras.br.unaerp.compras.model.LoginModel;

/**
 * Created by Raul on 13/04/2016.
 */
public class LoginDAO extends SQLiteOpenHelper {
    public LoginDAO(Context context) {
        super(context, "GestaoVendas", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //cria tabela de login
        String sql = "create table login (" +
                " id integer primary key autoincrement not null," +
                "  usuario text not null," +
                "  senha text not null," +
                "data_cadastro date not null);";
        db.execSQL(sql);
        //cria usuario default
        sql = "insert into login (usuario, senha, data_cadastro) values ('a','a',date('now'));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists login;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertUsuario() {
        //Pendente
    }

    public boolean validaAcesso(LoginModel usuario) {
        boolean validado = false;

        SQLiteDatabase db = getReadableDatabase();
        String[] colunas = {"usuario", "senha"};
        String[] login = {usuario.getUsuario(), usuario.getSenha()};
        Cursor c_login = db.query("login", colunas, "usuario = ? and senha = ?", login, null, null, null);

        if (c_login.getCount() > 0) {
            validado = true;
        }

        return validado;
    }

}
