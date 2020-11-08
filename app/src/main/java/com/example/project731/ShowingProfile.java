package com.example.project731;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ShowingProfile extends AppCompatActivity {
    //references
    Button add_shoe;
    EditText select_user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_list);

        add_shoe = findViewById(R.id.add_list);
        select_user = findViewById(R.id.profile_name);
    }
}
