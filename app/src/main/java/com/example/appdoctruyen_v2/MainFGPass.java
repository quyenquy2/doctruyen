package com.example.appdoctruyen_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFGPass extends AppCompatActivity {
        EditText edtUsername;
        Button btnReset,btngoback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fgpass);
        anhxa();
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkreset(edtUsername.getText().toString().trim());
            }
        });
        btngoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFGPass.this,MainDangNhap.class);
                startActivity(intent);
            }
        });
    }

    private void checkreset(String username) {
        DatabaseReference myref= FirebaseDatabase.getInstance().getReference("taikhoan");
        ArrayList<TaiKhoan> mlistuser = new ArrayList<>();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    TaiKhoan tk= dataSnapshot.getValue(TaiKhoan.class);
                    mlistuser.add(tk);
                }
                ////////////////////////////////////////////
                for (int i=0;i<mlistuser.size();i++)
                {
                    if (mlistuser.get(i).getmTenTaiKhoan().equals(username))
                    {
                        sendmail(mlistuser.get(i).getmEmail());
                        break;
                    }
                    else
                    {
                        if (i== (mlistuser.size()-1))
                        {
                            Toast.makeText(MainFGPass.this,"Tài khoản không tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                myref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myref.addValueEventListener(valueEventListener);

    }

    private void sendmail(String email)
    {
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        mauth.sendPasswordResetEmail(email).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainFGPass.this,"Đã gửi email reset",Toast.LENGTH_SHORT).show();
                        } else
                        {
                            Toast.makeText(MainFGPass.this,"không thể gửi email",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void anhxa()
    {
        edtUsername = findViewById(R.id.edtUsername);
        btnReset =findViewById(R.id.btnReset);
        btngoback=findViewById(R.id.btngoback);
    }
}