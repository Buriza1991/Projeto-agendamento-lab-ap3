package com.example.ap3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Nome do banco e versão
    private static final String DATABASE_NAME = "armazenamento.db";
    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE_RESERVAS =
            "CREATE TABLE reservas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "sala TEXT, " +
                    "data TEXT, " +
                    "hora TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESERVAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS reservas");
        onCreate(db);
    }

    public void inserirReserva(String nome, String sala, String data, String hora) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("sala", sala);
        valores.put("data", data);
        valores.put("hora", hora);

        db.insert("reservas", null, valores);
        db.close();
    }
}
