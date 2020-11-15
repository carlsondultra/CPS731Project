package com.example.project731;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class FirebaseMainActivity extends AppCompatActivity {

    private Button logout, createShoeList, viewShoeList, viewProfile;
    private EditText edit;
    private Button add;
    boolean addShoes;
    UserProfileDatabaseHelper uPHelper;
    UserProfile uprofile;
    ShoeDatabaseHelper sHelper;
    ShoeListAdapter shoe_listAdapt;
    ListView shoe_list;
    TextView select_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_main);

        edit = findViewById(R.id.edit);
        add = findViewById(R.id.add);

        logout = findViewById(R.id.logout);
        select_user =(TextView) findViewById(R.id.profile_name2);
        shoe_list =(ListView) findViewById(R.id.shoe_list);
        createShoeList = findViewById(R.id.shoeListCreate);
        viewShoeList = findViewById(R.id.add_list2);
        viewProfile = findViewById(R.id.user_profile2);
        select_user.setText(FirebaseLoginActivity.user);
        select_user.setFocusable(false);
        //button clicks
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(FirebaseMainActivity.this,"You have logged out.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FirebaseMainActivity.this, FirebaseLoginScreenActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_name = edit.getText().toString();
                if(txt_name.isEmpty()){
                    Toast.makeText(FirebaseMainActivity.this, "No name has been entered.", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseDatabase.getInstance().getReference().child("Test").push().child("Name").setValue(txt_name);
                }
            }
        });





        createShoeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoeProfileForLists shoe;
                sHelper = new ShoeDatabaseHelper(FirebaseMainActivity.this);
                Toast.makeText(FirebaseMainActivity.this, "" + addShoes, Toast.LENGTH_SHORT).show();
                try{
                    for(int i = 0; i <11; i++){
                        shoe = new ShoeProfileForLists("Shoe "+i, i , "drawable://" + R.drawable.obsidean );
                        sHelper.addOne(shoe);
                    }
                }catch(Exception e){
                    Toast.makeText(FirebaseMainActivity.this, "Error creating new list", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewShoeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShoes = true;
                sHelper = new ShoeDatabaseHelper(FirebaseMainActivity.this);
                List<ShoeProfileForLists> everyone = sHelper.getEveryone();

                shoe_listAdapt = new ShoeListAdapter(FirebaseMainActivity.this, R.layout.adapter_view_layout, everyone);
                shoe_list.setAdapter(shoe_listAdapt);

            }
        });
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShoes = false;
                uPHelper = new UserProfileDatabaseHelper(FirebaseMainActivity.this);
                List<UserProfile> everyone2 = uPHelper.getEveryone(FirebaseLoginActivity.user);

                //shoe_listAdapt = new ShoeListAdapter(FirebaseMainActivity.this, R.layout.adapter_view_layout, everyone2);
                shoe_list.setAdapter(shoe_listAdapt);
            }
        });



        shoe_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(addShoes) {
                    ShoeProfileForLists shoe = (ShoeProfileForLists) parent.getItemAtPosition(position);
                    uPHelper = new UserProfileDatabaseHelper(FirebaseMainActivity.this);
                    uprofile = new UserProfile(FirebaseLoginActivity.user, shoe);
                    boolean b = uPHelper.addOne(uprofile);
                    Toast.makeText(FirebaseMainActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}