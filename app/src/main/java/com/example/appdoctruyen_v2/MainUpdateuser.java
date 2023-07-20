package com.example.appdoctruyen_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdoctruyen_v2.adapter.userAdapter;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.database.firebasedt;
import com.example.appdoctruyen_v2.model.TaiKhoan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainUpdateuser extends AppCompatActivity {
    databasedoctruyen databaseDocTruyen;
    public Context context;
    private TextView tv_username;
    private EditText edt_pass;
    private EditText edt_mail;
    private TaiKhoan user;
    private Button btn_updateus;
    userAdapter usadpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);



        tv_username = findViewById(R.id.tv_username2);
        edt_pass =findViewById(R.id.edt_pass2);
        edt_mail = findViewById(R.id.edt_mail2);
        btn_updateus =findViewById(R.id.btn_updateus);

        user = (TaiKhoan) getIntent().getExtras().get("objus");
        tv_username.setText(user.getmTenTaiKhoan());
        edt_pass.setText(user.getmMatKhau());
        edt_mail.setText(user.getmEmail());




        databaseDocTruyen =new databasedoctruyen(this);


        btn_updateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateuser2();
            }
        });
    }

    private void updateuser2() {

        String tentk= tv_username.getText().toString().trim();

        System.out.println(tentk+"+++++++++++++++++++++");
        //databaseDocTruyen.Deletetk(tentk);
        String pass1 = edt_pass.getText().toString();
        String mail1 = edt_mail.getText().toString();
        TaiKhoan taikhoan =new TaiKhoan(tentk,pass1,mail1,1);
        taikhoan.xuattk();

        String regex="^\\w+[a-z0-9]*@\\w+mail.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail1);

        if(pass1.length() <8) {Toast.makeText(MainUpdateuser.this, "Mật khẩu cần ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();}
        else {
            if (pass1.equals("") || mail1.equals("")) {
                Toast.makeText(MainUpdateuser.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                Log.e("Thông báo : ", "Bạn chưa nhập đầy đủ thông tin");
            } else {
                if (matcher.find()) {
                    firebasedt db=new firebasedt();
                    db.updatetaikhoan(user.getmEmail(),user.getmMatKhau(),taikhoan);
                    System.out.println("lllllllllllllllllllllllllll");
                    Toast.makeText(MainUpdateuser.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    Intent resun = new Intent();
                    setResult(Activity.RESULT_OK, resun);
                    finish();
                } else {
                    Toast.makeText(MainUpdateuser.this, "Định dạng mail không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        }

        // databaseDocTruyen.AddTaiKhoan(taikhoan);


    }

}