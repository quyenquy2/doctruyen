package com.example.appdoctruyen_v2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.appdoctruyen_v2.MainActivity.email;
import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;
import static com.example.appdoctruyen_v2.MainActivity.pass;
import static com.example.appdoctruyen_v2.MainActivity.phanquyen;

import com.example.appdoctruyen_v2.adapter.userAdapter;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AccountFragmment extends Fragment  {
    private static final int rqcode = 10;
    private View view;
    private TextView tvtaikhoan,tvgmail;
    private Button btndangxuat;
    private RecyclerView rcv_user;
    private EditText edt_tk;
    private Button btn_upass;

    ArrayList<TaiKhoan> userArl;
    private userAdapter userAdapter;
    databasedoctruyen databaseDocTruyen;
    ArrayList<TaiKhoan> arrayList;
    private LayoutInflater inflater;
    TaiKhoan user1;

    public AccountFragmment() {
       // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseDocTruyen=new databasedoctruyen(getContext());

        //ánh xạ
        tvtaikhoan = view.findViewById(R.id.tvtentaikhoan);
        tvgmail = view.findViewById(R.id.tvgmail);
        tvtaikhoan.setText(tentaikhoan);
        tvgmail.setText(email);
        rcv_user= view.findViewById(R.id.rcv_user);
        rcv_user = getView().findViewById(R.id.rcv_user);
        edt_tk = view.findViewById(R.id.edt_tk);
        user1=new TaiKhoan(tentaikhoan,pass,email,phanquyen);
//        btn_upass=view.findViewById(R.id.btnupass);
        if (MainActivity.check_admin == 2){

            initList();

            edt_tk.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {

                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
                    filter(s.toString());
                }

            });




        }else{

            Toast.makeText(getContext(),"Bạn không là quản trị viên",Toast.LENGTH_SHORT).show();
            Log.e("Thêm truyện: ","Bạn không có quyền");
        }





    }


    public void initList(){

        rcv_user=getView().findViewById(R.id.rcv_user);
        userArl = new ArrayList<>();
        arrayList =new ArrayList<>();

        DatabaseReference myref= FirebaseDatabase.getInstance().getReference("taikhoan");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    TaiKhoan tk=dataSnapshot.getValue(TaiKhoan.class);
                    if (tk.getmPhanQuyen()==1)
                    {
                        userArl.add(tk);
                        arrayList.add(tk);
                    }
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                //rcv_user.setLayoutManager(linearLayoutManager);
                rcv_user.setLayoutManager(linearLayoutManager);

                userAdapter = new userAdapter(getContext(), new userAdapter.iclickitemuser() {
                    @Override
                    public void updateus(TaiKhoan taiKhoan) {
                        clickupdateus(taiKhoan);
                    }
                }, userArl);

                rcv_user.setAdapter(userAdapter);
                myref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void filter(String text){

        //xóa sau mỗi lần gọi tới filter
        arrayList.clear();

        ArrayList<TaiKhoan> filteredList = new ArrayList<>();

        for(TaiKhoan item : userArl){
            if (item.getmTenTaiKhoan().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                //Thêm dữ liệu để hiển thị ra item nội dung
                arrayList.add(item);
            }
        }
        userAdapter.filterList(filteredList);

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_account_fragmment, container, false);
        btndangxuat = view.findViewById(R.id.btndangxuat);
        btn_upass =view.findViewById(R.id.btnupass);
        btndangxuat.setOnClickListener(view ->{
            Intent intent = new Intent(getContext(),MainDangNhap.class);
            startActivity(intent);
        });
        btn_upass.setOnClickListener(view ->{
            Intent intent1 = new Intent(getContext(), MainUpdatepass.class);
            Bundle bundle1=new Bundle();
            bundle1.putSerializable("objus1",user1);
            intent1.putExtras(bundle1);
            startActivity(intent1);
        });

        return view;
    }

    private void clickupdateus(TaiKhoan user)
    {
        Intent intent =new Intent(getContext(), MainUpdateuser.class);
        Bundle bundle =new Bundle();
        bundle.putSerializable("objus",user);
        intent.putExtras(bundle);
        startActivityForResult(intent,rqcode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == rqcode && resultCode == Activity.RESULT_OK)
        {
            loaddata();
        }
    }
    public void loaddata()
    {
        userArl = new ArrayList<>();
        DatabaseReference myref= FirebaseDatabase.getInstance().getReference("taikhoan");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    TaiKhoan tk=dataSnapshot.getValue(TaiKhoan.class);
                    if (tk.getmPhanQuyen()==1)
                    {
                        userArl.add(tk);
                    }
                }
                userAdapter.setdata(userArl);
                myref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}