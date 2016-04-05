package com.example.machine2.ne;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity
{
    ListView lv;


    ArrayList<String> al=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);


        try
        {
            ArrayList<String> filelist = new ArrayList<String>();

            Bundle b = getIntent().getExtras();
           String name = b.getString("mylist");
            System.out.println("name "+name);
            filelist.add(name);
            ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filelist);
            lv.setAdapter(a);
            System.out.println("filelist " + filelist);
        }

        catch (Exception e)
        { }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data=(String)parent.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, DetailPage.class);
                i.putExtra("data",data);
                startActivity(i);
            }
        });

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.add)
        {
            Intent i=new Intent(MainActivity.this,AddCity.class);
            startActivity(i);
        }

        return false;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL("http://openweathermap.org/img/w/10d.png");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
