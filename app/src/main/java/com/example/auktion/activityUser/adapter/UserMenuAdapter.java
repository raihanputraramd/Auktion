package com.example.auktion.activityUser.adapter;

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
import com.example.auktion.activityUser.UserDetailItemActivity;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;

import java.util.ArrayList;

public class UserMenuAdapter extends RecyclerView.Adapter<UserMenuAdapter.UserMenuViewHolder> {

    private AppDatabase database;
    private Context context;
    private ArrayList<Barang> listBarang;

    public UserMenuAdapter(Context context, ArrayList<Barang> listBarang) {
        this.context = context;
        this.listBarang = listBarang;


        database = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }

    @NonNull
    @Override
    public UserMenuAdapter.UserMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_menu,
                parent, false);
        return new UserMenuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final UserMenuAdapter.UserMenuViewHolder holder, final int position) {
        final Barang barang = listBarang.get(position);

        holder.tvNamaBarang.setText(barang.getNama_barang());
        holder.tvHargaBarang.setText(barang.getHarga_awal());
        holder.tvTanggalBarang.setText(barang.getTgl());
        holder.cvItemUserMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Barang barangs = database.getBarangDAO()
                        .selectBarangDetail(listBarang.get(position).getId_barang());
                context.startActivity(UserDetailItemActivity.getActIntent((Activity) context)
                        .putExtra("data", barangs));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class UserMenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBarang, tvHargaBarang, tvTanggalBarang;
        CardView cvItemUserMenu;

        UserMenuViewHolder(View itemView) {
            super(itemView);
            cvItemUserMenu = itemView.findViewById(R.id.cv_item_userMenu);
            tvNamaBarang = itemView.findViewById(R.id.tv_namaBarang_userMenu);
            tvHargaBarang = itemView.findViewById(R.id.tv_hargaBarang_userMenu);
            tvTanggalBarang = itemView.findViewById(R.id.tv_tanggalBarang_userMenu);
        }
    }
}
