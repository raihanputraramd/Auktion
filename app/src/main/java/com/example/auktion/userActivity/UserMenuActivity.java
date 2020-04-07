package com.example.auktion.userActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.auktion.R;

public class UserMenuActivity extends AppCompatActivity {

    ImageButton ibProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        ibProfile = findViewById(R.id.ib_user_profile);

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMenuActivity.this,
                        UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
