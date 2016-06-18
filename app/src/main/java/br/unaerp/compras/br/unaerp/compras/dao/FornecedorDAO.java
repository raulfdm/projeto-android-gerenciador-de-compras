package br.unaerp.compras.br.unaerp.compras.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.unaerp.compras.br.unaerp.compras.atividades.MainActivity;
import br.unaerp.compras.br.unaerp.compras.model.FornecedorModel;

/**
 * Created by Raul on 10/04/2016.
 */
public class FornecedorDAO extends SQLiteOpenHelper {

    public FornecedorDAO(Context context, int version) {
        super(context, "GestaoVendas", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table fornecedor (" +
                "id integer primary key autoincrement not null," +
                "nomefantasia text not null," +
                "cnpj text not null," +
                "endereco text not null,"+
                "numero text not null,"+
                "complemento text,"+
                "bairro text,"+
                "cep text,"+
                "cidade text not null,"+
                "estado text not null,"+
                "telefone text,"+
                "site text,"+
                "email text);";
        db.execSQL(sql);
        sql = "insert into fornecedor (nomefantasia, cnpj, endereco,numero,cidade,estado) values ('Doce Festa', '1032132', 'ruasuhashusa','1213','Ribeirão Preto','SP');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists fornecedor;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertFornecedor(FornecedorModel fornecedor) {
        SQLiteDatabase db = getWritableDatabase();//escrever no db
        ContentValues dados = getContentFornecedor(fornecedor);
        db.insert("Fornecedor", null, dados);

    }

    public List<FornecedorModel> selectFornecedor() {
        String sql = "select * from fornecedor order by nomefantasia;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c_Fornecedor = db.rawQuery(sql, null);
        List<FornecedorModel> fornecedores = new ArrayList<FornecedorModel>();

        while (c_Fornecedor.moveToNext()) {
            FornecedorModel fornecedor = new FornecedorModel();
            fornecedor.setId(c_Fornecedor.getLong(c_Fornecedor.getColumnIndex("id")));
            fornecedor.setNomeFantasia(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("nomefantasia")));
            fornecedor.setCnpj(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("cnpj")));
            fornecedor.setEndereco(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("endereco")));
            fornecedor.setNumero(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("numero")));
            fornecedor.setComplemento(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("complemento")));
            fornecedor.setBairro(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("bairro")));
            fornecedor.setCep(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("cep")));
            fornecedor.setCidade(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("cidade")));
            fornecedor.setEstado(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("estado")));
            fornecedor.setTelefone(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("telefone")));
            fornecedor.setSite(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("site")));
            fornecedor.setEmail(c_Fornecedor.getString(c_Fornecedor.getColumnIndex("email")));

            fornecedores.add(fornecedor);
        }
        c_Fornecedor.close();

        return fornecedores;
    }

    public void deleteFornecedor(FornecedorModel fornecedor) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {fornecedor.getId().toString()};
        db.delete("fornecedor", "id = ?", params);
    }

    public void updateFornecedor(FornecedorModel fornecedor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentFornecedor(fornecedor);
        String[] params = {fornecedor.getId().toString()};
        db.update("fornecedor", dados, "id = ?", params);
    }

    private ContentValues getContentFornecedor(FornecedorModel fornecedor) {
        ContentValues dados = new ContentValues();
        dados.put("nomefantasia", fornecedor.getNomeFantasia());
        dados.put("cnpj",fornecedor.getCnpj());
        dados.put("endereco",fornecedor.getEndereco());
        dados.put("numero",fornecedor.getNumero());
        dados.put("complemento",fornecedor.getComplemento());
        dados.put("bairro",fornecedor.getBairro());
        dados.put("cep",fornecedor.getCep());
        dados.put("cidade",fornecedor.getCidade());
        dados.put("estado",fornecedor.getEstado());
        dados.put("telefone",fornecedor.getTelefone());
        dados.put("site",fornecedor.getSite());
        dados.put("email",fornecedor.getEmail());

        return dados;
    }

    public void startaBD(int newVersion){
        SQLiteDatabase db = getWritableDatabase();
        this.onUpgrade(db,db.getVersion(),newVersion);
    }
}
