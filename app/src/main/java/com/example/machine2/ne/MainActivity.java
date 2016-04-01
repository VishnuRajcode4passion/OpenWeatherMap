package com.example.machine2.ne;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
//proper comments needed

public class MainActivity extends AppCompatActivity {
    EditText in;
    ArrayList<String> al=new ArrayList<String>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);
        try {
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
        {

        }

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            Intent i=new Intent(MainActivity.this,AddCity.class);
            startActivity(i);
        }

        return false;
    }



}
