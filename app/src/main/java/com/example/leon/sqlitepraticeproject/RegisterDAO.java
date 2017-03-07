package com.example.leon.sqlitepraticeproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Leon on 2017/2/28.
 */
public class RegisterDAO {

    public static final String TAG = "RegisterDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String [] allColumn = {DBHelper.ACCOUNT, DBHelper.PASSWORD, DBHelper.NAME, DBHelper.PHONE};
    private String [] loginColumn = {DBHelper.ACCOUNT, DBHelper.PASSWORD};


    public RegisterDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public long InsetRegister(String account, String password, String name, String phone){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.ACCOUNT, account);
        cv.put(DBHelper.PASSWORD, password);
        cv.put(DBHelper.NAME, name);
        cv.put(DBHelper.PHONE, phone);
        return mDatabase.insert(DBHelper.TABLE_REGISTER, null, cv);
    }

    public Cursor SelectRegister(){
        Cursor cursor = mDatabase.query(DBHelper.TABLE_REGISTER, allColumn, null, null, null, null, null);
        return cursor;
    }

    public Boolean CheckLogin(String account, String password){
        Cursor cursor = mDatabase.query(DBHelper.TABLE_REGISTER,
                loginColumn,
                DBHelper.ACCOUNT + " = " + account + " and " + DBHelper.PASSWORD + " = " +password,
                null,null,null,null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

}
