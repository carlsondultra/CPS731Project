package com.example.project731;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.Object;


public class FirebaseRegisterActivity extends AppCompatActivity {

    public static String sex,txt_email,txt_password,txt_password_confirm,name2;
    private EditText email,name1;
    private EditText password,passwordConfirm;
    private Button register,back;
    private RadioGroup radioGroup;
    RadioButton radioButton;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

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
        back = findViewById(R.id.back_button2);
        name1 = findViewById(R.id.name);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        auth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirebaseRegisterActivity.this, FirebaseLoginScreenActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectId = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectId);
                if(radioButton !=null)
                    sex = radioButton.getText().toString();
                txt_email = email.getText().toString();
                txt_password = password.getText().toString();
                txt_password_confirm = passwordConfirm.getText().toString();
                name2 = name1.getText().toString();


                registerT();
            }
        });

    }


    public boolean registerT(){

        if (txt_email.equals("") || txt_password.equals("")){
            Toast.makeText(FirebaseRegisterActivity.this, "Please fill in the empty fields.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (txt_password.length() < 6){
            Toast.makeText(FirebaseRegisterActivity.this, "Password is too short.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!txt_password.equals(txt_password_confirm)){
            Toast.makeText(FirebaseRegisterActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(name2.equals("")){
            Toast.makeText(FirebaseRegisterActivity.this, "Enter a name please.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(radioButton == null){
            Toast.makeText(FirebaseRegisterActivity.this, "Select male or female.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            registerUser(txt_email, txt_password, name2, sex);
            return true;
        }
    }
    public boolean registerNoToast(){

        if (txt_email.equals("") || txt_password.equals("")){
            return false;
        }
        else if (txt_password.length() < 6){
            return false;
        }
        else if(!txt_password.equals(txt_password_confirm)){
            return false;
        }else if(name2.equals("")){
            return false;
        }else if(radioButton == null){
            return false;
        }
        else{
            return true;
        }
    }
    public void registerUser(String email, String password, final String name, final String sex) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(FirebaseRegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(FirebaseRegisterActivity.this, "Successful register. Please login with your email and password.", Toast.LENGTH_LONG).show();
                    String userId = auth.getCurrentUser().getUid();
                    DatabaseReference currentUDb = FirebaseDatabase.getInstance().getReference().child("Users").child(sex).child(userId).child("name");
                    currentUDb.setValue(name);
                    startActivity(new Intent(FirebaseRegisterActivity.this, FirebaseLoginActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(FirebaseRegisterActivity.this, "Unsuccessful register. Email already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}