package com.example.appdoctruyen_v2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.DanhGia;
import com.example.appdoctruyen_v2.adapter.DanhGiaAdapter;
import com.example.appdoctruyen_v2.model.Truyen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.appdoctruyen_v2.MainActivity.email;
import static com.example.appdoctruyen_v2.MainActivity.check_admin;
import static com.example.appdoctruyen_v2.MainActivity.pass;
import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;


import java.util.ArrayList;

public class MainXemDanhGia extends AppCompatActivity {
      ArrayList<DanhGia> listdanhgia;
    //  static ArrayList<DanhGia> listxem_dg;
      ListView list_view;
    databasedoctruyen databasedoctruyen;
    DanhGiaAdapter DanhGiaAdapter;
    Truyen truyen;
    String tentruyen;
    Button themdg,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_xem_danh_gia);
        databasedoctruyen=new databasedoctruyen(this);


        Intent intent_dg = getIntent();
        String tentk=intent_dg.getStringExtra("tentk");
        String  noidung =intent_dg.getStringExtra("noidung");
        int dl= intent_dg.getIntExtra("dulieu",0);
        tentruyen= intent_dg.getStringExtra("tentruyen");
        AnhXa();
        themdg =findViewById(R.id.buttonAdddg);
        home=findViewById(R.id.gohome);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainXemDanhGia.this,MainActivity.class);
                System.out.println("  email:"+email+"  pass:"+pass+"   tentailhoan"+tentaikhoan);
                intent.putExtra("phanq",check_admin);
                intent.putExtra("email",email);
                intent.putExtra("tentaikhoan",tentaikhoan);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });

        themdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainXemDanhGia.this,MainDanhGia.class);
                intent.putExtra("tentruyen",tentruyen);
                startActivity(intent);
            }
        });


    }

    void AnhXa(){
        list_view =findViewById(R.id.list_view);

        listdanhgia = new ArrayList<>();

        DatabaseReference myref= FirebaseDatabase.getInstance().getReference("danhgia");
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DanhGiaAdapter = new DanhGiaAdapter(getApplicationContext(),listdanhgia);
                list_view.setAdapter(DanhGiaAdapter);
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    DanhGia danhGia=dataSnapshot.getValue(DanhGia.class);
                    if (danhGia.getTenTruyen().equals(tentruyen))
                    {
                        listdanhgia.add(danhGia);
                    }
                }
                DanhGiaAdapter.notifyDataSetChanged();
                myref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myref.addValueEventListener(valueEventListener);
    }

}