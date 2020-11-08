package com.example.project731;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.crypto.ShortBufferException;

public class ShowingProfile extends AppCompatActivity {
    //references
    Button add_shoe;
    EditText select_user;
    RecyclerView shoe_list;
    ArrayAdapter shoe_listAdapt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_list);

        add_shoe = findViewById(R.id.add_list);
        select_user = findViewById(R.id.profile_name);
        shoe_list = findViewById(R.id.shoe_list);

        add_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoeDatabaseHelper sHelper = new ShoeDatabaseHelper(ShowingProfile.this);
                List<ShoeProfileForLists> everyone = sHelper.getEveryone();

                shoe_listAdapt = new ArrayAdapter<ShoeProfileForLists>(ShowingProfile.this, android.R.layout.simple_expandable_list_item_1, everyone);

            }
        });
       }
}
