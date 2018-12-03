package com.electronic.lapsus.diplomadopucmm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "students_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Student.CREATE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void deleteAllStudent(){

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }


    public long insertStudent(int imgID,
                              String name,
                              String gender,
                              LocalDateTime birthdate,
                              String career,
                              String address,
                              Boolean assistance) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Student.COLUMN_IMGID, imgID);
        values.put(Student.COLUMN_NAME, name);
        values.put(Student.COLUMN_GENDER, gender);
        values.put(Student.COLUMN_BIRTHDAY, birthdate.toString());
        values.put(Student.COLUMN_CAREER, career);
        values.put(Student.COLUMN_ADDRESS, address);
        values.put(Student.COLUMN_ASSISTANCE, assistance);

        // insert row
        long id = db.insert(Student.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Student.TABLE_NAME + " ORDER BY " +
                Student.COLUMN_BIRTHDAY + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setImgID(cursor.getInt(cursor.getColumnIndex(Student.COLUMN_IMGID)));
                student.setName(cursor.getString(cursor.getColumnIndex(Student.COLUMN_NAME)));
                student.setGender(cursor.getString(cursor.getColumnIndex(Student.COLUMN_GENDER)));
                try {
                    long date = new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(cursor.getColumnIndex(Student.COLUMN_BIRTHDAY))).getTime();
                    student.setBirthday(millsToLocalDateTime(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                student.setCareer(cursor.getString(cursor.getColumnIndex(Student.COLUMN_CAREER)));
                student.setAddress(cursor.getString(cursor.getColumnIndex(Student.COLUMN_ADDRESS)));
                student.setAssistance(cursor.getString(cursor.getColumnIndex(Student.COLUMN_ASSISTANCE)) == "true" ? true : false);

                students.add(student);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return students;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime millsToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }

}
