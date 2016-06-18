package br.unaerp.compras.br.unaerp.compras.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;
import br.unaerp.compras.br.unaerp.compras.model.ProdutoModel;

/**
 * Created by raul on 17/6/2016.
 */
public class ProdutoDAO extends SQLiteOpenHelper{

    public ProdutoDAO(Context context, int version) {
        super(context, "GestaoVendas", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table produto (" +
                "id integer primary key autoincrement not null," +
                "codigo text not null," +
                "marca text not null," +
                "descr text not null," +
                "fornecedor text not null," +
                "tamanho text not null);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists produto;";
        db.execSQL(sql);
        onCreate(db);
    }
    public void insertProduto(ProdutoModel produto) {
        SQLiteDatabase db = getWritableDatabase();//escrever no db
        ContentValues dados = getContentProduto(produto);
        db.insert("produto", null, dados);

    }

    public List<ProdutoModel> selectProdutos() {
        String sql = "select * from produto order by nome;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c_produto = db.rawQuery(sql, null);
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

        while (c_produto.moveToNext()) {
            ProdutoModel produto = new ProdutoModel();
            produto.setId(c_produto.getLong(c_produto.getColumnIndex("id")));
            produto.setFornecedor(c_produto.getString(c_produto.getColumnIndex("fornecedor")));
            produto.setCodigo(c_produto.getString(c_produto.getColumnIndex("codigo")));
            produto.setMarca(c_produto.getString(c_produto.getColumnIndex("marca")));
            produto.setDescricao(c_produto.getString(c_produto.getColumnIndex("descr")));
            produto.setTamanho(c_produto.getString(c_produto.getColumnIndex("tamanho")));

            produtos.add(produto);
        }
        c_produto.close();

        return produtos;
    }

    public void updateProduto(ProdutoModel produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentProduto(produto);
        String[] params = {produto.getId().toString()};
        db.update("produto", dados, "id = ?", params);
    }

    private ContentValues getContentProduto(ProdutoModel produto) {
        ContentValues dados = new ContentValues();
        dados.put("codigo", produto.getCodigo());
        dados.put("marca", produto.getMarca());
        dados.put("descr", produto.getDescricao());
        dados.put("fornecedor", produto.getFornecedor());
        dados.put("tamanho", produto.getTamanho());

        return dados;
    }

    public void startaBD(int versaoBD) {
        SQLiteDatabase db = getWritableDatabase();
        this.onUpgrade(db,db.getVersion(),versaoBD);
    }

    public List<String> selectFornecedores(){
        String sql = "select nomefantasia from fornecedor order by nomefantasia;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c_fornecedor = db.rawQuery(sql,null);
        List<String> fornecedores = new ArrayList<>();

        while(c_fornecedor.moveToNext()){
            String estado = c_fornecedor.getString(c_fornecedor.getColumnIndex("nomefantasia"));
            fornecedores.add(estado);
        }
        c_fornecedor.close();

        return fornecedores;
    }

}
