package com.example.appdoctruyen_v2.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.MainNoiDungTruyen;
import com.example.appdoctruyen_v2.R;
import com.example.appdoctruyen_v2.YeuThichFragment;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.database.firebasedt;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.yeuthich;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTruyenYeuThich extends RecyclerView.Adapter<adapterTruyenYeuThich.ViewHolder> {
    String tieudeyt="";
    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyenYeuThich(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
        notifyDataSetChanged();
    }

    public void setdata(Context context,ArrayList<Truyen> listTruyen){
        this.context = context;
        this.listTruyen = listTruyen;
        notifyDataSetChanged();

    }

    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen=filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public adapterTruyenYeuThich.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truyenv2, parent, false);
        adapterTruyenYeuThich.ViewHolder viewHolder = new adapterTruyenYeuThich.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTruyenYeuThich.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        position = holder.getAdapterPosition();
        Truyen truyen=(Truyen) listTruyen.get(position);
        holder.txtTenTruyen.setText(truyen.getTenTruyen());
        tieudeyt=truyen.getTenTruyen();

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtruyen);
        int finalPosition2 = position;
        holder.imgtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDeleteYeuThic(finalPosition2);
                //return false;
            }
        });
        int finalPosition3 = position;
        holder.txtTenTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDeleteYeuThic(finalPosition3);
                //return false;
            }
        });
    }
    private void DialogDeleteYeuThic(int position) {
        String tentkyt=tentaikhoan;

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(context);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdeleteyeuthic);
        //Click No mới thoát, click ngoài ko thoát
        // dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieude=listTruyen.get(position).getTenTruyen();
                System.out.println("================================"+tieudeyt);
                yeuthich yt=new yeuthich(tentaikhoan,tieude);
                firebasedt fb = new firebasedt();
                fb.DeleteYT(yt);
                listTruyen.remove(position);
                setdata(context, listTruyen);
                Toast.makeText(context,"Bỏ yêu thích thành công",Toast.LENGTH_SHORT).show();
                dialog.cancel();
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

    @Override
    public int getItemCount() {
        return listTruyen.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTruyen;
        ImageView imgtruyen;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenTruyen =  itemView.findViewById(R.id.textviewTenTruyenv2);
            //viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTenTruyen);
            imgtruyen=itemView.findViewById(R.id.imgNewTruyenv2);
        }
    }
}
