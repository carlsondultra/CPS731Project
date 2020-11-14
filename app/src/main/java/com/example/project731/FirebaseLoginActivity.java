package com.example.project731;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseLoginActivity extends AppCompatActivity {

    public static String user;
    private EditText email;
    private EditText password;
    private Button login;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);

        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_confirm_box);
        login = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                try {
                    loginUser(txt_email, txt_password);
                    user=txt_email;
                }catch(Exception e){
                    Toast.makeText(FirebaseLoginActivity.this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(FirebaseLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FirebaseLoginActivity.this, FirebaseMainActivity.class));
                finish();
            }
        });
        auth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FirebaseLoginActivity.this, "Email or password is incorrect.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}