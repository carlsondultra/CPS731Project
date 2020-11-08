package com.example.project731;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity {

    //control references
    Button create_account;
    EditText username_enter, password_enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        create_account = findViewById(R.id.create_account);
        username_enter = findViewById(R.id.username_enter);
        password_enter = findViewById(R.id.password_enter);

        //button listener
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {


                ProfileCreation newUser;
                try {

                    newUser = new ProfileCreation(-1, username_enter.getText().toString(), password_enter.getText().toString());
                    Toast.makeText(LoginScreenActivity.this, newUser.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(LoginScreenActivity.this, "Error creating new account", Toast.LENGTH_SHORT).show();
                    newUser = new ProfileCreation(-1, "error", "error");
                }
                UserDatabaseHelper dbHelper = new UserDatabaseHelper(LoginScreenActivity.this);

                boolean success = dbHelper.addOne(newUser);


                Toast.makeText(LoginScreenActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
            }
        });

    }
}