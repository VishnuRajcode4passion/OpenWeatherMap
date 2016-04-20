package com.example.machine2.ne;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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
    ListView cities;

    RequestQueue queue;
    ProgressDialog progressDialog;

    String url;
    String tempincelsius[];
    String cityname[];

    JsonObjectRequest jsObjRequest;

    Bitmap image;

    ArrayList<Cities> cityList;
    ArrayAdapter<Cities> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cities = (ListView) findViewById(R.id.listView);
        SQLController sqlController = new SQLController(MainActivity.this);
        sqlController.open();

        final String cursor = sqlController.fetch();
        sqlController.close();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("LOADING...");
        progressDialog.show();

        queue = Volley.newRequestQueue(this);
        url = "http://api.openweathermap.org/data/2.5/group?id=" + cursor + "&units=metric&appid=45df4fca7d202600be0e657e2d0a9dcd";
        System.out.println("CITY NAMES ARE " + url);
        jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            // JSON response will be obtained in this method if there are no network issues
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub

                progressDialog.dismiss();

                try {
                    JSONArray jsonArray = response.getJSONArray("list");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        Cities city = new Cities();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONArray jsonArray1 = jsonObject.getJSONArray("weather");
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
                        String icon = jsonObject1.getString("icon");
                        String image = "http://api.openweathermap.org/img/w/" + icon + ".png";
                        try {
                            city.setIcon(new LoadImageTask().execute(image).get());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        JSONObject jsonObject2 = jsonObject.getJSONObject("main");
                        city.setTemperature(jsonObject2.getString("temp"));
                        city.setName(jsonObject.getString("name"));
                        cityList.add(city);
                    }

                    arrayAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            //If there is any error in network connection ,then this method will be executed
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Network Error ", Toast.LENGTH_LONG).show();
            }
        });


        queue.add(jsObjRequest);

        cityList = new ArrayList<Cities>();
        arrayAdapter = new CityListAdapter(this, R.layout.city_list, cityList);
        cities.setAdapter(arrayAdapter);


        //when clicking the particular item in listview ,pass the data to detailPage activity.
        cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view.findViewById(R.id.textView6);

                String data = (String) v.getText();
                Intent i = new Intent(MainActivity.this, SlidingMainActivity.class);
                i.putExtra("data", data);
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