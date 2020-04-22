package com.example.auktion.activityUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.auktion.R;
import com.example.auktion.activityUser.adapter.UserMenuAdapter;
import com.example.auktion.model.Barang;
import com.example.auktion.model.User;
import com.example.auktion.utils.AppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;

public class UserMenuActivity extends AppCompatActivity {
    private ImageButton ibProfile;
    private TextView tvWarning;
    private RecyclerView rvUserMenu;
    private AppDatabase database;
    private UserMenuAdapter adapter;
    private ArrayList<Barang> list;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        initId();
        initRoom();
        initStetho();

        user = (User) getIntent().getSerializableExtra("profileUser");


        list = new ArrayList<>();
        list.addAll(Arrays.asList(database.getBarangDAO().selectAllBarang()));

        checkDataBarang(list);

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoProfile();
            }
        });
    }

    private void showRecyclerView(ArrayList<Barang> barangs) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvUserMenu.setLayoutManager(linearLayoutManager);
        rvUserMenu.setHasFixedSize(true);
        adapter = new UserMenuAdapter(UserMenuActivity.this, barangs);
        adapter.notifyDataSetChanged();
        rvUserMenu.setAdapter(adapter);
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    private void initRoom() {
        database = Room.databaseBuilder(this, AppDatabase.class,
                "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }

    private void gotoProfile() {
        Intent intent = new Intent(UserMenuActivity.this, UserProfileActivity.class);
        intent.putExtra("userDetailProfile", user);
        startActivity(intent);
    }

    private void initId() {
        ibProfile = findViewById(R.id.ib_user_profile);
        rvUserMenu = findViewById(R.id.rv_userMenu);
        tvWarning = findViewById(R.id.tv_warning_userMenu);
    }

    private void checkDataBarang(ArrayList<Barang> barangArrayList) {
        if (database.getBarangDAO().checkEmptyBarang() == 0) {
            tvWarning.setVisibility(View.VISIBLE);
            rvUserMenu.setVisibility(View.INVISIBLE);
        } else {
            showRecyclerView(list);
            tvWarning.setVisibility(View.INVISIBLE);
            rvUserMenu.setVisibility(View.VISIBLE);
        }
    }
}
