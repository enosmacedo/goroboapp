package br.escolaprogramacao.robotmaker.bluetooth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by allanromanato on 5/27/15.
 */
public class CriaBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "outronome.db";
    public static final String TABELA = "perifericos";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String DESCRICAO = "descricao";
    public static final String ENDERECO = "endereco";
    public static final String TIPO = "tipo";
    public static final int VERSAO = 3;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + "("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + DESCRICAO + " text,"
                + ENDERECO + " text,"
                + TIPO + " integer"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}