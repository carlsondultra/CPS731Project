package com.example.project731;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class FirebaseMenuActivity extends AppCompatActivity {

    private Button back;
    private Button btnAbout;
    private Button stores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_menu);

        back = findViewById(R.id.back);
        btnAbout = findViewById(R.id.btnAbout);
        stores = findViewById(R.id.stores);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirebaseMenuActivity.this, FirebaseLoginScreenActivity.class));
                finish();
            }
        });

        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(FirebaseMenuActivity.this, btnAbout);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(FirebaseMenuActivity.this, " " + item.getTooltipText(), Toast.LENGTH_SHORT).show();
                        switch(item.getItemId()){
                            case R.id.one:
                                //Code
                                return true;
                            case R.id.two:
                                //Code
                                return true;
                            case R.id.three:
                                //Code
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();
            }
        });

        stores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirebaseMenuActivity.this, MapsActivity.class));
                finish();
            }
        });


    }
}