package com.example.auktion.userActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.R;
import com.example.auktion.data.UserDAO;
import com.example.auktion.model.User;
import com.example.auktion.presenter.UserRegisterPresenter;
import com.example.auktion.utils.AppDatabase;
import com.example.auktion.view.IUserRegisterView;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.textfield.TextInputLayout;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;

public class UserRegisterActivity extends AppCompatActivity implements IUserRegisterView {

    private TextInputLayout edtUsername, edtPassword, edtNamaLengkap, edtTelepon;

    private UserDAO userDAO;

    private User user;

    private UserRegisterPresenter userRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        edtUsername = findViewById(R.id.edt_username_registerAdmin);
        edtPassword = findViewById(R.id.edt_password_registerAdmin);
        edtNamaLengkap = findViewById(R.id.edt_namaLengkap_registerAdmin);
        edtTelepon = findViewById(R.id.edt_telepon_register);

        Button btnRegister = findViewById(R.id.btnRegister_user);

        inisialisasiStetho();
        inisialisasiDBRoom();

        userRegisterPresenter = new UserRegisterPresenter(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User(edtUsername.getEditText().getText().toString().trim(),
                        edtPassword.getEditText().getText().toString(), edtNamaLengkap.getEditText().getText().toString(),
                        edtTelepon.getEditText().getText().toString());

                userRegisterPresenter.onRegister(edtUsername.getEditText().getText().toString().trim(),
                        edtPassword.getEditText().getText().toString(), edtNamaLengkap.getEditText().getText().toString(),
                        edtTelepon.getEditText().getText().toString());

            }
        });
    }

    @Override
    public void onRegisterSuccess(String message) {
        Toasty.success(this, message, Toasty.LENGTH_SHORT).show();
        userDAO.insertUser(user);
    }

    @Override
    public void onRegisterError(String message) {
        Toasty.error(this, message, Toasty.LENGTH_SHORT).show();
        setToNull();
    }


    private void inisialisasiStetho() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    private void inisialisasiDBRoom() {
        userDAO = Room.databaseBuilder(this, AppDatabase.class, "dbLelang.db")
                .allowMainThreadQueries()
                .build()
                .getuserDAO();
    }

    private void setToNull() {
        edtUsername.getEditText().setText("");
        edtTelepon.getEditText().setText("");
        edtNamaLengkap.getEditText().setText("");
        edtPassword.getEditText().setText("");
    }

}
