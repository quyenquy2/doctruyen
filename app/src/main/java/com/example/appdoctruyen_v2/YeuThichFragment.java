package com.example.appdoctruyen_v2;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.adapter.adapterTruyen;
import com.example.appdoctruyen_v2.adapter.adapterTruyenV2;
import com.example.appdoctruyen_v2.adapter.adapterTruyenYeuThich;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.yeuthich;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class YeuThichFragment extends Fragment {
    RecyclerView listViewNew;

    ArrayList<Truyen> TruyenArraylist;

    com.example.appdoctruyen_v2.adapter.adapterTruyenYeuThich adapterTruyen;



    //databasedoctruyen databasedoctruyen;
    databasedoctruyen databaseDocTruyen;

    public YeuThichFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        databaseDocTruyen=new databasedoctruyen(getContext());
        listViewNew = getView().findViewById(R.id.listviewyeuthich);




        AnhXa();



    }

    private void DialogDeleteYeuThic(int position) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(getContext());

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdeleteyeuthic);
        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = TruyenArraylist.get(position).getTenTruyen();
                //Xóa trong SQL
                //  databaseDocTruyen.Delete(idtruyen);
                //Cập nhật lại listview
                //Intent intent = new Intent(getContext(), MainYeuThich.class);
                //getActivity().finish();
                //startActivity(intent);

                //startActivity(intent);
                Toast.makeText(getContext(),"Bỏ yêu thích thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }


    private void AnhXa()
    {
        //toolbar=findViewById(R.id.toolbarmanhinhchinh);
        listViewNew=getView().findViewById(R.id.listviewyeuthich);
        TruyenArraylist=new ArrayList<>();
        DatabaseReference myref= FirebaseDatabase.getInstance().getReference("dsyeuthich");
        DatabaseReference myref2=FirebaseDatabase.getInstance().getReference("truyen");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    yeuthich yt=dataSnapshot.getValue(yeuthich.class);
                    if (yt.getTentaikhoan().equals(tentaikhoan))
                    {
                      myref2.addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot snapshot2) {
                              for (DataSnapshot dataSnapshot2 :snapshot2.getChildren())
                              {
                                  Truyen truyen=dataSnapshot2.getValue(Truyen.class);
                                  if(truyen.getTenTruyen().equals(yt.getTieude())) {TruyenArraylist.add(truyen);}
                              }
                              adapterTruyen.notifyDataSetChanged();
                              myref2.removeEventListener(this);
                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError error2) {

                          }
                      }) ;
                    }
                }
                myref.removeEventListener(this);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                listViewNew.setLayoutManager(gridLayoutManager);

                adapterTruyen=new adapterTruyenYeuThich(getContext(),TruyenArraylist);
                listViewNew.setAdapter(adapterTruyen);
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
        return inflater.inflate(R.layout.fragment_yeuthich, container, false);
    }

}
