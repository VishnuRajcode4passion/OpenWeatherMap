package com.example.machine2.ne;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by machine2 on 09/04/16.
 */
public class Operations {
    public static final String RID = "id";
    public static final String RNAME = "name";
    private static final String RDATABASE = "operations";
    private static final String RTABLE = "tab";
    private static final int DATABASE_VERSION = 6;
    private DBhelper ourHelper;
    private Context ourContext;
    private SQLiteDatabase ourDatabase;
    String result;

    public long creatEntry(String name) {
//        System.out.println("name " +name);
        ContentValues cv = new ContentValues();
        cv.put(RNAME, name);

        return ourDatabase.insert(RTABLE, null, cv);
    }

    public ArrayList getdata() {
        String[] column = new String[]{RID, RNAME};

        Cursor c = ourDatabase.query(RTABLE, column, null, null, null, null, null);
        ArrayList<String> arrayList = new ArrayList<>();
        int iname = c.getColumnIndex(RNAME);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = c.getString(iname) + "\n";
            arrayList.add(result);
        }
            return arrayList;
        }

    private  static class DBhelper extends SQLiteOpenHelper {
        public DBhelper(Context context) {
            super(context,RDATABASE,null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + RTABLE + "(" + RID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RNAME + " TEXT NOT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + RTABLE);
            onCreate(db);

        }
    }
    public Operations(Context c)
    {
        ourContext=c;
    }
    public Operations open() throws SQLException
    {

        ourHelper=new DBhelper(ourContext);

        ourDatabase=ourHelper.getWritableDatabase();
        System.out.println("name ");
        return  this;
    }
    public void close()
    {
        ourHelper.close();
    }
}


