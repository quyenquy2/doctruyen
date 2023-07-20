package com.example.appdoctruyen_v2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainDangNhap extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;
    LinearLayout fgpass;
    ArrayList<TaiKhoan> listuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);
        AnhXa();


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDangNhap.this,MainDangKy.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String taikhoan = edtTaiKhoan.getText().toString();
                    String matkhau = edtMatKhau.getText().toString();
                    if (taikhoan.equals("") || matkhau.equals(""))
                    {
                         Toast.makeText(MainDangNhap.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        for (int i=0;i<listuser.size();i++) {
                            if (listuser.get(i).getmTenTaiKhoan().equals(taikhoan)) {
                                TaiKhoan user=listuser.get(i);
                                System.out.println("////////////////////////");
                                clicksignin(user,matkhau);
                                break;
                            } else {
                                if (i==(listuser.size()-1)) Toast.makeText(MainDangNhap.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            }
        });

        fgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainDangNhap.this,MainFGPass.class);
                startActivity(intent);
            }
        });


    }
    private void AnhXa() {
        edtTaiKhoan = findViewById(R.id.edtUsername);
        edtMatKhau = findViewById(R.id.edtPasswod);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        fgpass =findViewById(R.id.fgpass);
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
            myref.removeEventListener(this);
           }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(MainDangNhap.this,"get fail",Toast.LENGTH_SHORT).show();
                System.out.println("---------------------------------------");
            }
        });
  }


  private void clicksignin(TaiKhoan user,String pass1)
  {
      FirebaseAuth auth=FirebaseAuth.getInstance();
      auth.signInWithEmailAndPassword(user.getmEmail(),pass1).
              addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        System.out.println("không lỗi");
                        int phanquyen = user.getmPhanQuyen();
                        String tentk = user.getmTenTaiKhoan();
                        String email = user.getmEmail();
                        String pass = user.getmMatKhau();
                        Intent intent = new Intent(MainDangNhap.this, MainActivity.class);
                        intent.putExtra("phanq", phanquyen);
                        intent.putExtra("email", email);
                        intent.putExtra("tentaikhoan", tentk);
                        intent.putExtra("pass", pass);
                        startActivity(intent);
                        Log.e("Đăng nhập : ", "Thành công");
                        Toast.makeText(MainDangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        String link="taikhoan/"+user.getmTenTaiKhoan()+"/mMatKhau";
                        DatabaseReference db=FirebaseDatabase.getInstance().getReference(link);
                        db.setValue(pass1);
                    }
                    else {
                        Toast.makeText(MainDangNhap.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                  }
              });
  }
}

//                               if (user.getmTenTaiKhoan().equals(taikhoan) && user.getmMatKhau().equals(matkhau)) {
//                                       System.out.println("không lỗi");
//                                       int phanquyen = user.getmPhanQuyen();
//                                       String tentk = user.getmTenTaiKhoan();
//                                       String email = user.getmEmail();
//                                       String pass = user.getmMatKhau();
//
//
//                                       Intent intent = new Intent(MainDangNhap.this, MainActivity.class);
//        intent.putExtra("phanq", phanquyen);
//        intent.putExtra("email", email);
//        intent.putExtra("tentaikhoan", tentk);
//        intent.putExtra("pass", pass);
//        startActivity(intent);
//        Log.e("Đăng nhập : ", "Thành công");
//        Toast.makeText(MainDangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//        } else {
//        Toast.makeText(MainDangNhap.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
//        }