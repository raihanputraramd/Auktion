package com.example.auktion.activityPetugas.adapter;

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
import com.example.auktion.activityPetugas.PetugasDetailItemActivity;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;

import java.util.ArrayList;

public class PetugasMenuAdapter extends RecyclerView.Adapter<PetugasMenuAdapter.PetugasMenuViewHolder> {

    private AppDatabase database;
    private Context context;
    private ArrayList<Barang> listBarang;

    public PetugasMenuAdapter(Context context, ArrayList<Barang> listBarang) {
        this.context = context;
        this.listBarang = listBarang;

        database = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }

    @NonNull
    @Override
    public PetugasMenuAdapter.PetugasMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_petugas_menu, parent, false);
        return new PetugasMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetugasMenuAdapter.PetugasMenuViewHolder holder, final int position) {
        Barang barang = listBarang.get(position);

        holder.tvNamaBarang.setText(barang.getNama_barang());
        holder.tvHargaBarang.setText(barang.getHarga_awal());
        holder.tvTanggalBarang.setText(barang.getTgl());
        holder.cvPetugasMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Barang barangs = database.getBarangDAO().selectBarangDetail(listBarang.get(position)
                        .getId_barang());
                context.startActivity(PetugasDetailItemActivity.getActIntent((Activity) context)
                        .putExtra("dataPetugas", barangs));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class PetugasMenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaBarang, tvHargaBarang, tvTanggalBarang;
        CardView cvPetugasMenu;

        PetugasMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.tv_namaBarang_petugasMenu);
            tvHargaBarang = itemView.findViewById(R.id.tv_hargaBarang_petugasMenu);
            tvTanggalBarang = itemView.findViewById(R.id.tv_tanggalBarang_petugasMenu);
            cvPetugasMenu = itemView.findViewById(R.id.cv_item_petugasMenu);
        }
    }
}
