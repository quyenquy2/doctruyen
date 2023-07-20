package com.example.appdoctruyen_v2.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.R;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.database.firebasedt;
import com.example.appdoctruyen_v2.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.userViewHolder> {

    public Context context;
    public  List<TaiKhoan> muserList;

    private iclickitemuser iclickitemuser;


    public  interface iclickitemuser
    {
        void updateus(TaiKhoan taiKhoan);
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public  List<TaiKhoan> getMuserList() {
        return muserList;
    }

    public void setMuserList(List<TaiKhoan> muserList) {
        this.muserList = muserList;
    }


    public userAdapter(Context context, userAdapter.iclickitemuser iclickitemuser,ArrayList<TaiKhoan> userArl) {
        this.context=context;
        this.iclickitemuser = iclickitemuser;
        this.muserList= userArl;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<TaiKhoan> filteredList) {
        muserList=filteredList;
        notifyDataSetChanged();
    }
    public   void setdata(List<TaiKhoan> list)
    {   this.context=context;
        this.muserList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public userAdapter.userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        userAdapter.userViewHolder viewHolder= new userAdapter.userViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder,@SuppressLint("RecyclerView") int position) {
        final TaiKhoan user =muserList.get(position);
        position =holder.getAdapterPosition();
        databasedoctruyen databaseDocTruyen;
        databaseDocTruyen = new databasedoctruyen(context);

        holder.tv_username.setText(user.getmTenTaiKhoan());
        holder.tv_mail.setText(user.getmEmail());


        int finalPosition = position;
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iclickitemuser.updateus(user);
            }

        });
        holder.btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Xác nhận xóa người dùng!")
                                    .setMessage("Bạn chắc chứ?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            firebasedt fb=new firebasedt();
                                            fb.deletetaikhoan(user);
                                            //Xóa trong SQL
                                            muserList.remove(finalPosition);
                                            //Cập nhật lại listview
                                            setdata( muserList);
                                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .setNegativeButton("No",null)
                                    .show();
                    }
                }
        );
    }


    @Override
    public int getItemCount() {
        return muserList.size();
    }


    public class userViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_username;
        private TextView tv_mail;
        private Button btnupdate;
        private Button btndelete;
        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_user);
            tv_mail =itemView.findViewById(R.id.tv_mail);
            btnupdate= itemView.findViewById(R.id.btnupdate);
            btndelete= itemView.findViewById(R.id.btndelete);
        }
    }


}
