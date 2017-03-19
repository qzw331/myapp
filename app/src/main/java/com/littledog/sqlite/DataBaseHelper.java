package com.littledog.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qzw on 2017/3/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="pizza";//database name
    private static final int DB_VERSION=1;//the version of database
    DataBaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
