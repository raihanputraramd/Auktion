package com.example.auktion.adminActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.R;
import com.example.auktion.data.AdminDAO;
import com.example.auktion.model.Admin;
import com.example.auktion.presenter.AdminRegisterPresenter;
import com.example.auktion.utils.AppDatabase;
import com.example.auktion.view.IUserRegisterView;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.textfield.TextInputLayout;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;

public class AdminRegisterActivity extends AppCompatActivity implements IUserRegisterView {

    private TextInputLayout edtUsername, edtPassword, edtNamaLengkap;

    private AdminDAO adminDAO;

    private Admin admin;

    private AdminRegisterPresenter adminRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        edtUsername = findViewById(R.id.edt_username_registerAdmin);
        edtPassword = findViewById(R.id.edt_password_registerAdmin);
        edtNamaLengkap = findViewById(R.id.edt_namaLengkap_registerAdmin);

        Button btnRegisterAdmin = findViewById(R.id.btn_register_admin);

        adminRegisterPresenter = new AdminRegisterPresenter(this);
        inisialisasiDBRoom();
        inisialisasiStetho();

        btnRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin = new Admin(edtNamaLengkap.getEditText().getText().toString().trim(),
                        edtUsername.getEditText().getText().toString().trim(),
                        edtPassword.getEditText().getText().toString().trim());

                adminRegisterPresenter.onRegister(edtNamaLengkap.getEditText().getText().toString().trim(),
                        edtUsername.getEditText().getText().toString().trim(),
                        edtPassword.getEditText().getText().toString().trim());
            }
        });
    }

    @Override
    public void onRegisterSuccess(String message) {
        Toasty.success(this, message, Toasty.LENGTH_SHORT).show();
        adminDAO.insertAdmin(admin);
    }

    @Override
    public void onRegisterError(String message) {
        Toasty.error(this, message, Toasty.LENGTH_SHORT).show();
        setToNull();
    }


    private void inisialisasiDBRoom() {
        adminDAO = Room.databaseBuilder(this, AppDatabase.class, "dbLelang.db")
                .allowMainThreadQueries()
                .build()
                .getAdminDao();
    }


    private void inisialisasiStetho() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    private void setToNull() {
        edtUsername.getEditText().setText("");
        edtNamaLengkap.getEditText().setText("");
        edtPassword.getEditText().setText("");
    }
}
