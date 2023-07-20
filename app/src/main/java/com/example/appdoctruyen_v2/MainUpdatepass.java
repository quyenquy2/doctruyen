package com.example.appdoctruyen_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.database.firebasedt;
import com.example.appdoctruyen_v2.model.TaiKhoan;

import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;

public class MainUpdatepass extends AppCompatActivity {
    databasedoctruyen databaseDocTruyen;
    public Context context;
    private TextView tv_username;
    private EditText edt_pass;
    private EditText edt_newpass;
    private EditText edt_xnpass;
    private TaiKhoan user;
    private Button btn_updatepass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass);
        user = (TaiKhoan) getIntent().getExtras().get("objus1");


        tv_username = findViewById(R.id.tv_username2);
        edt_pass =findViewById(R.id.edt_pass);
        edt_newpass = findViewById(R.id.edt_mkmoi);
        edt_xnpass=findViewById(R.id.edt_xnmk);
        btn_updatepass =findViewById(R.id.btn_updatepass);

        tv_username.setText(user.getmTenTaiKhoan().toString());



        btn_updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepass2();
            }
        });
    }

    private void updatepass2() {

        String tentk= tv_username.getText().toString().trim();
        System.out.println(tentk+"+++++++++++++++++++++");
        String pass1 = edt_pass.getText().toString();
        String newpass=edt_newpass.getText().toString();
        String xnpass=edt_xnpass.getText().toString();
        TaiKhoan taikhoan =new TaiKhoan(tentk,newpass,user.getmEmail(),user.getmPhanQuyen());
        taikhoan.xuattk();


        if(pass1.length() <8 || newpass.length() <8 || xnpass.length() <8) {Toast.makeText(MainUpdatepass.this, "Mật khẩu cần ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();}
        else {
            if (pass1.equals("") || newpass.equals("") || xnpass.equals("")) {
                Toast.makeText(MainUpdatepass.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                Log.e("Thông báo : ", "Bạn chưa nhập đầy đủ thông tin");
            } else {
                if (pass1.equals(user.getmMatKhau())) {
                    if (newpass.equals(xnpass)) {
                        firebasedt db=new firebasedt();
                        db.updatepass(taikhoan);
                        Toast.makeText(MainUpdatepass.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent resun = new Intent();
                        setResult(Activity.RESULT_OK, resun);
                        finish();
                    } else {
                        Toast.makeText(MainUpdatepass.this, "Mật khẩu xác nhận không đúng", Toast.LENGTH_SHORT).show();
                        Log.e("Thông báo : ", "Mật khẩu xác nhận không đúng");
                    }
                } else {
                    Toast.makeText(MainUpdatepass.this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                    Log.e("Thông báo : ", "Mật khẩu cũ không đúng");
                    System.out.println(pass1+"++++++++++++++++"+user.getmMatKhau());
                }
            }
        }


    }

}