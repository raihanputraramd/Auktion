package com.example.auktion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.auktion.presenter.ILoginPresenter;
import com.example.auktion.presenter.LoginPresenter;
import com.example.auktion.userActivity.UserMenuActivity;
import com.example.auktion.view.ILoginView;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    TextInputLayout edtUsername, edtPassword;
    Button btnLogin;

    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btnLogin);

        loginPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, UserMenuActivity.class);
                startActivity(intent);
            }
        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginPresenter.onLogin(edtUsername.getText().toString(),
//                        edtPassword.getText().toString());
//            }
//        });
    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, "Mohon isi dengan benar data nya",
                Toast.LENGTH_SHORT).show();
    }

}
