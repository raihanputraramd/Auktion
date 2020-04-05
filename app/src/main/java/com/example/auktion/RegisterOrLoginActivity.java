package com.example.auktion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterOrLoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_or_login);

        Button gotoLogin = findViewById(R.id.btn_gotoLogin_loginOrRegister);
        Button gotoRegister = findViewById(R.id.btn_gotoRegister_loginOrRegister);

        gotoLogin.setOnClickListener(this);
        gotoRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gotoLogin_loginOrRegister:
                Intent iLogin = new Intent(RegisterOrLoginActivity.this, MainActivity.class);
                startActivity(iLogin);
                break;
            case R.id.btn_gotoRegister_loginOrRegister:
                Intent iRegister = new Intent(RegisterOrLoginActivity.this, UserRegisterActivity.class);
                startActivity(iRegister);
            default:
                break;

        }
    }
}
