
package com.example.machine2.ne;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

//Main activity class
public class MainActivity extends AppCompatActivity {
    ListView listView;

    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);

        SQLController sqlController = new SQLController(MainActivity.this);
        sqlController.open();
        ArrayList cursor = sqlController.fetch();
        sqlController.close();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_textcolor, cursor);
        listView.setAdapter(arrayAdapter);
        //when clicking the particular item in listview ,pass the data to detailPage activity.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, SlidingMainActivity.class);
                i.putExtra("data", data);
                startActivity(i);
//                SavePreferences("MEM1", name);
//                LoadPreferences();
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
        if (id == R.id.add) {
            Intent i = new Intent(MainActivity.this, AddCity.class);
            startActivity(i);
        }
        if (id == R.id.favorite) {
            // LoadPreferences();


        }
        return false;
    }
}
