package com.example.project731;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseRegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password,passwordConfirm;
    private Button register;

    private FirebaseAuth auth;

    public FirebaseRegisterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_register);

        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_first);
        passwordConfirm = findViewById(R.id.password_confirm_box);
        register = findViewById(R.id.register);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_password_confirm = passwordConfirm.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(FirebaseRegisterActivity.this, "Please fill in the empty fields.", Toast.LENGTH_SHORT).show();
                }
                else if (txt_password.length() < 6){
                    Toast.makeText(FirebaseRegisterActivity.this, "Password is too short.", Toast.LENGTH_SHORT).show();
                }
                else if(!txt_password.equals(txt_password_confirm)){
                    Toast.makeText(FirebaseRegisterActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser(txt_email, txt_password);
                }
            }
        });
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(FirebaseRegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(FirebaseRegisterActivity.this, "Successful register.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FirebaseRegisterActivity.this, FirebaseMainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(FirebaseRegisterActivity.this, "Unsuccessful register. Email already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}