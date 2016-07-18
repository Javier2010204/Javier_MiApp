package com.javier.android.frasesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by USUARIOR on 26/05/2016.
 */
public class QuotesDataSource {
    public static final String QUOTES_TABLE_NAME = "Quotes";
    public static final String STRING_TYPE = "text";
    public static final String  INT_TYPE = "integer";

    public static class ColumnQuotes{
        public static final String ID_QUOTES = BaseColumns._ID;
        public static final String BODY_QUOTES = "body";
        public static final String AUTHOR_QUOTES = "author";
    }

    public static final String CREATE_QUOTES_SCRIPT =
            "create table " +QUOTES_TABLE_NAME+"("+
                    ColumnQuotes.ID_QUOTES+" "+INT_TYPE+" primary key autoincrement,"+
                    ColumnQuotes.BODY_QUOTES+" "+STRING_TYPE+" not null,"+
                    ColumnQuotes.AUTHOR_QUOTES+" "+STRING_TYPE+" not null)";

    public static final String INSERT_QUOTES_SCRIPT =
            "insert into "+QUOTES_TABLE_NAME+" values(" +
                    "null, " +
                    "\"El ignorante afirma, el sabio duda y reflexiona\"," +
                    "\"Aristóteles\")," +
                    "(null," +
                    "\"Si buscas resultados distintos, no hagas siempre lo mismo\"," +
                    "\"Albert Einstein\")," +"(null," +
                    "\"Ojo por ojo y todo el mundo acabará ciego\"," +
                    "\"Mahatma Gandhi\")";

    private QuotesReaderDbHelper openHelper;
    private SQLiteDatabase database;

    public QuotesDataSource(Context context){
        openHelper = new QuotesReaderDbHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void saveQuotesRow(String body, String author){
        //Contenedor de valores
        ContentValues values = new ContentValues();

        values.put(QuotesDataSource.ColumnQuotes.BODY_QUOTES, body);
        values.put(QuotesDataSource.ColumnQuotes.AUTHOR_QUOTES, author);

        database.insert(QUOTES_TABLE_NAME, null, values);
    }

    public Cursor getAllQuotes(){
        return database.rawQuery(
                "select * from " + QUOTES_TABLE_NAME,null
        );
    }

    public void deleteQuotesRow(Integer id){
        String selection = ColumnQuotes.BODY_QUOTES + " =? ";
        String[] selectionArgs = {"3"};

        database.delete("Quotes", selection, selectionArgs);
    }

}
