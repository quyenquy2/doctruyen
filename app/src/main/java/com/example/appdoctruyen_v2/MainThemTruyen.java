package com.example.appdoctruyen_v2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainThemTruyen extends AppCompatActivity {

    EditText edtTieuDe,edtNoiDung,edtAnh;
    Button btnThemTr;
    databasedoctruyen databaseDocTruyen;
    private Truyen truyen6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_truyen);
        edtTieuDe = findViewById(R.id.dbtieude);
        edtNoiDung = findViewById(R.id.dbnoidung);
        edtAnh = findViewById(R.id.dbimg);
        btnThemTr=findViewById(R.id.btnThem);

        databaseDocTruyen = new databasedoctruyen(this);

        btnThemTr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addtruyenmoi();
            }

        });
    }
    private Truyen CreatTruyen(){
        String tentruyen = edtTieuDe.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();
        Truyen truyen = new Truyen(tentruyen,noidung,img,0,System.currentTimeMillis());
        return truyen;
    }

    private void addtruyenmoi()
    {
        DatabaseReference myref= FirebaseDatabase.getInstance().getReference("truyen");


        String tentruyen = edtTieuDe.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();
        Truyen truyen = CreatTruyen();

        edtNoiDung.setMovementMethod(new ScrollingMovementMethod());
        if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
            Toast.makeText(MainThemTruyen.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }
        else {

            // Tạo một đối tượng DatabaseReference chỉ đến nơi muốn kiểm tra
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("truyen").child(tentruyen);

// Thêm một ValueEventListener để lắng nghe giá trị của đối tượng
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue() == null)
                    {
                        myref.child(truyen.getTenTruyen()).setValue(truyen);
                        Toast.makeText(MainThemTruyen.this,"Thêm truyện thành công",Toast.LENGTH_SHORT).show();
                        Log.e("Thêm truyện : ","Thành công");

                        Intent intenresul3=new Intent();
                        setResult(Activity.RESULT_OK,intenresul3);
                        finish();

                    }
                    else {
                        Toast.makeText(MainThemTruyen.this, "Truyện này đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }



//        else {
//            try {
//                Cursor cursor= databaseDocTruyen.getTruyen4(tentruyen);
//                cursor.moveToFirst();
//                String datatentaikhoan = cursor.getString(1);
//                Toast.makeText(MainThemTruyen.this, "Truyện này đã tồn tại", Toast.LENGTH_SHORT).show();
//            } catch (Exception e)
//            {
//                            databaseDocTruyen.AddTruyen(truyen);
//
//            Toast.makeText(MainThemTruyen.this,"Thêm truyện thành công",Toast.LENGTH_SHORT).show();
//            Log.e("Thêm truyện : ","Thành công");
//
//            Intent intenresul3=new Intent();
//            setResult(Activity.RESULT_OK,intenresul3);
//                finish();
//            }
//        }



//        {
//            databaseDocTruyen.AddTruyen(truyen);
//
//            Toast.makeText(MainThemTruyen.this,"Thêm truyện thành công",Toast.LENGTH_SHORT).show();
//            Log.e("Thêm truyện : ","Thành công");
//
//            Intent intenresul3=new Intent();
//            setResult(Activity.RESULT_OK,intenresul3);
//            finish();
//        }
    }
}