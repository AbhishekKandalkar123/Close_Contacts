package com.akan.closecontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "Register.db";
    private static final String DB_ID = "id";
    private static final String TABLE_NAME1 = "registerTB";
    private static final String TABLE_NAME2 = "ContactsTB";
    private static final String Name = "name";
    private static final String Phone_No = "phoneNo";
    private static final String Pass_Word = "password";

    private static final String Name2 = "name";
    private static final String Phone_No2 = "phoneNo";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_NAME1+ "("
                 + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Name + " TEXT,"
                + Pass_Word + " TEXT,"
                + Phone_No + " TEXT)";
        String query2 = "CREATE TABLE " + TABLE_NAME2 + "("
                + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Name2 + " TEXT,"
                + Phone_No2 + " TEXT)";

        db.execSQL(query1);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }

    public Boolean insertData(String UserName, String Password, String PhoneNumber) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Name, UserName);
            values.put(Pass_Word, Password);
            values.put(Phone_No, PhoneNumber);
            long result = db.insert(TABLE_NAME1, null, values);
            if(result == -1) {           // if failed to insert values returns -1
                return false;
            }
            else {
                return true;
            }
    }

    public Boolean checkUserName(String UserName) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor c = mydb.rawQuery("SELECT * FROM " + TABLE_NAME1 +" WHERE "+Name+" = ?", new String[] {UserName});
        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean checkUserNamePass(String UserName, String Password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor c = mydb.rawQuery("SELECT * FROM "+ TABLE_NAME1 +" WHERE "+Name+" = ? AND "+Pass_Word+" = ?", new String[]{UserName, Password});
        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<LoginModal>readContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorContacts = db.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        ArrayList<LoginModal> contactModalArraylist = new ArrayList<>();

        if(cursorContacts.moveToFirst()){
            do{
                contactModalArraylist.add(new LoginModal(cursorContacts.getString(1),
                                                        cursorContacts.getString(2)));

            }while(cursorContacts.moveToNext());
        }
        cursorContacts.close();
        return contactModalArraylist;
    }

    public Boolean addNewContact(String ContactName, String ContactNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Name2, ContactName);
        values.put(Phone_No2, ContactNumber);

        long result = db.insert(TABLE_NAME2, null, values);
        if (result == -1) {           // if failed to insert values returns -1
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean checkPhoneNo(String PhoneNo) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor c = mydb.rawQuery("SELECT * FROM " + TABLE_NAME2 +" WHERE "+Phone_No2+" = ?", new String[] {PhoneNo});
        if (c.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public void updateContacts(String NumberOrg,String Number,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Name2,name);
        values.put(Phone_No2, Number);

        db.update(TABLE_NAME2, values,"phoneNo=?", new String[]{NumberOrg});
        db.close();
    }

    public void deleteContacts(String PhoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, "phoneNo=?", new String[]{PhoneNumber});
        db.close();
    }

    public boolean isDBEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean chk = true;
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
        if (mCursor.getCount()>0){
            chk = false;
        }
        return chk;
    }
}

