

package com.example.machine2.ne;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
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

/**
 * Created by machine2 on 30/03/16.
 */
/*The activity for add the city to Main Activity

 */
public class AddCity extends AppCompatActivity {
    //variables declaration
    EditText search;
    ListView listView;
    ImageButton imageButton;
    ArrayList<String> arrayList = new ArrayList<>();
    String name;
    String url;
    JSONArray jsonArray;
    JsonObjectRequest jsObjRequest;
    JSONObject jsonObject;
    ArrayAdapter<String> adapter;
    AlertDialog.Builder alertDialogBuilder;
    Intent intent;
    AlertDialog alertDialog;
    RequestQueue queue;
    String cityId;
    String list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);
        queue = Volley.newRequestQueue(this);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        listView = (ListView) findViewById(R.id.cityList);
        //Onclick  of  image button
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                search = (EditText) findViewById(R.id.editText);
                list = search.getText().toString().trim();

                if (list.isEmpty() || list.length() == 0 || list.equals("") || list == null) {
                    Toast.makeText(getApplication(), "Enter a city", Toast.LENGTH_LONG).show();
                } else {
                    //fetch data from the internet with selected city
                    url = "http://api.openweathermap.org/data/2.5/find?q=" + list + "&type=like&cnt=10&APPID=45df4fca7d202600be0e657e2d0a9dcd";
                    //Request for the json objects
                    jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // TODO Auto-generated method stub
                            System.out.println("RESPONSE " + response);
                            try {

                                jsonArray = new JSONArray(response.getString("list"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    name = jsonObject.getString("name");
//                                    id = jsonObject.getString("id");
                                    arrayList.add(name);
                                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_textcolor, arrayList);
                                    listView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "volley error", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(jsObjRequest);
                }
            }
        });
        //The onclick of the list view
        //Searching city results are displayed on this listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                alertDialogBuilder = new AlertDialog.Builder(AddCity.this);
                alertDialogBuilder.setMessage("Are you sure,you want to add the city");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String name = (String) parent.getItemAtPosition(position);

                        url = "http://api.openweathermap.org/data/2.5/weather?q=" + name + "&APPID=45df4fca7d202600be0e657e2d0a9dcd";
                        //Request for the json objects
                        jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // TODO Auto-generated method stub
                                System.out.println("RESPONSE " + response);
                                try {
                                    cityId = response.getString("id");
                                    System.out.println("THE CLICKED CITY ID IS " +cityId);
                                    intent = new Intent(AddCity.this, MainActivity.class);
                                    SQLController sqlController = new SQLController(AddCity.this);
                                    sqlController.open();
                                    sqlController.insert(cityId);
                                    sqlController.close();
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "volley error", Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(jsObjRequest);

                    }

                });


                //alert dialog is displayed when click on the listview to add the city
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });
    }

}




