package com.example.auktion.activityAdmin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.auktion.R;
import com.example.auktion.model.Admin;

public class AdminProfileActivity extends AppCompatActivity {

    private Admin admin;
    private TextView tvUsername, tvNama;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        initId();

        admin = (Admin) getIntent().getSerializableExtra("adminDetailProfile");

        checkData();
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
    }

    private void checkData() {
        if (admin != null) {
            tvUsername.setText(admin.getUsername());
            tvNama.setText(admin.getNama_petugas());
        }
    }
    private void initId() {
        tvUsername = findViewById(R.id.tv_username_adminProfile);
        tvNama = findViewById(R.id.tv_nama_adminProfile);
        btnLogOut = findViewById(R.id.btn_logOut_adminProfile);
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}
