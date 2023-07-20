package com.example.appdoctruyen_v2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.database.firebasedt;
import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainDangKy extends AppCompatActivity {
    EditText edtDKTaiKhoan,edtDKMatKhau,edtDKEmail,edtDKLMatKhau;
    Button btnDKDangKy,btnDKDangNhap;
    ArrayList<TaiKhoan> listuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        AnhXa();


        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();
                String lmatkhau = edtDKLMatKhau.getText().toString();

                String regex="^\\w+[a-z0-9]*@\\w+mail.com$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(email);



                TaiKhoan taikhoan1 = new TaiKhoan(taikhoan,matkhau,email,1);
                if (matkhau.length() < 8) {Toast.makeText(MainDangKy.this, "Mật khẩu cần ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();}
                else {
                    if (taikhoan.equals("") || matkhau.equals("") || email.equals("") || lmatkhau.equals("")) {
                        Toast.makeText(MainDangKy.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        Log.e("Thông báo : ", "Bạn chưa nhập đầy đủ thông tin");
                    } else {
                        if (matcher.find()) {
                            if (matkhau.equals(lmatkhau)) {
                                String datatentaikhoan = null;
                                    for (int i=0;i<listuser.size();i++) {
                                        if(listuser.get(i).getmTenTaiKhoan().equals(taikhoan))
                                        {datatentaikhoan =taikhoan;break;
                                        }
                                        else {datatentaikhoan = "";}
                                    }

                                    if (datatentaikhoan.equals(taikhoan)) {
                                        Toast.makeText(MainDangKy.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();

                                    } else {
                                        String dataemail = null;
                                        for (int i=0;i<listuser.size();i++) {
                                            if(listuser.get(i).getmEmail().equals(email))
                                            {dataemail =email;break;
                                            }
                                            else {dataemail = "";}
                                        }
                                        if (dataemail.equals(email)) {
                                            Toast.makeText(MainDangKy.this, "Email đã được đăng ký", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            firebasedt fb=new firebasedt();
                                            fb.adduser(taikhoan1);
                                            Toast.makeText(MainDangKy.this, "Đăng ký thành công ", Toast.LENGTH_SHORT).show();
                                            edtDKTaiKhoan.setText("");
                                            edtDKMatKhau.setText("");
                                            edtDKEmail.setText("");
                                            edtDKLMatKhau.setText("");
                                        }

                                    }


                            } else {
                                Toast.makeText(MainDangKy.this, "Mật khẩu xác nhận không trùng", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainDangKy.this, "Định dạng mail không đúng", Toast.LENGTH_SHORT).show();
                        }
                        //Nếu đầy đủ thông tin
                    }
                }
                }
        });
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainDangKy.this,MainDangNhap.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        edtDKEmail = findViewById(R.id.dkEmail);
        edtDKMatKhau = findViewById(R.id.dkMatKhau);
        edtDKTaiKhoan = findViewById(R.id.dkTaiKhoan);
        edtDKLMatKhau = findViewById(R.id.dklMatKhau);
        btnDKDangKy = findViewById(R.id.dkDangKy);
        btnDKDangNhap = findViewById(R.id.dkDangNhap);
        checktk();
    }
    private void checktk()
    {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        String link="taikhoan";
        DatabaseReference myref= db.getReference(link);
        listuser =new ArrayList<>();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    TaiKhoan  user1 = postSnapshot.getValue(TaiKhoan.class);
                    System.out.println("++++++++++++++++++++++++++++++++++");
                    System.out.println(user1.toString());
                    listuser.add(user1);
                }
//                  user = snapshot.getValue(TaiKhoan.class);
//                  System.out.println("++++++++++++++++++++++++++++++++++"+tt);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainDangKy.this,"get fail",Toast.LENGTH_SHORT).show();
                System.out.println("---------------------------------------");
            }
        });
    }
}