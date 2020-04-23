package com.example.auktion.activityUser;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.R;
import com.example.auktion.model.Barang;
import com.example.auktion.utils.AppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.textfield.TextInputLayout;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;

public class UserDetailItemActivity extends AppCompatActivity {

    private TextView tvNamaBarang;
    private TextView tvHargaBarang;
    private TextView tvDeskripsiBarang;
    private TextView tvTanggalBarang;
    private TextView tvStatusBarang;
    private ImageButton ibBack;
    private Button btnPasangPenawaran;
    private Barang barang;
    private AppDatabase database;

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, UserDetailItemActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_item);

        inisialisasiRoom();
        inisialisasiStetho();
        initId();

        btnPasangPenawaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogForm();
            }
        });
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        barang = (Barang) getIntent().getSerializableExtra("data");
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
        int statusBarang = barang.getStatus();
        if (statusBarang == 1) {
            tvStatusBarang.setText(R.string.dibuka);
            btnPasangPenawaran.setClickable(true);
        } else if (statusBarang == 0) {
            tvStatusBarang.setText(R.string.ditutup);
            btnPasangPenawaran.setText(R.string.tidak_dapat_menawar);
            btnPasangPenawaran.setClickable(false);
        }
    }

    private void initId() {
        tvNamaBarang = findViewById(R.id.tv_namaBarang_userDetailItem);
        tvHargaBarang = findViewById(R.id.tv_hargaBarang_userDetailItem);
        tvDeskripsiBarang = findViewById(R.id.tv_deskripsiBarang_userDetailItem);
        tvTanggalBarang = findViewById(R.id.tv_tanggalBarang_userDetailItem);
        ibBack = findViewById(R.id.btn_back_userDetailItem);
        btnPasangPenawaran = findViewById(R.id.btn_user_pasangPenawaran);
        tvStatusBarang = findViewById(R.id.tv_status_userDetailItem);
    }

    private void dialogForm() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(UserDetailItemActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_user_penawaran, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        final TextInputLayout edtpenawaran = dialogView.findViewById(R.id.edt_penawaran_UserDetailItem);

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int value = 0;
                int value2 = 0;
                String finalValue;
                try {
                    value = Integer.parseInt(edtpenawaran.getEditText().getText().toString());
                    value2 = Integer.parseInt(tvHargaBarang.getText().toString());
                    finalValue = String.valueOf(value);
                    if (value > value2) {
                        database.getBarangDAO().updateHarga(finalValue, barang.getId_barang());
                        tvHargaBarang.setText(finalValue);
                    } else {
                        Toasty.error(UserDetailItemActivity.this
                                , "Mohon masukan nominal dengan benar"
                                , Toasty.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void inisialisasiStetho() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    private void inisialisasiRoom() {
        database = Room.databaseBuilder(this, AppDatabase.class,
                "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }


}
