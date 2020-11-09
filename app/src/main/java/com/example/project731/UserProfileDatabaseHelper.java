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
    public static final String COLUMN_USERPROFILE_SHOE = "USERPROFILE_SHOE";
    public static final String COLUMN_USERPROFILE = "USERPROFILE";
    public UserProfileDatabaseHelper(@Nullable Context context) {

        super(context,"userProfileTest.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        String createTableStatement = "CREATE TABLE " + USERPROFILE_TABLE + " ( " + COLUMN_USERPROFILE + " ProfileCreation , " + COLUMN_USERPROFILE_SHOE + " ShoeProfileForLists UNIQUE ) " ;

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne(String username, ShoeProfileForLists shoe){
        //add to the list
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERPROFILE, username);
        cv.put(COLUMN_USERPROFILE_SHOE, shoe.getShoeName());

        long insert = db.insert(USERPROFILE_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }

    }

    public boolean deleteOne(String user, ShoeProfileForLists shoe){
        //delete off a list from view

        SQLiteDatabase db = this.getWritableDatabase();
        String queString = "DELETE FROM " + USERPROFILE_TABLE + " WHERE " + COLUMN_USERPROFILE_SHOE + " = " + shoe;

        Cursor cursor = db.rawQuery(queString, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }
    public List<ShoeProfileForLists> getEveryone(){
        //returnes a list to view
        List<ShoeProfileForLists> returnlist = new ArrayList<>();

        String queString = "SELECT * FROM " + USERPROFILE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queString, null);
        if(cursor.moveToFirst()){
            //loop throug hto create a new shoe result and put them in the lsit
            do{
                String shoeID = cursor.getString(0);
                int shoePic = cursor.getInt(1);

                ShoeProfileForLists newShoe = new ShoeProfileForLists(shoeID, shoePic);
                returnlist.add(newShoe);
            }while(cursor.moveToNext());
        }else{
            //no add
        }
        cursor.close();
        db.close();
        return returnlist;
    }
}
