package com.aleds90.android.pokemonhelper.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.*;

public class SQLConnection {

    protected SQLiteDatabase database;
    private SQLHelper dbHelper;
    private Context mContext;

    public SQLConnection(Context context) {
        this.mContext = context;
        dbHelper = SQLHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = SQLHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }
}

