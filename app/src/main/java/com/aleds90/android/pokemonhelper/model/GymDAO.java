package com.aleds90.android.pokemonhelper.model;

import android.content.ContentValues;
import android.content.Context;

import com.aleds90.android.pokemonhelper.database.SQLConnection;
import com.aleds90.android.pokemonhelper.database.SQLHelper;

public class GymDAO extends SQLConnection {

    private static final String WHERE_ID_EQUALS =  "id =?";

    public GymDAO(Context context) {
        super(context);
    }

    public long save(Gym gym) {
        ContentValues values = new ContentValues();
        values.put("address", gym.getAddress());
        values.put("longitude", gym.getLongitude());
        values.put("latitude", gym.getLatitude());
        values.put("level", gym.getLevel());
        values.put("notes", gym.getNotes());

        return database.insert(SQLHelper.GYM_TABLE, null, values);
    }

}
