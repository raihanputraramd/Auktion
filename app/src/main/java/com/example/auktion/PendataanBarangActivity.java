package com.example.auktion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.auktion.activityPetugas.adapter.PetugasPendataanBarangAdapter;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class PendataanBarangActivity extends AppCompatActivity {

    private RecyclerView rvPetugasPendataanBarang;
    private ImageButton ibBack;
    private FloatingActionButton fabAddItem;
    private SwipeRefreshLayout swipeRefreshLayout;

    private PetugasPendataanBarangAdapter adapter;

    private ArrayList<Barang> list;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendataan_barang);
        initId();
        initDb();

        list = new ArrayList<>();
        list.addAll(Arrays.asList(database.getBarangDAO().selectAllBarang()));

        showRecyclerView(list);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAddBarang();
            }
        });
    }

    private void initId() {
        rvPetugasPendataanBarang = findViewById(R.id.rv_petugas_petugasPendataanBarang);
        ibBack = findViewById(R.id.ib_petugas_petugasPendataanBarang);
        fabAddItem = findViewById(R.id.fab_addBarang_petugasPendataanBarang);
        swipeRefreshLayout = findViewById(R.id.sw_layout);
    }

    private void gotoAddBarang() {
        Intent intent = new Intent(PendataanBarangActivity.this, AddBarangActivity.class);
        startActivity(intent);
    }

    private void initDb() {
        database = Room.databaseBuilder(this, AppDatabase.class,
                "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }

    private void showRecyclerView(ArrayList<Barang> barangArrayList) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvPetugasPendataanBarang.setLayoutManager(linearLayoutManager);
        rvPetugasPendataanBarang.setHasFixedSize(true);
        adapter = new PetugasPendataanBarangAdapter(PendataanBarangActivity.this,
                barangArrayList);
        adapter.notifyDataSetChanged();
        rvPetugasPendataanBarang.setAdapter(adapter);
    }
}
