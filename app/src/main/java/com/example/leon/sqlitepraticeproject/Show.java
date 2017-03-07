package com.example.leon.sqlitepraticeproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Leon on 2017/2/28.
 */
public class Show extends AppCompatActivity {

    ListView listView;
    RegisterDAO registerdao;
    ShowItem showItem;

    ArrayList<ShowItem> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        findV();

        registerdao = new RegisterDAO(Show.this);
        Cursor cursor = registerdao.SelectRegister();

        if (cursor.getCount() >0){
            String [] arrayAccount = new String[cursor.getCount()];
            String [] arrayName = new String[cursor.getCount()];
            String [] arrayPhone = new String[cursor.getCount()];
            int i=0;

            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                showItem = new ShowItem();
                showItem.setItemAccount(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT)));
                showItem.setItemName(cursor.getString(cursor.getColumnIndex(DBHelper.NAME)));
                showItem.setItemPhone(cursor.getString(cursor.getColumnIndex(DBHelper.PHONE)));
                arrayList.add(showItem);
                cursor.moveToNext();
            }

            ShowAdapter showAdapter = new ShowAdapter(Show.this, arrayList);
            listView.setAdapter(showAdapter);
        }
        else{
            Log.e("countyo", cursor.getCount()+"0000");
        }

    }

    private void findV(){
        listView = (ListView) findViewById(R.id.show_lv);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Show.this, Main.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
