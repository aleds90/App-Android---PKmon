package com.aleds90.android.pokemonhelper.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.aleds90.android.pokemonhelper.database.SQLConnection;
import com.aleds90.android.pokemonhelper.database.SQLHelper;

import java.util.ArrayList;

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

    public ArrayList<Pokemon> getPokemons() {
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
        String query = "SELECT * FROM "
                + SQLHelper.POKEMON_TABLE + " pokemon, "
                + SQLHelper.GYM_TABLE + " gym WHERE pokemon.gym"
                + " = gym.id";

        // Building query using INNER JOIN keyword
        /*String query = "SELECT " + EMPLOYEE_ID_WITH_PREFIX + ","
        + EMPLOYEE_NAME_WITH_PREFIX + "," + DataBaseHelper.EMPLOYEE_DOB
        + "," + DataBaseHelper.EMPLOYEE_SALARY + ","
        + DataBaseHelper.EMPLOYEE_DEPARTMENT_ID + ","
        + DEPT_NAME_WITH_PREFIX + " FROM "
        + DataBaseHelper.EMPLOYEE_TABLE + " emp INNER JOIN "
        + DataBaseHelper.DEPARTMENT_TABLE + " dept ON emp."
        + DataBaseHelper.EMPLOYEE_DEPARTMENT_ID + " = dept."
        + DataBaseHelper.ID_COLUMN;*/

        Log.d("query", query);
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(cursor.getInt(0));
            pokemon.setName(cursor.getString(1));
            pokemon.setCp(cursor.getInt(2));

            Gym gym = new Gym();
            gym.setId(cursor.getInt(4));
            gym.setAddress(cursor.getString(5));
            gym.setLongitude(cursor.getDouble(6));
            gym.setLatitude(cursor.getDouble(7));
            gym.setLevel(cursor.getInt(8));
            gym.setNotes(cursor.getString(9));
            pokemon.setGym(gym);

            pokemons.add(pokemon);
        }
        return pokemons;
    }

    public int deletePokemon(Pokemon pokemon) {
        return database.delete(SQLHelper.POKEMON_TABLE,
                WHERE_ID_EQUALS, new String[]{pokemon.getId() + ""});
    }

    public long update(Pokemon pokemon) {
        ContentValues values = new ContentValues();
        values.put("name", pokemon.getName());
        values.put("cp", pokemon.getCp());
        long result = database.update(SQLHelper.POKEMON_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(pokemon.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }
}
