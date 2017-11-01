package com.droppledev.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayasfn on 10/13/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    // buat inisiasi database dan tabel
    private static final int DB_VERSION=2;
    private static final String DB_NAME= "Tutorial3";
    private static final String TABLE_BIODATA = "Biodata";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOCATION = "location";

    public DBHandler(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //buat tabel baru
        String CREATE_TABLE = "CREATE TABLE " + TABLE_BIODATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_LOCATION + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // DROP TABLE untuk hapus tabel
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BIODATA);
        onCreate(sqLiteDatabase);
    }

    public void addBiodata(Biodata biodata){ //auto increment KEY_ID Primary Key Integer
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,biodata.getName());
        values.put(KEY_LOCATION,biodata.getLocation());
        db.insert(TABLE_BIODATA,null,values);
        db.close();

    }

    public ArrayList<Biodata> getAllBiodata() {
        ArrayList<Biodata> biodataList = new ArrayList<Biodata>();
        String selectQuery = "SELECT * FROM " + TABLE_BIODATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) { // agar cursor ke tempat yg paling pertama (kiri /0 )
            do{
                Biodata biodata = new Biodata();
                biodata.setId(Integer.parseInt(cursor.getString(0)));
                biodata.setName(cursor.getString(1));
                biodata.setLocation(cursor.getString(2));

                biodataList.add(biodata);
            }while(cursor.moveToNext());
        }
        return biodataList;
    }

    public int updateBiodata(Biodata biodata){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, biodata.getName());
        values.put(KEY_LOCATION, biodata.getLocation());

        return db.update(TABLE_BIODATA,values, KEY_ID + " = ?",
                new String[] {String.valueOf(biodata.getId())});
    }

    public void deleteBiodata (Biodata biodata){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BIODATA, KEY_ID + " = ?",
                new String[] {String.valueOf(biodata.getId())});
        db.close();
    }


}
