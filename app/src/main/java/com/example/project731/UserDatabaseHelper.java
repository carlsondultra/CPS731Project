package com.example.project731;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USERNAME = "USERNAME";
    public UserDatabaseHelper(@Nullable Context context) {

        super(context,"userbaseTest.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_USERNAME + " TEXT PRIMARY KEY, " + COLUMN_USER_PASSWORD + " TEXT ) " ;

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean isMatching(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from USER_TABLE where USERNAME = ? and USER_PASSWORD = ?", new String[]{username,password});
        if(cursor.getCount() > 0 ){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
    }}


    public boolean addOne(ProfileCreation userModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, userModel.getUsername());
        cv.put(COLUMN_USER_PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }

    }
}
