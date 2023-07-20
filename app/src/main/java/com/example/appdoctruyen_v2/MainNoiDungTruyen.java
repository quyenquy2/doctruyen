package com.example.appdoctruyen_v2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.appdoctruyen_v2.MainActivity.check_admin;
import static com.example.appdoctruyen_v2.MainActivity.email;
import static com.example.appdoctruyen_v2.MainActivity.pass;
import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;

import androidx.appcompat.app.AppCompatActivity;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.main.MainAdmin;
import com.example.appdoctruyen_v2.model.yeuthich;
import com.example.appdoctruyen_v2.database.firebasedt;

public class MainNoiDungTruyen extends AppCompatActivity {

    TextView txtTenTruyen,txtNoidung;
    String tieudeyt;
    String tentk=tentaikhoan;
    int idtruyen;
    Button btnDanhGia, btnYeuThich, btnChiaSe,btngohome;
    databasedoctruyen databaseDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noi_dung_truyen);

        AnhXa();

        //databasedoctruyen database = new databasedoctruyen(this);
        databaseDocTruyen = new databasedoctruyen(this);
        txtNoidung = findViewById(R.id.NoiDung);
        txtTenTruyen = findViewById(R.id.TenTruyen);



        Intent intent = getIntent();
        String tenTruyen = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");
        idtruyen= intent.getIntExtra("id",0);
        txtTenTruyen.setText(tenTruyen);
        System.out.println("==========================="+idtruyen);
        tieudeyt=tenTruyen;
        txtNoidung.setText(noidung);
        yeuthich yeuthich = Creatyt();
        //Cuộn textview
        txtNoidung.setMovementMethod(new ScrollingMovementMethod());

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainNoiDungTruyen.this,MainXemDanhGia.class);
                intent.putExtra("tentruyen",txtTenTruyen.getText().toString());
                startActivity(intent);
            }
        });

        btnChiaSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainNoiDungTruyen.this,MainChiaSe.class);
                intent.putExtra("tentruyen",txtTenTruyen.getText().toString());
                startActivity(intent);
            }
        });

        btnYeuThich.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //databaseDocTruyen.AddYeuThich(yeuthich);
                firebasedt fb=new firebasedt();
                fb.addyeuthich(yeuthich);
                Toast.makeText(MainNoiDungTruyen.this,"Đã thêm vào danh sách yêu thích",Toast.LENGTH_SHORT).show();
                Log.e("Yêu thích: ","Đã thêm vào danh sách yêu thích");
            }
        });

        btngohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainNoiDungTruyen.this,MainActivity.class);
                System.out.println("email:"+email+"  pass:"+pass+"   tentailhoan"+tentaikhoan);
                intent.putExtra("phanq",check_admin);
                intent.putExtra("email",email);
                intent.putExtra("tentaikhoan",tentaikhoan);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });



    }

    private void AnhXa() {
        txtTenTruyen = findViewById(R.id.TenTruyen);
        txtNoidung = findViewById(R.id.NoiDung);
        btnDanhGia = findViewById(R.id.buttonDanhgia);
        btnYeuThich = findViewById(R.id.buttonYeuthich);
        btnChiaSe = findViewById(R.id.buttonChiase);
        btngohome=findViewById(R.id.gohomend);
    }
    private yeuthich Creatyt(){
      //  String tieudeyt=tenTruyen;
        System.out.println("+++++++++++++++++++++++++++"+tieudeyt);

        yeuthich yeuthich =new yeuthich(tentaikhoan,tieudeyt);
        return yeuthich;
    }
}