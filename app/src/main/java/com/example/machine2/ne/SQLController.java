package com.example.machine2.ne;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class  SQLController
{

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "cityList";
    private static final String TABLE_CONTACTS = "city";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String CITY_ID = "_cityid";


    private DBhelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    ArrayList<String> arrayList = new ArrayList<String>();




    public class DBhelper extends SQLiteOpenHelper
    {

        public DBhelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
           // String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, " + CITY_ID + " TEXT)";
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"  + CITY_ID + " TEXT)";
            db.execSQL(CREATE_CONTACTS_TABLE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            onCreate(db);
        }
    }
    public void insert(String name,String cityid) {
        ContentValues values = new ContentValues();
        //values.put(KEY_NAME, name);
        values.put(CITY_ID, cityid);// City Name
        System.out.println("values" + values);
        database.insert(TABLE_CONTACTS, null, values);
    }
    public String fetch() {
        String[] columns = new String[] {
               // KEY_ID,KEY_NAME,CITY_ID
                KEY_ID,CITY_ID
        };
        Cursor cursor = database.query(TABLE_CONTACTS, columns, null, null, null, null, null);
        String result ="";
       // int iName = cursor.getColumnIndex(KEY_NAME);
        int cId = cursor.getColumnIndex(CITY_ID);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
//            result = cursor.getString(iName);//+" "+cursor.getString(cId);
//            arrayList.add(result)
 result =result +","+ cursor.getString(cId);
           //arrayList.add(result);
        }
        return result;
    }

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLController open() throws SQLException
    {
        dbHelper = new DBhelper(ourcontext);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

}