package br.usjt.desenvmob.aula03.model;

/**
 * Created by paulo guilherme da silva on 11/04/2018.
 * 816113977
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static br.usjt.desenvmob.aula03.model.FilaDbContract.FilaBanco;

public class FilaDbHelper extends SQLiteOpenHelper{

    public static final Integer DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Fila.db";

    public FilaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String SQL_CREATE_FILA =
            "CREATE TABLE " + FilaBanco.TABLE_NAME + " ( " +
                    FilaBanco._ID + " INTEGER PRIMARY KEY, " +
                    FilaBanco.ID_FILA + " INTEGER, " +
                    FilaBanco.NM_FILA + " TEXT," +
                    FilaBanco.NM_FIGURA + " TEXT, " +
                    FilaBanco.IMG_FIGURA + "BLOB )";

    public static final String SQL_DROP_FILA =
            "DROP TABLE IF EXISTS " + FilaBanco.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FILA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_FILA);
        db.execSQL(SQL_CREATE_FILA);
    }
}
