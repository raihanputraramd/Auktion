package com.example.auktion.activityPetugas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.R;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;

public class PetugasDetailItemActivity extends AppCompatActivity {

    private TextView tvNamaBarang, tvTanggalBarang, tvHargaBarang, tvDeskripsiBarang;
    private ImageButton ibBack;
    private Button btnSetStatus;
    private Barang barang;
    private AppDatabase database;

    private int isOpenForLelang;

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, PetugasDetailItemActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_detail_item);
        initId();
        initRoom();
        initStetho();


        barang = (Barang) getIntent().getSerializableExtra("dataPetugas");
        ibBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSetStatus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                formStatusBarang();
            }
        });
        checkData();
        statusBarang();
    }

    private void formStatusBarang() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(PetugasDetailItemActivity.this);
        dialog.setTitle("Mengubah Status ?");
        dialog.setCancelable(true)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int statusBarang = barang.getStatus();
                        if (statusBarang == 1) {
                            isOpenForLelang = 0;
                            btnSetStatus.setText("Ditutup");
                            Toasty.success(PetugasDetailItemActivity.this,
                                    "Berhasil Merubah Status Barang Ke Ditutup",
                                    Toasty.LENGTH_SHORT).show();
                            database.getBarangDAO().updateStatus(isOpenForLelang, barang.getId_barang());
                        } else {
                            isOpenForLelang = 1;
                            btnSetStatus.setText("Dibuka");
                            Toasty.success(PetugasDetailItemActivity.this,
                                    "Berhasil Merubah Status Barang Ke Dibuka",
                                    Toasty.LENGTH_SHORT).show();
                            database.getBarangDAO().updateStatus(isOpenForLelang, barang.getId_barang());
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.show();
    }

    private void statusBarang() {
        int statusBarang = barang.getStatus();
        if (statusBarang == 1) {
            btnSetStatus.setText("Dibuka");
        } else if (statusBarang == 0) {
            btnSetStatus.setText("Ditutup");
        }
    }


    private void checkData() {
        if (barang != null) {
            try {
                tvNamaBarang.setText(barang.getNama_barang());
                tvHargaBarang.setText(barang.getHarga_awal());
                tvDeskripsiBarang.setText(barang.getDeskripsi_barang());
                tvTanggalBarang.setText(barang.getTgl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toasty.error(this, "Error", Toasty.LENGTH_SHORT).show();
        }
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

    private void initId() {
        tvNamaBarang = findViewById(R.id.tv_namaBarang_petugasDetailItem);
        tvTanggalBarang = findViewById(R.id.tv_tanggalBarang_petugasDetailItem);
        tvHargaBarang = findViewById(R.id.tv_hargaBarang_petugasDetailItem);
        tvDeskripsiBarang = findViewById(R.id.tv_deskripsiBarang_petugasDetailItem);
        ibBack = findViewById(R.id.btn_back_petugasDetailItem);
        btnSetStatus = findViewById(R.id.btn_bukaLelang_petugasDetailItem);
    }
}
