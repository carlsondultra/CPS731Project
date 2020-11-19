package com.example.project731;

import android.os.Bundle;
import androidx.room.Database;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseMatchActivity extends AppCompatActivity {

    private Button beat, heat;
    private TextView email,name;
    private ImageView grail;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_matching);

        beat = findViewById(R.id.beat_button);
        heat = findViewById(R.id.heat_button);
        email = findViewById(R.id.profile_name3);
        name = findViewById(R.id.realName);
        auth = FirebaseAuth.getInstance();
        email.setText(FirebaseLoginActivity.user);
        email.setFocusable(false);



    }
}

