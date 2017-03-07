package com.example.leon.sqlitepraticeproject;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Leon on 2017/2/28.
 */
public class Register extends AppCompatActivity {

    EditText edt_account, edt_password, edt_check_password, edt_name, edt_phone;
    Button btn_ok, btn_back;
    String strAccount, strPassword, strCheckPassword, strName, strPhone;
    RegisterDAO registerdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        findV();

        registerdao = new RegisterDAO(Register.this);



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAccount = edt_account.getText().toString();
                strPassword = edt_password.getText().toString();
                strCheckPassword = edt_check_password.getText().toString();
                strName = edt_name.getText().toString();
                strPhone = edt_phone.getText().toString();
                if (!strAccount.equals("")
                        && !strPassword.equals("")
                            && !strCheckPassword.equals("")
                                && !strName.equals("")
                                    && !strPhone.equals("")){
                    if(checkPassword(strPassword, strCheckPassword)){
                        if (registerdao.InsetRegister(strAccount, strPassword, strName, strPhone) > 0){
                            final AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                            dialog.setTitle("系統");                   //標題
                            dialog.setMessage("註冊成功！！！");       //內容
                            dialog.setCancelable(false);               //Dialog畫面以外範圍是否可以控制
                            //Negative按鈕
                            dialog.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent();
                                    intent.setClass(Register.this, Main.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            dialog.show();
                        }
                        else{
                            final AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                            dialog.setTitle("系統");                   //標題
                            dialog.setMessage("註冊失敗！！！");       //內容
                            dialog.setCancelable(false);               //Dialog畫面以外範圍是否可以控制
                            //Negative按鈕
                            dialog.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent();
                                    intent.setClass(Register.this, Register.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            dialog.show();
                        }

                    }
                    else{
                        edt_password.setText("");
                        edt_check_password.setText("");
                        Toast.makeText(Register.this, "密碼輸入不正確",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Register.this, "每一個欄位都需要填寫，請再重複檢查。",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
                dialog.setTitle("警告");                                                    //標題
                dialog.setMessage("如果現在離開這個頁面，將不會儲存您所輸入的資訊！！！");  //內容
                dialog.setCancelable(false);                                                //Dialog畫面以外範圍是否可以控制
                //Negative按鈕
                dialog.setNegativeButton("確定離開", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setClass(Register.this, Main.class);
                        startActivity(intent);
                        finish();
                    }
                });
                //Positive按鈕
                dialog.setPositiveButton("不要離開", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
//                            dialogInterface.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    private void findV(){
        edt_account = (EditText) findViewById(R.id.edt_account);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_check_password = (EditText) findViewById(R.id.edt_check_password);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_back = (Button) findViewById(R.id.btn_back);
    }

    private boolean checkPassword(String password, String checkPassword){
        if (password.equals(checkPassword))
            return true;
        else
            return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Register.this, Main.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
