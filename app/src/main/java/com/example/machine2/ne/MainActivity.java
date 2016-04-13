
package com.example.machine2.ne;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.concurrent.ExecutionException;


//Main activity class
public class MainActivity extends AppCompatActivity {
    ListView listView;

    ArrayAdapter<String> arrayAdapter;

    RequestQueue queue;
    ProgressDialog progressDialog;
    String url[] = new String[30];
    JsonObjectRequest jsObjRequest;
    JSONArray jsonArray;
    JSONObject jsonObject;
    JSONObject object;
    String tempincelsius[]=new String[30];
    String cityname[]=new String[30];
    String cityNames[] = new String[30];
    JSONObject jsonObj;
    String countryname[]= new String[30];
    Bitmap image[]=new Bitmap[30];
    int i;
    String base[]=new String[30];
    String icon[]=new String[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView= (ListView)findViewById(R.id.listView);

        SQLController sqlController = new SQLController(MainActivity.this);
        sqlController.open();
        ArrayList cursor = sqlController.fetch();
        sqlController.close();
        System.out.println("ARRAY LIST CONTENTS "+cursor);
//        cityNames = new String[cursor.size()];
//        cityNames = (String[]) cursor.toArray(cityNames);
//        for(String s : cityNames)
//            System.out.println("CITY IDs "+s);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("LOADING...");
//    //    progressDialog.show();
//                queue = Volley.newRequestQueue(this);
//                System.out.println("CITY NAMES LENGTH " + cityNames.length);
//        if(cityNames.length!=0) {
//            url[i] = "http://api.openweathermap.org/data/2.5/weather?q=" + cityNames[i] + "&units=metric&APPID=45df4fca7d202600be0e657e2d0a9dcd";
//            System.out.println("CITY NAMES ARE " + cityNames[i]);
//            jsObjRequest = new JsonObjectRequest(Request.Method.GET, url[i], null, new Response.Listener<JSONObject>() {
//                @Override
//                // JSON response will be obtained in this method if there are no network issues
//                public void onResponse(JSONObject response) {
//                    // TODO Auto-generated method stub
//                    System.out.println("RESPONSE " + response);
//                    //  progressDialog.dismiss();
//                    try {
//                        jsonArray = new JSONArray(response.getString("weather"));
//                        jsonObject = jsonArray.getJSONObject(0);
//                        icon[i] = jsonObject.getString("icon");
//                        base[i] = "http://api.openweathermap.org/img/w/" + icon[i] + ".png";
//                        try {
//                            image[i] = new LoadImageTask().execute(base[i]).get();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        }
//
//                        object = response.getJSONObject("main");
//                        tempincelsius[i] = object.getString("temp");
//                        // tvTemparature.setText(tempincelsius + " °C");
//
//                        cityname[i] = response.getString("name");
//                        jsonObj = new JSONObject(response.getString("sys"));
//                        countryname[i] = jsonObj.getString("country");
//                        // tvCity.setText(cityname + "," + countryname);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                //If there is any error in network connection ,then this method will be executed
//                public void onErrorResponse(VolleyError error) {
//                    progressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Network Error ", Toast.LENGTH_LONG).show();
//                }
//            });
//            System.out.println("CITY NAME " + cityname[i]);
//            System.out.println("IMAGE " + image[i]);
//            System.out.println("TEMPERATURE " + tempincelsius[i]);
//            queue.add(jsObjRequest);
//
//        }
//            CityListAdapter cityListAdapter = new CityListAdapter(this,cityname,tempincelsius,image);
//            listView.setAdapter(cityListAdapter);

        //when clicking the particular item in listview ,pass the data to detailPage activity.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data=(String)parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this,SlidingActivity.class);
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
        if (id == R.id.add) {
            Intent i = new Intent(MainActivity.this, AddCity.class);
            startActivity(i);
        }
        return false;
    }
}
