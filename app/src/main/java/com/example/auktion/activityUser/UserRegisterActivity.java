package com.example.auktion.activityUser;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.auktion.LoginActivity;
import com.example.auktion.R;
import com.example.auktion.activityAdmin.AdminRegisterActivity;
import com.example.auktion.activityUser.presenter.UserRegisterPresenter;
import com.example.auktion.activityUser.view.IUserRegisterView;
import com.example.auktion.data.UserDAO;
import com.example.auktion.model.User;
import com.example.auktion.utils.AppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.textfield.TextInputLayout;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;

public class UserRegisterActivity extends AppCompatActivity implements IUserRegisterView {

    private TextInputLayout edtUsername, edtPassword, edtNamaLengkap, edtTelepon;
    private TextView tvGoToLogin,tvGoToRegisterAdmin;
    private Button btnRegister;
    private UserDAO userDAO;

    private User user;

    private UserRegisterPresenter userRegisterPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        initId();
        inisialisasiStetho();
        inisialisasiDBRoom();

        userRegisterPresenter = new UserRegisterPresenter(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserData();
            }
        });

        tvGoToRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogForm();
            }
        });

        tvGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRegisterSuccess(String message) {
        Toasty.success(this, message, Toasty.LENGTH_SHORT).show();
        userDAO.insertUser(user);
        Intent intent = new Intent(UserRegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRegisterError(String message) {
        Toasty.error(this, message, Toasty.LENGTH_SHORT).show();
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

    private void dialogForm() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(UserRegisterActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_admin_register, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        final TextInputLayout edtAdminPassword = dialogView.findViewById(R.id.edt_passwordAdmin_registerUser);

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (edtAdminPassword.getEditText().getText().toString().trim().equals("admin123")) {
                    Intent intent = new Intent(UserRegisterActivity.this,
                            AdminRegisterActivity.class);
                    dialog.dismiss();
                    startActivity(intent);
                } else {
                    Toasty.error(UserRegisterActivity.this, "Password Salah",
                            Toasty.LENGTH_SHORT).show();
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

    private void getUserData() {
        user = new User(edtNamaLengkap.getEditText().getText().toString(),
                edtUsername.getEditText().getText().toString().trim(),
                edtPassword.getEditText().getText().toString(),
                edtTelepon.getEditText().getText().toString());

        userRegisterPresenter.onRegister(user);
    }

    private void initId() {
        edtUsername = findViewById(R.id.edt_username_registerUser);
        edtPassword = findViewById(R.id.edt_password_registerUser);
        edtNamaLengkap = findViewById(R.id.edt_namaLengkap_registerUser);
        edtTelepon = findViewById(R.id.edt_telepon_registerUser);
        tvGoToLogin = findViewById(R.id.tv_goToLogin_registerUser);
        tvGoToRegisterAdmin = findViewById(R.id.tv_goToRegister_registerUser);
        btnRegister = findViewById(R.id.btnRegister_user);

    }
}
