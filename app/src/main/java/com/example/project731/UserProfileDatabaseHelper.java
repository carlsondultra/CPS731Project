package com.example.project731;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserProfileDatabaseHelper extends SQLiteOpenHelper {

    public static final String USERPROFILE_TABLE = "USERPROFILE_TABLE";
    public static final String COLUMN_USER_UNIQUE_ID = "USER_UID";
    public static final String COLUMN_USERPROFILE_SHOE = "USERPROFILE_SHOE";
    public static final String COLUMN_USERPROFILE_SHOEPIC = "USERPROFILE_SHOEPIC";
    public static final String COLUMN_USERPROFILE = "USERPROFILE";
    public UserProfileDatabaseHelper(@Nullable Context context) {

        super(context,"userProfileTest15.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        String createTableStatement = "CREATE TABLE " + USERPROFILE_TABLE + " ("+COLUMN_USER_UNIQUE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_USERPROFILE + " TEXT , " + COLUMN_USERPROFILE_SHOE + " TEXT , " + COLUMN_USERPROFILE_SHOEPIC + " TEXT )";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne(UserProfile user){
        //add to the list
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERPROFILE, user.getNewUser());
        cv.put(COLUMN_USERPROFILE_SHOE, user.getShoeName());
        cv.put(COLUMN_USERPROFILE_SHOEPIC, user.getShoeImage());

        long insert = db.insert(USERPROFILE_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }

    }


    public boolean deleteOne(UserProfile user){
        //delete off a list from view

        SQLiteDatabase db = this.getWritableDatabase();
        String queString = "DELETE FROM " + USERPROFILE_TABLE + " WHERE " + COLUMN_USER_UNIQUE_ID + " = " + user.getId();


        Cursor cursor = db.rawQuery(queString, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }
    public List<UserProfile> getEveryone(String user){
        //returnes a list to view
        List<UserProfile> returnlist = new ArrayList<>();

        String queString = "SELECT * FROM " + USERPROFILE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queString, null);
        if(cursor.moveToFirst()){
            //loop throug hto create a new shoe result and put them in the lsit
            do{
                int id = cursor.getInt(0);
                String email = cursor.getString(1);
                String shoeID = cursor.getString(2);
                String shoeImage = cursor.getString(3);

                if(email.equals(user)) {
                    ShoeProfileForLists newShoe = new ShoeProfileForLists(shoeID,shoeImage );
                    UserProfile newProfile = new UserProfile(id,email, newShoe);
                    returnlist.add(newProfile);
                }
            }while(cursor.moveToNext());
        }else{
            //no add
        }
        cursor.close();
        db.close();
        return returnlist;
    }
}
