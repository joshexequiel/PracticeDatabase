package com.arcaya.a1115_practicedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id, fname, lname, section;
    DBHelper helper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);

        id = findViewById(R.id.pvID);
        fname = findViewById(R.id.pvFname);
        lname = findViewById(R.id.pvLname);
        section = findViewById(R.id.pvSection);
        cursor = helper.getRecords();
        cursor.moveToFirst();
    }

    public void addRecord(View view) {
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String sec = section.getText().toString();

        long isInserted = helper.insert(firstname, lastname, sec);

        if (isInserted == 1) {
            Toast.makeText(this, "not inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "record inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void moveFirst(View view) {
            cursor.moveToFirst();
            displayData();
    }

    public void movePrevious(View v) {
        if (cursor.isFirst()) {
            cursor.moveToFirst();
            Toast.makeText(this, "This is the first record.", Toast.LENGTH_LONG).show();
        }
        else {
            cursor.moveToPrevious();
        }
        displayData();
    }

    public void moveLast(View v) {
        cursor.moveToLast();
        displayData();
    }

    public void moveNext(View v) {
        if (cursor.isLast()) {
            cursor.moveToLast();
            Toast.makeText(this, "This is the last record.", Toast.LENGTH_LONG).show();
        }
        else {
            cursor.moveToNext();
        }
        displayData();
    }

    private void displayData() {
        id.setText(cursor.getString(0));
        fname.setText(cursor.getString(1));
        lname.setText(cursor.getString(2));
        section.setText(cursor.getString(3));
    }

    public void editRecord(View v) {
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String sec = section.getText().toString();
        String id = cursor.getString(0);

        int numUpdated = helper.updateRecords(id, firstname, lastname, sec);

        if (numUpdated == 1) {
            Toast.makeText(this, "Record updated.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Record update failed.", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteRecord(View v) {
        String id = cursor.getString(0);
        int numDeleted = helper.deleteRecord(id);

        if (numDeleted == 1) {
            Toast.makeText(this, "Record deleted.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Record not deleted.", Toast.LENGTH_LONG).show();
        }
    }
}
