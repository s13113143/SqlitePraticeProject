package com.example.leon.sqlitepraticeproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Leon on 2017/2/28.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_REGISTER = "register";
    public static final String _ID = "_id";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String PHONE = "phone";

    private static final String DATABASE_NAME = "sqlitepraticeproject.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_REGISTER = "CREATE TABLE IF NOT EXISTS " + TABLE_REGISTER + "("
            + _ID + " INTEGER AUTO_INCREMENT PRIMARY KEY, "
            + ACCOUNT + " TEXT, "
            + PASSWORD + " TEXT, "
            + NAME + " TEXT, "
            + PHONE + " TEXT"
            +");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_REGISTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
