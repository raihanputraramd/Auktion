package com.example.auktion.activityPetugas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.auktion.PendataanBarangActivity;
import com.example.auktion.R;
import com.example.auktion.activityPetugas.adapter.PetugasMenuAdapter;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;

public class PetugasMenuActivity extends AppCompatActivity {

    private FloatingActionButton fabMain;
    private FloatingActionButton fabPendataan;
    private FloatingActionButton fabLogOut;
    private Animation fabOpen;
    private Animation fabClose;
    private Animation rotateForward;
    private Animation rotateBackward;
    private RecyclerView rvPetugasMenu;
    private TextView tvFabPendataanBarang;
    private TextView tvFabLogOut;
    private TextView tvWarning;

    private AppDatabase database;
    private PetugasMenuAdapter adapter;

    private boolean isOpen = false;
    private ArrayList<Barang> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_menu);

        initId();
        initDb();
        initStetho();

        list = new ArrayList<>();
        list.addAll(Arrays.asList(database.getBarangDAO().selectAllBarang()));

        checkDataBarang(list);

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });
        fabPendataan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gotoPendataan();
            }
        });
        fabLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
    }

    private void animateFab() {
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        if (isOpen) {
            fabMain.startAnimation(rotateForward);
            fabPendataan.startAnimation(fabClose);
            fabLogOut.startAnimation(fabClose);
            fabPendataan.setClickable(false);
            fabLogOut.setClickable(false);
            tvFabPendataanBarang.startAnimation(fabClose);
            tvFabLogOut.startAnimation(fabClose);
            isOpen = false;
        } else {
            fabMain.startAnimation(rotateForward);
            fabPendataan.startAnimation(fabOpen);
            fabLogOut.startAnimation(fabOpen);
            fabPendataan.setClickable(true);
            fabLogOut.setClickable(true);
            tvFabPendataanBarang.startAnimation(fabOpen);
            tvFabLogOut.startAnimation(fabOpen);
            isOpen = true;
        }
    }

    private void initId() {
        fabMain = findViewById(R.id.fab_main_petugasMenu);
        fabPendataan = findViewById(R.id.fab_sub_pendataan_petugasMenu);
        rvPetugasMenu = findViewById(R.id.rv_petugasMenu);
        tvFabPendataanBarang = findViewById(R.id.tvFab_PendataanBarang_petugasMenu);
        tvWarning = findViewById(R.id.tv_warning_petugasMenu);
        fabLogOut = findViewById(R.id.fab_sub_logOut_petugasMenu);
        tvFabLogOut = findViewById(R.id.tvFab_logOut_PetugasMenu);
    }

    private void initDb() {
        database = Room.databaseBuilder(this, AppDatabase.class,
                "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    private void gotoPendataan() {
        Intent intent = new Intent(PetugasMenuActivity.this,
                PendataanBarangActivity.class);
        startActivity(intent);
    }

    private void generateLaporan() {

    }

    private void checkDataBarang(ArrayList<Barang> barangArrayList) {
        if (database.getBarangDAO().checkEmptyBarang() == 0) {
            tvWarning.setVisibility(View.VISIBLE);
            rvPetugasMenu.setVisibility(View.INVISIBLE);
        } else {
            showRecyclerView(list);
            tvWarning.setVisibility(View.INVISIBLE);
            rvPetugasMenu.setVisibility(View.VISIBLE);
        }
    }

    private void showRecyclerView(ArrayList<Barang> barangArrayList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvPetugasMenu.setLayoutManager(linearLayoutManager);
        rvPetugasMenu.setHasFixedSize(true);
        adapter = new PetugasMenuAdapter(PetugasMenuActivity.this, barangArrayList);
        adapter.notifyDataSetChanged();
        rvPetugasMenu.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
