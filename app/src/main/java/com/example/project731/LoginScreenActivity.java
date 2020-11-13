package com.example.project731;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity {

    //control references
    Button create_account, login_button, shoe_create;
    EditText username_enter, password_enter;
    public static String user;
    UserDatabaseHelper uHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        create_account = findViewById(R.id.create_account);
        username_enter = findViewById(R.id.username_enter);
        password_enter = findViewById(R.id.password_enter);
        login_button = findViewById(R.id.login_button);
        shoe_create = findViewById(R.id.shoe_create);



        //button listener
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                ProfileCreation newUser;
                try {

                    newUser = new ProfileCreation( username_enter.getText().toString(), password_enter.getText().toString());
                    Toast.makeText(LoginScreenActivity.this, newUser.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(LoginScreenActivity.this, "Error creating new account", Toast.LENGTH_SHORT).show();
                    newUser = new ProfileCreation( "error", "error");
                }
                UserDatabaseHelper dbHelper = new UserDatabaseHelper(LoginScreenActivity.this);

                boolean success = dbHelper.addOne(newUser);


                Toast.makeText(LoginScreenActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uHelper = new UserDatabaseHelper(LoginScreenActivity.this);
                ProfileCreation newUser;
                newUser = new ProfileCreation( username_enter.getText().toString(), password_enter.getText().toString());

                boolean temp = uHelper.isMatching(username_enter.getText().toString(), password_enter.getText().toString());
                if (temp){
                    user = username_enter.getText().toString();
                    startActivity(new Intent(LoginScreenActivity.this,ShowingProfile.class));
                }else{
                    Toast.makeText(LoginScreenActivity.this, "Error. Password or Username do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        shoe_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoeProfileForLists shoe;
                ShoeDatabaseHelper dbShoeHelper = new ShoeDatabaseHelper(LoginScreenActivity.this);
                boolean success2;
                try{
                    for(int i = 0; i <11; i++){
                        shoe = new ShoeProfileForLists("Shoe "+i, i );
                        success2 = dbShoeHelper.addOne(shoe);
                    }
                }catch(Exception e){
                    Toast.makeText(LoginScreenActivity.this, "Error creating new list", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}