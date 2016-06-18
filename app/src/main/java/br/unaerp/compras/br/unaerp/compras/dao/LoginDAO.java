package br.unaerp.compras.br.unaerp.compras.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.unaerp.compras.br.unaerp.compras.atividades.LoginActivity;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;
import br.unaerp.compras.br.unaerp.compras.model.LoginModel;

/**
 * Created by Raul on 13/04/2016.
 */
public class LoginDAO extends SQLiteOpenHelper {
    public LoginDAO(Context context, int version) {
        super(context, "GestaoVendas", null, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //cria tabela de login
        String sql = "create table usuario (" +
                " id integer primary key autoincrement not null," +
                "nomecompleto text not null,"+
                "sexo char not null,"+
                "email text not null,"+
                "  usuario text not null," +
                "  senha text not null," +
                "data_cadastro date not null);";
        db.execSQL(sql);
        //cria usuario default
        sql = "insert into usuario (nomecompleto,sexo,email,usuario, senha, data_cadastro) values ('Raul Felipe de Melo','M','raulfdm@hotmail.com','a','a',date('now'));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists usuario;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertUsuario() {
        //Pendente
    }

    public LoginModel validaAcesso(LoginModel usuario) {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "select nomecompleto, email from usuario where usuario = '"+usuario.getUsuario() +"' and senha = '"+usuario.getSenha()+"';";
        /*String[] colunas = {"usuario", "senha"};
        String[] login = {usuario.getUsuario(), usuario.getSenha()};
        Cursor c_login = db.query("usuario", colunas, "usuario = ? and senha = ?", login, null, null, null);*/

        /*if (c_login.getCount() > 0) {
            validado = true;
        }*/

        Cursor c_login = db.rawQuery(sql, null);
        while (c_login.moveToNext()) {
            usuario.setNomeCompleto(c_login.getString(c_login.getColumnIndex("nomecompleto")));
            usuario.setEmail(c_login.getString(c_login.getColumnIndex("email")));
        }

        return usuario;
    }

    public void startaBD(int versaoBD) {
        SQLiteDatabase db = getWritableDatabase();
        this.onUpgrade(db,db.getVersion(),versaoBD);
    }
}
