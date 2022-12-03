package com.example.dbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "EARTH";
    public static int DB_VERSION = 1;
    public static String TABLE_NAME = "Student";

    public static String ID_COL = "id";
    public static String NAME_COL = "name";
    public static String NIC_COL = "nic";
    public static String MOBILE_COL = "mobile";
    public static String EMAIL_COL = "email";



    public DBHelper(Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE "+TABLE_NAME+" ("
                        + ID_COL+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NAME_COL + " TEXT, "
                        + NIC_COL + " VARCHAR, "
                        + MOBILE_COL + " INTEGER, "
                        + EMAIL_COL + " TEXT )";


        sqLiteDatabase.execSQL(query);

        //String query = "CREATE TABLE Student(id int primary key,name text,nic varchar(10),mobile int,email text);"

    }

    public  void addNewStudent(String name,String nic,String mobile,String email){

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues container = new ContentValues();
            container.put(NAME_COL,name);
            container.put(NIC_COL,nic);
            container.put(MOBILE_COL,mobile);
            container.put(EMAIL_COL,email);

            db.insert(TABLE_NAME,null,container);

            db.close();

    }


    public ArrayList<Student> getAllStudents(){

        ArrayList<Student> studentList = new ArrayList<Student>();

        String selectQuery ="SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Student student = new Student();

                student.setName((cursor.getString(1)));
                student.setNic((cursor.getString(2)));
                student.setMobile((cursor.getInt(3)));
                student.setEmail((cursor.getString(4)));

                studentList.add(student);
            }while (cursor.moveToNext());
        }

        return  studentList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(sqLiteDatabase);
    }
}
