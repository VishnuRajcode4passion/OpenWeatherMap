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


    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "cityList";
    private static final String TABLE_CONTACTS = "city";

    private static final String KEY_ID = "id";

    private static final String CITY_ID = "cityId";


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

            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," +CITY_ID + " TEXT)";


            db.execSQL(CREATE_CONTACTS_TABLE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            onCreate(db);
        }
    }

        public void insert(String id) {
            ContentValues values = new ContentValues();
            values.put(CITY_ID, id); // City Name
            System.out.println("ID IS " + id);
            database.insert(TABLE_CONTACTS, null, values);
        }
        public String fetch() {
            String[] columns = new String[] {
                    KEY_ID,CITY_ID
            };
            Cursor cursor = database.query(TABLE_CONTACTS, columns, null, null, null, null, null);
           String result ="";
            int iName = cursor.getColumnIndex(CITY_ID);
           for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
           {
              result = result+","+cursor.getString(iName);
               System.out.println("RESULT "+result);
              // arrayList.add(result);
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