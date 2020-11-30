package com.example.project731;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class FirebaseMainActivity extends AppCompatActivity {

    private Button logout, createShoeList, viewShoeList, viewProfile,add_grail,match;
    private EditText edit;
    private Button add;
    boolean addShoes,chooseGrail,nameSet;
    UserProfileDatabaseHelper uPHelper;
    UserProfile uprofile;
    ShoeDatabaseHelper sHelper;
    ProfileListAdapter profile_listAdapt;
    ShoeListAdapter shoe_listAdapt;
    ListView shoe_list;
    TextView select_user;
    DatabaseReference currentUDb;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private String userSex;
    private String oppositeSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_main);

        edit = findViewById(R.id.edit);
        add = findViewById(R.id.add);
        auth = FirebaseAuth.getInstance();

        logout = findViewById(R.id.logout);
        select_user =(TextView) findViewById(R.id.profile_name2);
        shoe_list =(ListView) findViewById(R.id.shoe_list);
        createShoeList = findViewById(R.id.shoeListCreate);
        viewShoeList = findViewById(R.id.add_list2);
        viewProfile = findViewById(R.id.user_profile2);
        select_user.setText(FirebaseLoginActivity.user);
        select_user.setFocusable(false);
        add_grail = findViewById(R.id.grail_button);
        match = findViewById(R.id.match);
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
                    nameSet = true;
                    checkUser(txt_name);
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
        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = auth.getCurrentUser().getUid();
                DatabaseReference currentUDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("grail");
                if(currentUDb.equals(null)){
                    Toast.makeText(FirebaseMainActivity.this, "Please select a grail.", Toast.LENGTH_SHORT).show();
                }else
                startActivity(new Intent(FirebaseMainActivity.this, FirebaseMatchActivity.class));
            }
        });
        add_grail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseGrail = true;
                addShoes = false;
                sHelper = new ShoeDatabaseHelper(FirebaseMainActivity.this);
                List<ShoeProfileForLists> everyone = sHelper.getEveryone();

                shoe_listAdapt = new ShoeListAdapter(FirebaseMainActivity.this, R.layout.adapter_view_layout, everyone);
                shoe_list.setAdapter(shoe_listAdapt);
            }
        });
        viewShoeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseGrail = false;
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
                chooseGrail = false;
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
                    if(b)
                    Toast.makeText(FirebaseMainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(FirebaseMainActivity.this, "Shoe is in your list already", Toast.LENGTH_SHORT).show();
                }else if(chooseGrail){
                    ShoeProfileForLists shoe = (ShoeProfileForLists) parent.getItemAtPosition(position);
                    checkUser(shoe.getShoeImage());
                }
                else{
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
    public void checkUser(final String shoe){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference maledb = FirebaseDatabase.getInstance().getReference().child("Users").child("Male");
        maledb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals(user.getUid())){
                    userSex = "Male";
                    oppositeSex = "Female";
                    if(nameSet) {
                        setName(shoe);
                    }
                    else {
                        setGrail(shoe);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference femaledb = FirebaseDatabase.getInstance().getReference().child("Users").child("Female");
        femaledb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals(user.getUid())){
                    userSex = "Female";
                    oppositeSex = "Male";
                    if(nameSet) {
                        setName(shoe);
                    }
                    else {
                        setGrail(shoe);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void setGrail(final String shoe){
        DatabaseReference oppositeSexdb = FirebaseDatabase.getInstance().getReference().child("Users").child(oppositeSex);
        oppositeSexdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()){
                    String userId = auth.getCurrentUser().getUid();
                    currentUDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userSex).child(userId).child("grail");
                    currentUDb.setValue(shoe);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void setName(final String shoe){
        DatabaseReference oppositeSexdb = FirebaseDatabase.getInstance().getReference().child("Users").child(oppositeSex);
        oppositeSexdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()){
                    String userId = auth.getCurrentUser().getUid();
                    currentUDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userSex).child(userId).child("name");
                    currentUDb.setValue(shoe);
                    nameSet=false;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}