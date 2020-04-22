package com.example.auktion.activityAdmin.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.auktion.R;
import com.example.auktion.activityAdmin.AdminDetailItemActivity;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;

import java.util.ArrayList;

public class AdminMenuAdapter extends RecyclerView.Adapter<AdminMenuAdapter.AdminMenuViewHolder> {

    private AppDatabase database;
    private Context context;
    private ArrayList<Barang> listBarang;

    public AdminMenuAdapter(Context context, ArrayList<Barang> listBarang) {
        this.context = context;
        this.listBarang = listBarang;

        database = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }

    @NonNull
    @Override
    public AdminMenuAdapter.AdminMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_menu,
                parent, false);
        return new AdminMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminMenuAdapter.AdminMenuViewHolder holder, final int position) {
        Barang barang = listBarang.get(position);

        holder.tvNamaBarang.setText(barang.getNama_barang());
        holder.tvHargaBarang.setText(barang.getHarga_awal());
        holder.tvTanggalBarang.setText(barang.getTgl());

        holder.cvAdminMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Barang barangs = database.getBarangDAO().selectBarangDetail(listBarang.get(position)
                        .getId_barang());
                context.startActivity(AdminDetailItemActivity.getActIntent((Activity) context)
                        .putExtra("dataAdmin",barangs));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class AdminMenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBarang, tvHargaBarang, tvTanggalBarang;
        CardView cvAdminMenu;

        AdminMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.tv_namaBarang_adminMenu);
            tvHargaBarang = itemView.findViewById(R.id.tv_hargaBarang_adminMenu);
            tvTanggalBarang = itemView.findViewById(R.id.tv_tanggalBarang_adminMenu);
            cvAdminMenu = itemView.findViewById(R.id.cv_item_adminMenu);
        }
    }
}
