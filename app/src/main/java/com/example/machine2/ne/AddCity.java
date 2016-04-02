package com.example.machine2.ne;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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
public class AddCity extends Activity
{

    EditText search;
    ListView view;
    ImageButton imageButton;
    ArrayList<String> arrayList = new ArrayList<>();
    String name;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);
        final RequestQueue queue = Volley.newRequestQueue(this);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        view = (ListView) findViewById(R.id.cityList);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                search = (EditText) findViewById(R.id.editText);
                String list = search.getText().toString();
                String url = "http://api.openweathermap.org/data/2.5/find?q=" + list + "&type=like&cnt=10&APPID=45df4fca7d202600be0e657e2d0a9dcd";

                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        // TODO Auto-generated method stub
                        System.out.println("RESPONSE " + response);
                        try
                        {

                            JSONArray jsonArray = new JSONArray(response.getString("list"));
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                System.out.println(jsonArray);
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                name = jsonObject.getString("name");
                                arrayList.add(name);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_textcolor, arrayList);
                                view.setAdapter(adapter);




                                view.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                                    {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddCity.this);
                                        alertDialogBuilder.setMessage("Are you sure,You wanted to Add City");

                                        final String data=(String)parent.getItemAtPosition(position);  // Bug fixed when searching and add city to the list



                                        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1)
                                            {
//                                                ArrayList<String> mylist = new ArrayList<String>();
//                                                mylist.add(name);
                                               // System.out.println("array" + mylist);
                                                Intent intent = new Intent(AddCity.this, MainActivity.class);

                                                intent.putExtra("mylist", name);

                                                intent.putExtra("mylist", data);


                                                MainActivity obj=new MainActivity();
                                             //   obj.merge();
                                                startActivity(intent);


                                            }
                                        });

                                        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        });

                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                        alertDialog.show();

                                    }
                                });



                            }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), "Volley Error ", Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(jsObjRequest);

            }
        });

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddCity.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to Add City");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
//                        String name = (String) parent.getItemAtPosition(position);
                        Intent intent = new Intent(AddCity.this, MainActivity.class);
                        intent.putExtra("mylist", name);
                        startActivity(intent);
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        }
    }





