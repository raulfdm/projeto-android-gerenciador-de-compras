package br.unaerp.compras.br.unaerp.compras.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;

/**
 * Created by Raul on 10/04/2016.
 */
public class ClienteDAO extends SQLiteOpenHelper {

    public ClienteDAO(Context context) {
        super(context, "GestaoVendas", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table clientes (" +
                "id integer primary key autoincrement not null," +
                "nome text not null," +
                "sobrenome text not null," +
                "apelido text," +
                "telefone text," +
                "celular text," +
                "endereco text not null," +
                "email text);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists clientes;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertCliente(ClienteModel cliente) {
        SQLiteDatabase db = getWritableDatabase();//escrever no db
        ContentValues dados = getContentCliente(cliente);
        db.insert("Clientes", null, dados);

    }

    public List<ClienteModel> selectCliente() {
        String sql = "select * from clientes order by nome;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c_Cliente = db.rawQuery(sql, null);
        List<ClienteModel> clientes = new ArrayList<ClienteModel>();

        while (c_Cliente.moveToNext()) {
            ClienteModel cliente = new ClienteModel();
            cliente.setId(c_Cliente.getLong(c_Cliente.getColumnIndex("id")));
            cliente.setNome(c_Cliente.getString(c_Cliente.getColumnIndex("nome")));
            cliente.setSobrenome(c_Cliente.getString(c_Cliente.getColumnIndex("sobrenome")));
            cliente.setApelido(c_Cliente.getString(c_Cliente.getColumnIndex("apelido")));
            cliente.setTelefone(c_Cliente.getString(c_Cliente.getColumnIndex("telefone")));
            cliente.setCelular(c_Cliente.getString(c_Cliente.getColumnIndex("celular")));
            cliente.setEndereco(c_Cliente.getString(c_Cliente.getColumnIndex("endereco")));
            cliente.setEmail(c_Cliente.getString(c_Cliente.getColumnIndex("email")));

            clientes.add(cliente);
        }
        c_Cliente.close();

        return clientes;
    }

    public void deleteCliente(ClienteModel cliente) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {cliente.getId().toString()};
        db.delete("clientes", "id = ?", params);
    }

    public void updateCliente(ClienteModel cliente) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentCliente(cliente);
        String[] params = {cliente.getId().toString()};
        db.update("clientes", dados, "id = ?", params);
    }

    private ContentValues getContentCliente(ClienteModel cliente) {
        ContentValues dados = new ContentValues();
        dados.put("nome", cliente.getNome());
        dados.put("sobrenome", cliente.getSobrenome());
        dados.put("apelido", cliente.getApelido());
        dados.put("telefone", cliente.getTelefone());
        dados.put("celular", cliente.getCelular());
        dados.put("endereco", cliente.getEndereco());
        dados.put("email", cliente.getEmail());
        return dados;
    }
}
