package com.example.auktion.activityAdmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.R;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class AdminDetailItemActivity extends AppCompatActivity {

    private TextView tvNamaBarang, tvTanggalBarang, tvHargaBarang, tvDeskripsiBarang, tvStatusBarang;
    private ImageButton ibBack;
    private Barang barang;
    private AppDatabase database;

    private int statusBarang;

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, AdminDetailItemActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_item);
        initId();
        initRoom();
        initStetho();


        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        barang = (Barang) getIntent().getSerializableExtra("dataAdmin");
        checkData();

    }

    private void checkData() {
        if (barang != null) {
            tvNamaBarang.setText(barang.getNama_barang());
            tvHargaBarang.setText(barang.getHarga_awal());
            tvDeskripsiBarang.setText(barang.getDeskripsi_barang());
            tvTanggalBarang.setText(barang.getTgl());
            checkStatus();
        }
    }

    private void checkStatus() {
        statusBarang = barang.getStatus();
        if (statusBarang == 1) {
             tvStatusBarang.setText("Dibuka");
        } else if (statusBarang == 0) {
            tvStatusBarang.setText("Ditutup");
        }
    }

    private void initId() {
        tvNamaBarang = findViewById(R.id.tv_namabarang_adminDetailItem);
        tvTanggalBarang = findViewById(R.id.tv_tanggalBarang_adminDetailItem);
        tvHargaBarang = findViewById(R.id.tv_hargaBarang_adminDetailItem);
        tvDeskripsiBarang = findViewById(R.id.tv_deskripsiBarang_adminDetailBarang);
        tvStatusBarang = findViewById(R.id.tv_status_adminDetailBarang);
        ibBack = findViewById(R.id.btn_back_adminDetailBarang);
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
}
