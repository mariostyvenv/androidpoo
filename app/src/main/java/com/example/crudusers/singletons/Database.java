package com.example.crudusers.singletons;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.crudusers.helpers.AdminSQLiteOpenHelper;

public class Database {

    public SQLiteDatabase sqLiteDB;
    private AdminSQLiteOpenHelper dbOpenHelper;

    public Database(Context context) {
        dbOpenHelper = new AdminSQLiteOpenHelper(context);
    }

    public void open() {
        try {
            sqLiteDB = dbOpenHelper.getWritableDatabase();
        } catch (SQLException s) {
            new Exception("Error with DB Open");
        }
    }

    public void close() {
        sqLiteDB.close();
    }
}
