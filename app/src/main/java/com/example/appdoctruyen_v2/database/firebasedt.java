package com.example.appdoctruyen_v2.database;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appdoctruyen_v2.MainUpdateuser;
import com.example.appdoctruyen_v2.model.DanhGia;
import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.yeuthich;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Optional;

public class firebasedt {


    public  void addyeuthich(yeuthich yt)
    {
        ArrayList<yeuthich> listyt=new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("dsyeuthich");
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    yeuthich yt1=dataSnapshot.getValue(yeuthich.class);
                    listyt.add(yt1);
                }
                if (!timkiem(listyt,yt))
                {
                    DatabaseReference newObjectRef = ref.push();
                    String objectId = newObjectRef.getKey();
                    newObjectRef.setValue(yt);

                    DatabaseReference ref2=FirebaseDatabase.getInstance().getReference("truyen");
                    ref2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            for (DataSnapshot dataSnapshot2 :snapshot2.getChildren())
                            {
                                Truyen truyen =dataSnapshot2.getValue(Truyen.class);
                                if (truyen.getTenTruyen().equals(yt.getTieude()))
                                {
                                    int a=truyen.getSoluongyt()+1;
                                    truyen.setSoluongyt(a);
                                    ref2.child(truyen.getTenTruyen()).setValue(truyen);
                                    ref2.removeEventListener(this);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error2) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addValueEventListener(valueEventListener);

    }
    public  boolean timkiem(ArrayList<yeuthich> listyt,yeuthich yt)
    {
        boolean t=false;
        for (int i=0;i<listyt.size();i++)
        {
            if ((listyt.get(i).getTieude().equals(yt.getTieude()))
                    && (listyt.get(i).getTentaikhoan().equals(yt.getTentaikhoan())))
            {
                t=true;
                break;
            }
        }
        return  t;
    }

   public void DeleteYT(yeuthich yt)
   {
       DatabaseReference myref=FirebaseDatabase.getInstance().getReference("dsyeuthich");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    yeuthich yt2=dataSnapshot.getValue(yeuthich.class);
                    if ( yt.getTieude().equals(yt2.getTieude()) && yt.getTentaikhoan().equals(yt2.getTentaikhoan())  )
                    {
                        myref.child(dataSnapshot.getKey()).removeValue();
                        System.out.println("xóa+++++++++++++--------------");
                        myref.removeEventListener(this);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


   }

   public void deletetruyen(Truyen truyen)
   {
       DatabaseReference myref1= FirebaseDatabase.getInstance().getReference("truyen");
       myref1.child(truyen.getTenTruyen()).removeValue();



       DatabaseReference myref=FirebaseDatabase.getInstance().getReference("dsyeuthich");
       myref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot:snapshot.getChildren())
               {
                   yeuthich yt2=dataSnapshot.getValue(yeuthich.class);
                   if ( truyen.getTenTruyen().equals(yt2.getTieude()) )
                   {
                       myref.child(dataSnapshot.getKey()).removeValue();
                       System.out.println("xóa+++++++++++++--------------");
                       myref.removeEventListener(this);
                   }
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

       DatabaseReference myref2=FirebaseDatabase.getInstance().getReference("danhgia");
       myref2.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot:snapshot.getChildren())
               {
                   DanhGia danhGia=dataSnapshot.getValue(DanhGia.class);
                   if (danhGia.getTenTruyen().equals(truyen.getTenTruyen()))
                   {
                       myref2.child(danhGia.getIDdanhgia()).removeValue();
                   }
               }
               myref2.removeEventListener(this);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

   }


    public void adduser(TaiKhoan us)
    {

        FirebaseAuth mauth=FirebaseAuth.getInstance();
        mauth.createUserWithEmailAndPassword(us.getmEmail(), us.getmMatKhau())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful())
            {
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference myref1= db.getReference("taikhoan");
                myref1.child(us.getmTenTaiKhoan()).setValue(us);
            }
            }
        });
    }

    public void updatetaikhoan(String email2,String pass2,TaiKhoan user)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email2,pass2)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        udep(user);
                    }
                });
        DatabaseReference db=FirebaseDatabase.getInstance().getReference("taikhoan");
        db.child(user.getmTenTaiKhoan()).setValue(user);
    }

    public void udep(TaiKhoan user1)
    {
        FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
        user2.updateEmail(user1.getmEmail())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user2.updatePassword(user1.getmMatKhau())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                System.out.println("Cập nhập user thành công");
                                            }
                                        }
                                    });
                        }
                    }
                });


    }
    public  void deletetaikhoan(TaiKhoan taiKhoan)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(taiKhoan.getmEmail(),taiKhoan.getmMatKhau())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            dlus(taiKhoan);
                                            System.out.println("xóa thành công");
                                        }
                                    }
                                });

                    }
                });
    }
    public void dlus(TaiKhoan taiKhoan2)
    {
        DatabaseReference db=FirebaseDatabase.getInstance().getReference("taikhoan");
        db.child(taiKhoan2.getmTenTaiKhoan()).removeValue();
        DatabaseReference db2=FirebaseDatabase.getInstance().getReference("dsyeuthich");

        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    yeuthich yt2=dataSnapshot.getValue(yeuthich.class);
                    if ( taiKhoan2.getmTenTaiKhoan().equals(yt2.getTentaikhoan()) )
                    {
                        db2.child(dataSnapshot.getKey()).removeValue();
                        String link="truyen/"+yt2.getTieude()+"/soluongyt";
                        DatabaseReference db3=FirebaseDatabase.getInstance().getReference(link);
                        db3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int currentValue = dataSnapshot.getValue(Integer.class);

                                // Giảm giá trị của trường con đi 1
                                int newValue = currentValue - 1;

                                // Cập nhật lại giá trị mới vào Realtime Database
                                db3.setValue(newValue);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }
                }
                db2.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updatepass(TaiKhoan tk)
    {
        String link= "taikhoan/"+tk.getmTenTaiKhoan();
        DatabaseReference db1=FirebaseDatabase.getInstance().getReference(link);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(tk.getmEmail(), tk.getmMatKhau())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
                        user2.updatePassword(tk.getmMatKhau())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            db1.setValue(tk);
                                            System.out.println("Cập nhập user thành công");
                                        }
                                    }
                                });
                    }
                });

    }

    public void AddDanhGia (DanhGia dg)
    {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("danhgia");
        DatabaseReference newObjectRef = ref.push();
        String objectId = newObjectRef.getKey();
        dg.setIDdanhgia(objectId);
        newObjectRef.setValue(dg);
    }
}