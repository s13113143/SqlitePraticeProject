package com.example.leon.sqlitepraticeproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends AppCompatActivity {

    EditText edt_account, edt_password;
    Button btn_login, btn_register, btn_show, btn_back;
    RegisterDAO registerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findV();


        final String str_account, str_password;
        str_account = edt_account.getText().toString();
        str_password = edt_password.getText().toString();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDAO = new RegisterDAO(Main.this);
                Boolean login_flag = registerDAO.CheckLogin(str_account, str_password);
                if (login_flag){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(Main.this);
                    dialog.setTitle("系統");                                                    //標題
                    dialog.setMessage("登入成功");  //內容
                    dialog.setCancelable(false);                                                //Dialog畫面以外範圍是否可以控制
                    //Negative按鈕
                    dialog.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialog.show();
                            Intent intent = new Intent();
                            intent.setClass(Main.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    dialog.show();
                }
                else{
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(Main.this);
                    dialog.setTitle("系統");                                                    //標題
                    dialog.setMessage("登入失敗，請在檢查帳號密碼是否有誤。");  //內容
                    dialog.setCancelable(false);                                                //Dialog畫面以外範圍是否可以控制
                    //Negative按鈕
                    dialog.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    dialog.show();
                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Show.class);
                startActivity(intent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findV(){
        edt_account = (EditText) findViewById(R.id.edt_account);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_back = (Button) findViewById(R.id.btn_back);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK){
            final AlertDialog.Builder dialog = new AlertDialog.Builder(Main.this);
            dialog.setTitle("系統");                                                    //標題
            dialog.setMessage("你確定要離開嗎？");  //內容
            dialog.setCancelable(false);                                                //Dialog畫面以外範圍是否可以控制
            //Negative按鈕
            dialog.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            dialog.setPositiveButton("不要離開", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();

        }
        return super.onKeyDown(keyCode, event);
    }
}
