package com.example.appdoctruyen_v2.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.R;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterQuanLy extends RecyclerView.Adapter<adapterQuanLy.ViewHolder> {

    private Context context;
    private ArrayList<Truyen> listTruyen;
    private  intercapnhattruyen intercapnhattruyen;
    public Truyen truyen;

    public interface intercapnhattruyen {
        void updatetruyen(Truyen truyen2);
        void xoatruyen(Truyen truyen);
    }

    public adapterQuanLy(Context context, adapterQuanLy.intercapnhattruyen intercapnhattruyen, ArrayList<Truyen> listTruyen ) {
        this.context = context;
        this.listTruyen = listTruyen;
        this.intercapnhattruyen = intercapnhattruyen;
        notifyDataSetChanged();
    }


    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen=filteredList;
        notifyDataSetChanged();
    }


    public void setdata(ArrayList<Truyen> listTruyen){
        this.context = context;
        this.listTruyen = listTruyen;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public adapterQuanLy.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtruyen, parent, false);
        adapterQuanLy.ViewHolder viewHolder = new adapterQuanLy.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterQuanLy.ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        truyen=(Truyen) listTruyen.get(position);
        holder.txtTenTruyen.setText(truyen.getTenTruyen());
        String a= truyen.getSoluongyt() +" Lượt yêu thích!";
        holder.tv_slyt.setText(a);
        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtruyen);

        int finalPosition = position;
        holder.imgtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDelete(finalPosition);
            }
        });

        int finalPosition1 = position;
        holder.txtTenTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDelete(finalPosition1);
            }
        });
    }


    //Dialog Delete
    private void DialogDelete(int position) {
        Truyen truyen=(Truyen) listTruyen.get(position);
        databasedoctruyen databaseDocTruyen;
        databaseDocTruyen = new databasedoctruyen(context);

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(context);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdelete);
        /*Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);*/

        //Ánh xạ
        Button btnDelete = dialog.findViewById(R.id.buttonDelete);
        Button btnUpdate = dialog.findViewById(R.id.buttonUpdate);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                intercapnhattruyen.xoatruyen(truyen);

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            intercapnhattruyen.updatetruyen(truyen);
                dialog.cancel();

            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listTruyen.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTruyen,tv_slyt;
        ImageView imgtruyen;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenTruyen =  itemView.findViewById(R.id.textviewTenTruyen);
            tv_slyt= itemView.findViewById(R.id.tv_soluongyt2);
            //viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTenTruyen);
            imgtruyen=itemView.findViewById(R.id.imgNewTruyen);
        }
    }


}
