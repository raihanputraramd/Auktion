package com.example.auktion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.data.BarangDAO;
import com.example.auktion.model.Barang;
import com.example.auktion.presenter.AddBarangPresenter;
import com.example.auktion.utils.AppDatabase;
import com.example.auktion.view.IAddBarangView;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;

public class AddBarangActivity extends AppCompatActivity implements IAddBarangView {

    private ImageButton btnBack;
    private Button btnKonfirmasi;
    private TextInputLayout etNamaBarang = null;
    private TextInputLayout etHargaBarang = null;
    private TextInputLayout etDeskripsiBarang = null;
    private TextView etTanggalBarang;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private BarangDAO barangDAO;
    private Barang barang;
    private AddBarangPresenter addBarangPresenter;
    private ArrayList<Barang> listBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang);
        initId();
        inisialisasiDBRoom();
        inisialisasiStetho();

        addBarangPresenter = new AddBarangPresenter(this);

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etTanggalBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateGetter();
            }
        });
        dateSetter();
    }

    @Override
    public void confirmAdd(String message) {
        Toasty.success(this, message, Toasty.LENGTH_SHORT).show();
        barangDAO.insertBarang(barang);
        Intent intent = new Intent(AddBarangActivity.this, PendataanBarangActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void missingItem(String message) {
        Toasty.error(this, message, Toasty.LENGTH_SHORT).show();
    }

    private void initId() {
        btnBack = findViewById(R.id.btn_back_addBarang);
        btnKonfirmasi = findViewById(R.id.btn_konfirmasi_addBarang);
        etNamaBarang = findViewById(R.id.edt_nama_addBarang);
        etHargaBarang = findViewById(R.id.edt_harga_addBarang);
        etDeskripsiBarang = findViewById(R.id.edt_deskripsi_addBarang);
        etTanggalBarang = findViewById(R.id.edt_tgl_addBarang);
    }

    private void getData() {
        barang = new Barang(Objects.requireNonNull(etNamaBarang.getEditText()).getText().toString(),
                Objects.requireNonNull(etHargaBarang.getEditText()).getText().toString().trim(),
                Objects.requireNonNull(etDeskripsiBarang.getEditText()).getText().toString(),
                etTanggalBarang.getText().toString(), 1);

        addBarangPresenter.addBarang(barang);
    }

    private void inisialisasiDBRoom() {
        barangDAO = Room.databaseBuilder(this, AppDatabase.class, "dbLelang.db")
                .allowMainThreadQueries()
                .build()
                .getBarangDAO();
    }

    private void inisialisasiStetho() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    private void dateGetter() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH);
        int day = cal.get(java.util.Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(AddBarangActivity.this,
                android.R.style.Theme_Holo_Dialog_MinWidth,
                dateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void dateSetter() {
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "/" + month + "/" + year;
                etTanggalBarang.setText(date);
            }
        };
    }
}
