package com.example.appdoctruyen_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.main.MainAdmin;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainCapNhat extends AppCompatActivity {
    databasedoctruyen databaseDocTruyen;
    int slyt;
    private Truyen truyen5;

    // TextView txtNoidung;
    EditText edtTieuDe,edtNoiDung,edtAnh;
    Button btnCapNhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cap_nhat);
        edtTieuDe = findViewById(R.id.dbtieude);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnCapNhat = findViewById(R.id.buttonCapNhat);
        edtAnh = findViewById(R.id.dbimg);


//        Intent intent = getIntent();
//        String tenTruyen = intent.getStringExtra("tentruyen");
//        String noidung = intent.getStringExtra("noidung");
//        String img = intent.getStringExtra("imgtruyen");
//        idtr=intent.getIntExtra("idtr",0);
//        edtTieuDe.setText(tenTruyen);
//        edtNoiDung.setText(noidung);
//        edtAnh.setText(img);

        truyen5= (Truyen) getIntent().getExtras().get("udtr");
        if (truyen5 != null)
        {
            edtTieuDe.setText(truyen5.getTenTruyen());
            edtNoiDung.setText(truyen5.getNoiDung());
            edtAnh.setText(truyen5.getAnh());
            slyt=truyen5.getSoluongyt();

        }



        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capnhattruyen();
            }
        });

    }

    private Truyen CreatTruyen(){
        String tentruyen = edtTieuDe.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id",0);
        Truyen truyen = new Truyen(tentruyen,noidung,img,slyt,System.currentTimeMillis());
        return truyen;
    }

private void capnhattruyen()
        {
            String tentruyen2 = edtTieuDe.getText().toString();
            String noidung2 = edtNoiDung.getText().toString();
            String img2 = edtAnh.getText().toString();
            Truyen truyen=new Truyen(tentruyen2,noidung2,img2,truyen5.getSoluongyt(),truyen5.getTime());
            //Truyen truyen = CreatTruyen();
            DatabaseReference myref= FirebaseDatabase.getInstance().getReference("truyen");

            if(tentruyen2.equals("") || noidung2.equals("") || img2.equals("")){
                Toast.makeText(MainCapNhat.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            }
            else{
                System.out.println("++++++++++++++++++++++++++++");
                System.out.println(tentruyen2+"qqqqqqqqqqqqqqqqq");
                System.out.println(slyt+"zzzzzzzzzzzzzzzzzzzzzz");

                myref.child(truyen5.getTenTruyen()).setValue(truyen);
                System.out.println(truyen.toString());
                Toast.makeText(MainCapNhat.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                Log.e("Cập nhật truyện! : ","Thành công");

                Intent intenresul2=new Intent();
                setResult(Activity.RESULT_OK,intenresul2);
            }
            //Cuộn textview
            edtNoiDung.setMovementMethod(new ScrollingMovementMethod());
            finish();
        }


}