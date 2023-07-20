package com.example.appdoctruyen_v2;

import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.database.firebasedt;
import com.example.appdoctruyen_v2.model.DanhGia;


public class MainDanhGia extends AppCompatActivity {
    EditText edtNoiDungDanhGia;
    Button btnDanhGia;
    Button btnXemDanhGia;
    EditText edttentk;
    databasedoctruyen databaseDocTruyen;
    String tieude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_gia);

        edttentk = findViewById(R.id.edttentk);
        edtNoiDungDanhGia = findViewById(R.id.dbnoidungdanhgia);
        btnDanhGia = findViewById(R.id.dbdanhgia);
        btnXemDanhGia=findViewById(R.id.dbxemdanhgia);
        edttentk.setText(tentaikhoan);
        databaseDocTruyen = new databasedoctruyen(this);
        tieude=getIntent().getStringExtra("tentruyen");

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidungdanhgia = edtNoiDungDanhGia.getText().toString();
                //String tentaikhoan = edttentk.getText().toString();
                edttentk.setText(tentaikhoan);
                System.out.println(tentaikhoan+"+++++++++++"+getIntent().getStringExtra("tentruyen"));
                DanhGia danhGia = CreatDanhGia();
                danhGia.setTenTruyen(getIntent().getStringExtra("tentruyen"));
                if(noidungdanhgia.equals("") ){
                    Toast.makeText(MainDanhGia.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{

                    firebasedt fb=new firebasedt();
                    fb.AddDanhGia(danhGia);



                  //  String noidungdanhgia = edtNoiDungDanhGia.getText().toString();
                    Intent intent_danh_gia = new Intent(MainDanhGia.this,MainXemDanhGia.class);
                    intent_danh_gia.putExtra("tentruyen",tieude);
                    finish();
                    startActivity(intent_danh_gia);
                    Toast.makeText(MainDanhGia.this,"Thêm đánh giá thành công",Toast.LENGTH_SHORT).show();
                    Log.e("Thêm đánh giá : ","Thành công");
                }
            }
        });

        btnXemDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_danh_gia = new Intent(MainDanhGia.this,MainXemDanhGia.class);
                intent_danh_gia.putExtra("tentruyen",tieude);
                finish();
                startActivity(intent_danh_gia);
            }
        });

    }


    private DanhGia CreatDanhGia(){
        String noiDungDanhGia = edtNoiDungDanhGia.getText().toString();
        edttentk.setText(tentaikhoan);
        DanhGia danhGia = new DanhGia(tentaikhoan,noiDungDanhGia);
        return danhGia;

    }
}