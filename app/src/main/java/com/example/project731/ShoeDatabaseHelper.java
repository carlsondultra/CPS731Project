package com.example.project731;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ShoeDatabaseHelper extends SQLiteOpenHelper {

    public static final String SHOE_TABLE = "SHOE_TABLE";
    public static final String COLUMN_SHOE_PIC_TEMP = "SHOE_PIC_TEMP";
    public static final String COLUMN_SHOENAME = "SHOENAME";
    public ShoeDatabaseHelper(@Nullable Context context) {

        super(context,"shoebaseTest.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + SHOE_TABLE + " ( " + COLUMN_SHOENAME + " TEXT PRIMARY KEY, " + COLUMN_SHOE_PIC_TEMP + " TEXT ) " ;

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne(ShoeProfileForLists userModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SHOENAME, userModel.getShoeName());
        cv.put(COLUMN_SHOE_PIC_TEMP, userModel.getShoePic());

        long insert = db.insert(SHOE_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }

    }
    public boolean addTo(ShoeProfileForLists userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();



        return true;
    }

    public List<ShoeProfileForLists> getEveryone(){
        List<ShoeProfileForLists> returnlist = new ArrayList<>();

        String queString = "SELECT * FROM " + SHOE_TABLE;
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
