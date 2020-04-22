package com.example.auktion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.activityAdmin.AdminMenuActivity;
import com.example.auktion.activityPetugas.PetugasMenuActivity;
import com.example.auktion.activityUser.UserMenuActivity;
import com.example.auktion.activityUser.UserProfileActivity;
import com.example.auktion.activityUser.UserRegisterActivity;
import com.example.auktion.model.Admin;
import com.example.auktion.model.User;
import com.example.auktion.presenter.ILoginPresenter;
import com.example.auktion.presenter.LoginPresenter;
import com.example.auktion.utils.AppDatabase;
import com.example.auktion.view.ILoginView;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.textfield.TextInputLayout;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private TextInputLayout edtUsername;
    private TextInputLayout edtPassword;
    private Button btnLogin;

    private Context context;
    private User user;
    private Admin admin;
    private AppDatabase database;

    private ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initId();
        initDb();
        initStetho();

        loginPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkData();
            }
        });
        gotoRegister();

    }

    @Override
    public void onLoginSuccess(String message) {
        Toasty.success(this, message, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(String message) {
        Toasty.error(this, message, Toasty.LENGTH_SHORT).show();
    }

    private void initId() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void getEdt() {
        user = database.getuserDAO().userlogin(edtUsername.getEditText().getText().toString().trim(),
                edtPassword.getEditText().getText().toString().trim());

        admin = database.getAdminDao().adminLogin(edtUsername.getEditText().getText().toString().trim(),
                edtPassword.getEditText().getText().toString().trim());

        loginPresenter.onLogin(edtUsername.getEditText().getText().toString().trim(),
                edtPassword.getEditText().getText().toString().trim());
    }

    private void checkData() {
        getEdt();
        if (user != null) {
            Intent intent = new Intent(LoginActivity.this, UserMenuActivity.class);
            intent.putExtra("profileUser", user);
            startActivity(intent);
        } else if (admin != null) {
            Intent intent = new Intent(LoginActivity.this, AdminMenuActivity.class);
            intent.putExtra("profileAdmin", admin);
            startActivity(intent);
        } else if (edtUsername.getEditText().getText().toString().trim().equals("petugas") &&
                edtPassword.getEditText().getText().toString().trim().equals("petugas")) {
            Intent intent = new Intent(LoginActivity.this, PetugasMenuActivity.class);
            startActivity(intent);
        } else {
            onLoginError("Mohon masukan data dengan benar");
        }
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    private void initDb() {
        database = Room.databaseBuilder(this, AppDatabase.class, "dbLelang.db")
                .allowMainThreadQueries()
                .build();
    }

    private void gotoRegister() {
        TextView gotoRegister = findViewById(R.id.tv_gotoRegister_login);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        UserRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

