package com.example.machine2.ne;

import android.content.Intent;
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
//Main activity class
public class MainActivity extends AppCompatActivity
{
    ListView listView;
    ArrayList<String> filelist = new ArrayList<String>();
    Bundle b;
    String name;
    ArrayAdapter<String> a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listView);
<<<<<<< HEAD

        //exception has to be handled when the main activity is first launched
        try {
            // Getting the data from previous activity and showing in list view.
            b = getIntent().getExtras();
            name = b.getString("mylist");
=======
      //fetch data from the Addcity
        try {
            Bundle bundle = getIntent().getExtras();
            String name = bundle.getString("mylist");
>>>>>>> 10e3e3d0bb63524a4ee42e416591a1e23f9ddf48
            filelist.add(name);
            a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filelist);
            listView.setAdapter(a);
        }

        catch (Exception e) { }
<<<<<<< HEAD

        //when clicking the particular item in listview ,pass the data to detailPage activity.
=======
        //Onclick of the listview
        //Display the details of the selected city
>>>>>>> 10e3e3d0bb63524a4ee42e416591a1e23f9ddf48
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        int id = item.getItemId();
        if (id == R.id.add)
        {
            Intent i=new Intent(MainActivity.this,AddCity.class);
            startActivity(i);
        }
        return false;
    }

}
