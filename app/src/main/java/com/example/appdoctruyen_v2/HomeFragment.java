package com.example.appdoctruyen_v2;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.appdoctruyen_v2.adapter.adapterTruyen;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.chuyenmuc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import  com.example.appdoctruyen_v2.database.*;
import java.util.ArrayList;
import  com.example.appdoctruyen_v2.adapter.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    FloatingActionButton fab,ac;
    RecyclerView listViewNew2,listViewNew;


    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;
    ArrayList<Truyen> TruyenArraylist1;

    ArrayList<Truyen> TruyenArraylist2;

    com.example.appdoctruyen_v2.adapter.adapterTruyenV2 adapterTruyen;


    ArrayList<TaiKhoan> taiKhoanArrayList;

    com.example.appdoctruyen_v2.database.databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databasedoctruyen=new databasedoctruyen(getContext());
        fab = getActivity().findViewById(R.id.fab_btn);

        Intent intentpq = getActivity().getIntent();
        int i= intentpq.getIntExtra("phanq",0);
        int idd=intentpq.getIntExtra("idd",0);
        email=intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MainTimKiem.class);
                startActivity(intent);
            }
        });

        AnhXa();
        ActionViewFlipper();
    }




    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://zingaudio.net/wp-content/uploads/2020/10/di-the-ta-quan.jpg");
        mangquangcao.add("https://www.nae.vn/ttv/ttv/public/images_user/2ccdc496f0e48846a8fb2ba70a2be3a41711865b6dcd7c5e716b1b52c07fba8b.jpg");
        mangquangcao.add("https://img.dtruyen.com/public/images/reviews_img/20191206/de_ba.jpg");
        mangquangcao.add("https://cdn.popsww.com/blog/sites/2/2021/03/top-17-truyen-huyen-huyen-di-gioi-hay-nhat-2021.jpg");

        for (int i=0; i<mangquangcao.size();i++)
        {
            ImageView imageView=new ImageView(getContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(4000);

        viewFlipper.setAutoStart(true);

        Animation animation_slide_in= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void AnhXa()
    {
        viewFlipper= getActivity().findViewById(R.id.viewflipper);
        listViewNew= getActivity().findViewById(R.id.listviewNew);
        listViewNew2= getActivity().findViewById(R.id.listviewNew2);
       // listView= getActivity().findViewById(R.id.listviewmanhinhchinh);
        //listviewThongtin=getActivity().findViewById(R.id.listviewThongTin);
        //navigationView= getActivity().findViewById(R.id.navigationview);
        //drawerLayout= getActivity().findViewById(R.id.drawerlayout);

        TruyenArraylist=new ArrayList<>();
        TruyenArraylist1=new ArrayList<>();
        adapterTruyen=new adapterTruyenV2(getContext(),TruyenArraylist);
        TruyenArraylist2 = new ArrayList<>();

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference myref= db.getReference("truyen");
//        myref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                   for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                           Truyen truyen1 = postSnapshot.getValue(Truyen.class);
//                           System.out.println("++++++++++++++++++++++++++++++++++");
//                           System.out.println(truyen1.toString());
//                           TruyenArraylist1.add(0,truyen1);
//
//                   }
//                for (int i=0;i<TruyenArraylist1.size() && i< 2;i++)
//                {
//                    TruyenArraylist.add(TruyenArraylist1.get(i));
//                }
//
//                adapterTruyen.notifyDataSetChanged();
//
//                listViewNew.setHasFixedSize(true);
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//                listViewNew.setLayoutManager(gridLayoutManager);
//
//                adapterTruyen=new adapterTruyenV2(getContext(),TruyenArraylist);
//                listViewNew.setAdapter(adapterTruyen);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("truyen");
        ref.orderByChild("time").limitToLast(2).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Truyen truyen1 = childSnapshot.getValue(Truyen.class);
                    // Xử lý đối tượng
                    TruyenArraylist1.add(0,truyen1);
                }

                adapterTruyen.notifyDataSetChanged();

                listViewNew.setHasFixedSize(true);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                listViewNew.setLayoutManager(gridLayoutManager);

                adapterTruyen=new adapterTruyenV2(getContext(),TruyenArraylist1);
                listViewNew.setAdapter(adapterTruyen);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Cần xử lý lỗi nếu có
            }
        });





        Query qr= myref.orderByChild("soluongyt");
        qr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Truyen truyen1 = postSnapshot.getValue(Truyen.class);
                    System.out.println("++++++++++++++++++++++++++++++++++");
                    System.out.println(truyen1.toString());
                    TruyenArraylist2.add(0,truyen1);
                }
                adapterTruyen.notifyDataSetChanged();

        //LISTVIEW2
        listViewNew2.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
        gridLayoutManager2.setOrientation(GridLayoutManager.VERTICAL);
        listViewNew2.setLayoutManager(gridLayoutManager2);

        adapterTruyen=new adapterTruyenV2(getContext(),TruyenArraylist2);
        listViewNew2.setAdapter(adapterTruyen);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        Cursor cursor2 = databasedoctruyen.getData2();
//        TruyenArraylist2 = new ArrayList<>();
//        while (cursor2.moveToNext())
//        {
//            int id=cursor2.getInt(0);
//            String tentruyen=cursor2.getString(1);
//            String noidung=cursor2.getString(2);
//            String anh=cursor2.getString(3);
//            int id_tk=cursor2.getInt(4);
//
//            TruyenArraylist2.add(new Truyen(tentruyen,noidung,anh,id_tk));
//
//
//        }
//
//        //LISTVIEW2
//        listViewNew2.setHasFixedSize(true);
//        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
//        gridLayoutManager2.setOrientation(GridLayoutManager.VERTICAL);
//        listViewNew2.setLayoutManager(gridLayoutManager2);
//
//        adapterTruyen=new adapterTruyenV2(getContext(),TruyenArraylist2);
//        listViewNew2.setAdapter(adapterTruyen);



        taiKhoanArrayList=new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Tăng bố cục cho fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
