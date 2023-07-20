package com.example.appdoctruyen_v2;


import static com.example.appdoctruyen_v2.MainActivity.check_admin;
import static com.example.appdoctruyen_v2.MainActivity.email;
import static com.example.appdoctruyen_v2.MainActivity.pass;
import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.adapter.adapterQuanLy;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.database.firebasedt;
import com.example.appdoctruyen_v2.model.Truyen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuanLyFragment extends Fragment {

    RecyclerView listView;
    Button buttonThem;
    private EditText edt_ttr;


    ArrayList<Truyen> TruyenArrayList;
    adapterQuanLy adaptertruyen;
    databasedoctruyen databaseDocTruyen;
    ArrayList<Truyen> arrayList;
    private static final int rqcode=10;

    public QuanLyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_ttr = view.findViewById(R.id.edt_ttr);

        if (MainActivity.check_admin == 2){
            init();
            edt_ttr.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString());
                }

            });

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            listView.setLayoutManager(linearLayoutManager);
            adaptertruyen = new adapterQuanLy(getContext(), new adapterQuanLy.intercapnhattruyen() {
                @Override
                public void updatetruyen(Truyen truyen2) {
                    clickudtruyen(truyen2);
                }

                @Override
                public void xoatruyen(Truyen truyen) {
                    clickdltruyen(truyen);
                }
            }, TruyenArrayList);
            listView.setAdapter(adaptertruyen);
        }else{

            Toast.makeText(getContext(),"Bạn không có quyền này",Toast.LENGTH_SHORT).show();
            Log.e("Thêm truyện: ","Bạn không có quyền");
        }

    }



    private void init() {

        listView = getActivity().findViewById(R.id.listviewAdmin);
        buttonThem = getActivity().findViewById(R.id.buttonAddTruyen);
        edt_ttr=getActivity().findViewById(R.id.edt_ttr);
        initList();



        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getActivity().getIntent();
                int id = intent.getIntExtra("Id",0);
                Intent intent1 = new Intent(getContext(), MainThemTruyen.class);
                intent1.putExtra("Id",id);
                //startActivity(intent1);
                startActivityForResult(intent1,rqcode);
            }
        });


    }




    //Gán DL vào listview
    public void initList(){
        TruyenArrayList = new ArrayList<>();
        arrayList=new ArrayList<>();
        DatabaseReference myref= FirebaseDatabase.getInstance().getReference("truyen");
        ValueEventListener valueEventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Truyen truyen=snapshot.getValue(Truyen.class);
                    TruyenArrayList.add(truyen);
                    arrayList.add(truyen);
                    // Xử lý với từng đối tượng

                }
                adaptertruyen.notifyDataSetChanged();
                myref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myref.addValueEventListener(valueEventListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quanly, container, false);
    }


    //search
    private void filter(String text){

        //xóa sau mỗi lần gọi tới filter
        arrayList.clear();

        ArrayList<Truyen> filteredList = new ArrayList<>();

        for(Truyen item : TruyenArrayList){
            if (item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                //Thêm dữ liệu để hiển thị ra item nội dung
                arrayList.add(item);
            }
        }
        adaptertruyen.filterList(filteredList);
    }



    private void clickudtruyen(Truyen truyen)
    {


        Intent intent=new Intent(getContext(), MainCapNhat.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("udtr",truyen);
        intent.putExtras(bundle);
        startActivityForResult(intent,rqcode);
    }
    private void clickdltruyen(Truyen truyen)
    {
        new AlertDialog.Builder(getContext())
                .setTitle("Xác nhận xóa truyện!")
                .setMessage("Bạn chắc chứ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        DatabaseReference myref1= FirebaseDatabase.getInstance().getReference("truyen");
//                        myref1.child(truyen.getTenTruyen()).removeValue();
                        firebasedt fb=new firebasedt();
                        fb.deletetruyen(truyen);
                        loaddata2();
                        Toast.makeText(getContext(),"Xóa truyện thành công",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No",null)
                .show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==rqcode && resultCode == Activity.RESULT_OK)
        {

            loaddata2();
            System.out.println("loaddataaaaaaaaaaaaaaaaaaaaaaaa");
        }
    }

    public void loaddata2()
    {
        TruyenArrayList = new ArrayList<>();
        DatabaseReference myref= FirebaseDatabase.getInstance().getReference("truyen");
        ValueEventListener valueEventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Truyen truyen=snapshot.getValue(Truyen.class);
                    TruyenArrayList.add(truyen);
                    // Xử lý với từng đối tượng

                }
                adaptertruyen.notifyDataSetChanged();
                adaptertruyen.setdata(TruyenArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myref.addValueEventListener(valueEventListener);
    }
}
