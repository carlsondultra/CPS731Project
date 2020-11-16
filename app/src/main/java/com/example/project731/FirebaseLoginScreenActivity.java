package com.example.project731;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirebaseLoginScreenActivity extends AppCompatActivity {

    private Button register;
    private Button login;
    private Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login_screen);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        menu = findViewById(R.id.menu);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirebaseLoginScreenActivity.this, FirebaseRegisterActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirebaseLoginScreenActivity.this, FirebaseLoginActivity.class));
                finish();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirebaseLoginScreenActivity.this, FirebaseMenuActivity.class));
                finish();
            }
        });

    }
}