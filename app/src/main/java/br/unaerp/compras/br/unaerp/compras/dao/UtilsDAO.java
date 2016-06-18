package br.unaerp.compras.br.unaerp.compras.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 15/6/2016.
 */
public class UtilsDAO extends SQLiteOpenHelper {
    public UtilsDAO(Context context, int version) {
        super(context, "GestaoVendas", null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table estados("+
                "id integer primary key autoincrement not null,"+
                "estado text not null,"+
                "abreviacao text not null);";

        db.execSQL(sql);
        ArrayList<String> estados = new ArrayList();
        estados.add("insert into estados (estado, abreviacao) values ('Acre','AC');");
        estados.add("insert into estados (estado, abreviacao) values ('Alagoas','AL');");
        estados.add("insert into estados (estado, abreviacao) values ('Amapá','AP');");
        estados.add("insert into estados (estado, abreviacao) values ('Amazonas','AM');");
        estados.add("insert into estados (estado, abreviacao) values ('Bahia','BA');");
        estados.add("insert into estados (estado, abreviacao) values ('Ceará','CE');");
        estados.add("insert into estados (estado, abreviacao) values ('Distrito Federal','DF');");
        estados.add("insert into estados (estado, abreviacao) values ('Espírito Santo','ES');");
        estados.add("insert into estados (estado, abreviacao) values ('Goiás','GO');");
        estados.add("insert into estados (estado, abreviacao) values ('Maranhão','MA');");
        estados.add("insert into estados (estado, abreviacao) values ('Mato Grosso','MT');");
        estados.add("insert into estados (estado, abreviacao) values ('Mato Grosso do Sul','MS');");
        estados.add("insert into estados (estado, abreviacao) values ('Minas Gerais','MG');");
        estados.add("insert into estados (estado, abreviacao) values ('Pará','PA');");
        estados.add("insert into estados (estado, abreviacao) values ('Paraíba','PB');");
        estados.add("insert into estados (estado, abreviacao) values ('Paraná','PR');");
        estados.add("insert into estados (estado, abreviacao) values ('Pernambuco','PE');");
        estados.add("insert into estados (estado, abreviacao) values ('Piauí','PI');");
        estados.add("insert into estados (estado, abreviacao) values ('Rio de Janeiro','RJ');");
        estados.add("insert into estados (estado, abreviacao) values ('Rio Grande do Norte','RN');");
        estados.add("insert into estados (estado, abreviacao) values ('Rio Grande do Sul','RS');");
        estados.add("insert into estados (estado, abreviacao) values ('Rondônia','RO');");
        estados.add("insert into estados (estado, abreviacao) values ('Roraima','RR');");
        estados.add("insert into estados (estado, abreviacao) values ('Santa Catarina','SC');");
        estados.add("insert into estados (estado, abreviacao) values ('São Paulo','SP');");
        estados.add("insert into estados (estado, abreviacao) values ('Sergipe','SE');");
        estados.add("insert into estados (estado, abreviacao) values ('Tocantins','TO');");


        for (String estado: estados) {
            sql = estado;
            db.execSQL(sql);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists estados;";
        db.execSQL(sql);
        onCreate(db);
    }

    public List<String> selectEstado(){
        String sql = "select * from estados order by abreviacao;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c_estado = db.rawQuery(sql,null);
        List<String> estados = new ArrayList<>();

        while(c_estado.moveToNext()){
            String estado = c_estado.getString(c_estado.getColumnIndex("abreviacao"));
             estados.add(estado);
        }
        c_estado.close();

        return estados;
    }

    public void startaBD(int versaoBD) {
        SQLiteDatabase db = getWritableDatabase();
        this.onUpgrade(db,db.getVersion(),versaoBD);
    }
}
