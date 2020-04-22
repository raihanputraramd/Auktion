package com.example.auktion.activityPetugas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auktion.R;
import com.example.auktion.model.Barang;

import java.util.ArrayList;

public class PetugasPendataanBarangAdapter extends RecyclerView.Adapter<PetugasPendataanBarangAdapter.PetugasPendataanBarangViewHolder> {

    private Context context;
    private ArrayList<Barang> list;

    public PetugasPendataanBarangAdapter(Context context, ArrayList<Barang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PetugasPendataanBarangAdapter.PetugasPendataanBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pendataan_barang, parent, false);
        return new PetugasPendataanBarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetugasPendataanBarangAdapter.PetugasPendataanBarangViewHolder holder, int position) {
        Barang barang = list.get(position);
        holder.tvNamaBarang.setText(barang.getNama_barang());
        holder.tvHargaBarang.setText(barang.getHarga_awal());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PetugasPendataanBarangViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBarang, tvHargaBarang;

        PetugasPendataanBarangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.tv_barang_petugasPendataan);
            tvHargaBarang = itemView.findViewById(R.id.tv_harga_petugasPendataan);
        }
    }
}
