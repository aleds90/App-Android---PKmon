package com.aleds90.android.pokemonhelper.database;

import android.content.Context;
import android.database.sqlite.*;
import android.util.StringBuilderPrinter;

/*
    Classe che si occupa di tutte le funzionalita' db related.
 */
public class SQLHelper extends SQLiteOpenHelper{
    //MARK: Properties
    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME    = "PokemonHelper";

    public static final String GYM_TABLE = "gym";
    public static final String POKEMON_TABLE = "pokemon";

    public static final String CREATE_GYM = "CREATE TABLE "+ GYM_TABLE+" " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "address VARCHAR(100), longitude REAL, latitude REAL, level INT, notes VARCHAR(100))";

    public static final String CREATE_POKEMON = "CREATE TABLE "+ POKEMON_TABLE +" " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR(45), cp INT, gym INT,  "
            + "FOREIGN KEY( gym ) REFERENCES "
            + GYM_TABLE + "(id) " + ")";

    //MARK: Contructores
    public SQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static SQLHelper instance;

    public static synchronized SQLHelper getHelper(Context context) {
        if (instance == null)
            instance = new SQLHelper(context);
        return instance;
    }

    //MARK: Override SQLiteOpenHelper Methods
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_GYM);
        db.execSQL(CREATE_POKEMON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
