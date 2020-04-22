package com.example.auktion.activityUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.R;
import com.example.auktion.model.User;
import com.example.auktion.utils.AppDatabase;

public class UserProfileActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private TextView tvUsername, tvNamaUser, tvTelepon;
    private Button btnLogOut;
    
    private User user;
    private AppDatabase database;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initId();
        inisialisasiRoom();

        user = (User) getIntent().getSerializableExtra("userDetailProfile");

        checkData();
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
    }

    private void checkData() {
        if (user != null) {
            tvUsername.setText(user.getUsername());
            tvNamaUser.setText(user.getNama_lengkap());
            tvTelepon.setText(user.getTelp());
        }
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }

    private void inisialisasiRoom() {
        database = Room.databaseBuilder(this, AppDatabase.class,
                "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }

    private void initId() {
        ibBack = findViewById(R.id.ib_back_userProfile);
        btnLogOut = findViewById(R.id.btn_logOut_userProfile);
        tvUsername = findViewById(R.id.tv_username_userProfile);
        tvNamaUser = findViewById(R.id.tv_namaUser_userProfile);
        tvTelepon = findViewById(R.id.tv_telepon_userProfile);
    }
}
