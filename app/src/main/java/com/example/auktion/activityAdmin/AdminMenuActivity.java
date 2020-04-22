package com.example.auktion.activityAdmin;

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
import com.example.auktion.activityAdmin.adapter.AdminMenuAdapter;
import com.example.auktion.model.Admin;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;

public class AdminMenuActivity extends AppCompatActivity {

    private FloatingActionButton fabMain, fabProfile, fabPendataan;
    private RecyclerView rvAdminMenu;
    private TextView tvWarning, tvFabProfile, tvFabPendataanBarang;
    private boolean isOpen = false;
    private AppDatabase database;
    private ArrayList<Barang> list;
    private Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        initId();
        initDb();
        initStetho();

        admin = (Admin) getIntent().getSerializableExtra("profileAdmin");

        list = new ArrayList<>();
        list.addAll(Arrays.asList(database.getBarangDAO().selectAllBarang()));
//        list.addAll(DataTest.getListData());

        checkDataBarang(list);

        showRecyclerView(list);
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });
        fabProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoProfile();
            }
        });
        fabPendataan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPendataan();
            }
        });
    }

    private void animateFab() {
        Animation fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        Animation fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        Animation rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        Animation rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        if (isOpen) {
            fabMain.startAnimation(rotateForward);
            fabProfile.startAnimation(fabClose);
            fabPendataan.startAnimation(fabClose);
            tvFabProfile.startAnimation(fabClose);
            tvFabPendataanBarang.startAnimation(fabClose);
            fabProfile.setClickable(false);
            fabPendataan.setClickable(false);
            isOpen = false;
        } else {
            fabMain.startAnimation(rotateForward);
            fabProfile.startAnimation(fabOpen);
            fabPendataan.startAnimation(fabOpen);
            tvFabProfile.startAnimation(fabOpen);
            tvFabPendataanBarang.startAnimation(fabOpen);
            fabProfile.setClickable(true);
            fabPendataan.setClickable(true);
            isOpen = true;
        }
    }

    private void showRecyclerView(ArrayList<Barang> barangArrayList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvAdminMenu.setLayoutManager(linearLayoutManager);
        rvAdminMenu.setHasFixedSize(true);
        AdminMenuAdapter adapter = new AdminMenuAdapter(AdminMenuActivity.this, barangArrayList);
        adapter.notifyDataSetChanged();
        rvAdminMenu.setAdapter(adapter);
    }

    private void initId() {
        fabMain = findViewById(R.id.fab_main_adminMenu);
        fabProfile = findViewById(R.id.fab_sub_profile_adminMenu);
        fabPendataan = findViewById(R.id.fab_sub_pendataan_adminMenu);
        tvWarning = findViewById(R.id.tv_warning_adminMenu);
        tvFabProfile = findViewById(R.id.tvFab_profile_adminMenu);
        rvAdminMenu = findViewById(R.id.rv_adminMenu);
        tvFabPendataanBarang = findViewById(R.id.tvFab_pendataanBarang_adminMenu);
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

    private void gotoProfile() {
        Intent intent = new Intent(AdminMenuActivity.this,
                AdminProfileActivity.class);
        intent.putExtra("adminDetailProfile", admin);
        startActivity(intent);
    }

    private void gotoPendataan() {
        Intent intent = new Intent(AdminMenuActivity.this,
                PendataanBarangActivity.class);
        startActivity(intent);
    }

    private void checkDataBarang(ArrayList<Barang> barangArrayList) {
        if (database.getBarangDAO().checkEmptyBarang() == 0) {
            tvWarning.setVisibility(View.VISIBLE);
            rvAdminMenu.setVisibility(View.INVISIBLE);
        } else {
            showRecyclerView(list);
            tvWarning.setVisibility(View.INVISIBLE);
            rvAdminMenu.setVisibility(View.VISIBLE);
        }
    }
}
