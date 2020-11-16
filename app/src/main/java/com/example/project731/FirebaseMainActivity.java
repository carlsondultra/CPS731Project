package com.example.project731;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
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
    ProfileListAdapter profile_listAdapt;
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
                PopupMenu popup = new PopupMenu(FirebaseMainActivity.this, logout);
                popup.getMenuInflater().inflate(R.menu.popup_menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(FirebaseMainActivity.this, " " + item.getTooltipText(), Toast.LENGTH_SHORT).show();
                        switch(item.getItemId()){
                            case R.id.logout:
                                FirebaseAuth.getInstance().signOut();
                                Toast.makeText(FirebaseMainActivity.this,"You have logged out.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(FirebaseMainActivity.this, FirebaseLoginScreenActivity.class));
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();
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
                        switch(i){
                            case 0:
                                shoe = new ShoeProfileForLists("Off-White Jordan 1's Chicago",  "drawable://" + R.drawable.offwhitechicagos );
                                sHelper.addOne(shoe);
                                break;
                            case 1:
                                shoe = new ShoeProfileForLists("Air Max 97/1 Sean Wotherspoon",  "drawable://" + R.drawable.sean );
                                sHelper.addOne(shoe);
                                break;
                            case 2:
                                shoe = new ShoeProfileForLists("Travis Scott Jordan 1's",  "drawable://" + R.drawable.travis );
                                sHelper.addOne(shoe);
                                break;
                            case 3:
                                shoe = new ShoeProfileForLists("Air Yeezy Red Octobers",  "drawable://" + R.drawable.redoctober );
                                sHelper.addOne(shoe);
                                break;
                            case 4:
                                shoe = new ShoeProfileForLists("Air Jordan 1's Banned",  "drawable://" + R.drawable.banned );
                                sHelper.addOne(shoe);
                                break;
                            case 5:
                                shoe = new ShoeProfileForLists("Air Jordan 1's Court Purple 2.0",  "drawable://" + R.drawable.courtpurple );
                                sHelper.addOne(shoe);
                                break;
                            case 6:
                                shoe = new ShoeProfileForLists("Air Jordan 1's Obsidean",  "drawable://" + R.drawable.obsidean );
                                sHelper.addOne(shoe);
                                break;
                            case 7:
                                shoe = new ShoeProfileForLists("Air Jordan 1's Shatter Back Board 1.0",  "drawable://" + R.drawable.sbb1 );
                                sHelper.addOne(shoe);
                                break;
                            case 8:
                                shoe = new ShoeProfileForLists("Air Jordan 1's Bred Toe",  "drawable://" + R.drawable.bredtoe );
                                sHelper.addOne(shoe);
                                break;
                            case 9:
                                shoe = new ShoeProfileForLists("Air Force 1 Triple White",  "drawable://" + R.drawable.airforce1 );
                                sHelper.addOne(shoe);
                                break;
                        }
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

                profile_listAdapt = new ProfileListAdapter(FirebaseMainActivity.this, R.layout.adapter_view_layout, everyone2);
                shoe_list.setAdapter(profile_listAdapt);
            }
        });



        shoe_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(addShoes) {
                    ShoeProfileForLists shoe = (ShoeProfileForLists) parent.getItemAtPosition(position);
                    uPHelper = new UserProfileDatabaseHelper(FirebaseMainActivity.this);
                    uprofile = new UserProfile(-1,FirebaseLoginActivity.user, shoe);
                    boolean b = uPHelper.addOne(uprofile);
                    Toast.makeText(FirebaseMainActivity.this, "success", Toast.LENGTH_SHORT).show();
                }else{
                    UserProfile user = (UserProfile) parent.getItemAtPosition(position);
                    ShoeProfileForLists shoe = new ShoeProfileForLists(user.getShoeName(),user.getShoeImage());
                    uPHelper = new UserProfileDatabaseHelper(FirebaseMainActivity.this);
                    uprofile = new UserProfile(user.getId(),FirebaseLoginActivity.user, shoe);
                    Toast.makeText(FirebaseMainActivity.this, " "+uprofile.getId()+"\n"+user.getId()+" "+user.getShoeName(), Toast.LENGTH_SHORT).show();

                    boolean b = uPHelper.deleteOne(uprofile);

                    List<UserProfile> everyone2 = uPHelper.getEveryone(FirebaseLoginActivity.user);

                    profile_listAdapt = new ProfileListAdapter(FirebaseMainActivity.this, R.layout.adapter_view_layout, everyone2);
                    shoe_list.setAdapter(profile_listAdapt);
                    Toast.makeText(FirebaseMainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();



                }
            }
        });

    }
}