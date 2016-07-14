package com.aleds90.android.pokemonhelper.model;

import android.content.ContentValues;
import android.content.Context;

import com.aleds90.android.pokemonhelper.database.SQLConnection;
import com.aleds90.android.pokemonhelper.database.SQLHelper;

public class PokemonDAO extends SQLConnection{

    private static final String WHERE_ID_EQUALS =  "id =?";

    public PokemonDAO(Context context) {
        super(context);
    }

    public long save(Pokemon pokemon) {
        ContentValues values = new ContentValues();
        values.put("name", pokemon.getName());
        values.put("cp", pokemon.getCp());
        values.put("gym", pokemon.getGym().getId());

        return database.insert(SQLHelper.POKEMON_TABLE, null, values);
    }
}
