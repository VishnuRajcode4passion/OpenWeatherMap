package com.example.machine2.ne;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by machine2 on 09/04/16.
 */
public class View extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Bundle b=getIntent().getExtras();
        String m=b.getString("msg");
        ListView listView;
        listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> display = new ArrayList<String>();
        display.add(m);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, display);
        listView.setAdapter(arrayAdapter);




    }

}
