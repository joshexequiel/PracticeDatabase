package com.arcaya.a1115_practicedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    String col2 = "fname";
    String col3 = "lname";
    String col4 = "section";
    String table = "stud";

    public DBHelper(@Nullable Context context) {
        super(context, "student.db", null, 1);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE stud(ID INTEGER PRIMARY KEY AUTOINCREMENT, FName VARCHAR(50), LName VARCHAR(50), Section VARCHAR(50))";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public long insert(String fn, String ln, String sec) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, fn);
        cv.put(col3, ln);
        cv.put(col4, sec);
        return db.insert(table, null, cv);
    }

    public Cursor getRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectall = "SELECT * FROM stud";
        return db.rawQuery(selectall, null);
    }

    public int updateRecords(String id, String fn, String ln, String sec) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, fn);
        cv.put(col3, ln);
        cv.put(col4, sec);
        return db.update(table, cv, "ID = ?", new String[]{id});
    }

    public int deleteRecord(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table, "ID = ?", new String[]{id});
    }

}
