package com.javier.android.frasesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USUARIOR on 26/05/2016.
 */
public class QuotesReaderDbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Quotes.db";
    public static final int DATABASE_VERSION = 1;

    public QuotesReaderDbHelper(Context context){
        super(context,
                DATABASE_NAME,
                null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        //creacion de la base de datos
        db.execSQL(QuotesDataSource.CREATE_QUOTES_SCRIPT);
        db.execSQL(QuotesDataSource.INSERT_QUOTES_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
