package com.example.machine2.ne;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Main activity class
public class MainActivity extends AppCompatActivity
{
    ListView listView;
    ArrayList<String> filelist = new ArrayList<String>();
    Bundle bundle;
    String name;
    ArrayAdapter<String> arrayAdapter;
    String filename = "mySharedString";
    SharedPreferences someData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        someData = getSharedPreferences(filename,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listView);
        //exception has to be handled when the main activity is first launched
        try {
            // Getting the data from previous activity and showing in list view.
            Set<String> set = someData.getStringSet("yourKey", null);
            List<String> sample=new ArrayList<String>(set);
     //       filelist.add(dataReturned);
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,sample);
            listView.setAdapter(arrayAdapter);

        }

        catch (Exception e) {
        }

        try {
            bundle = getIntent().getExtras();
            name = bundle.getString("mylist");
            filelist.add(name);
            SharedPreferences.Editor  editor = someData.edit();
            Set<String> set = new HashSet<String>();
            set.addAll(filelist);
            editor.putStringSet("yourKey", set);
            editor.commit();
//                        arrayAdapter.add();
            arrayAdapter.notifyDataSetChanged();
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,filelist);
            listView.setAdapter(arrayAdapter);
        }
        catch (Exception e) {}

        //when clicking the particular item in listview ,pass the data to detailPage activity.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data=(String)parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this,SlidingMainActivity.class);
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

        int id = item.getItemId();
        if (id == R.id.add)
        {
            Intent i=new Intent(MainActivity.this,AddCity.class);
            startActivity(i);
        }
        return false;
    }

}
