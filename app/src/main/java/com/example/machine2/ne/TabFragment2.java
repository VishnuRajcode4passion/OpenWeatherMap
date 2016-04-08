package com.example.machine2.ne;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.concurrent.ExecutionException;


public class TabFragment2 extends Fragment {


    RequestQueue queue;
    ProgressDialog progressDialog;
    Bundle bundle;
    String data;
    String url;
    JsonObjectRequest jsObjRequest;
    ImageView imageView;
    ListView lv;
    String time[] = new String[7];
    String description[] =new String[7];
    String temperature[] = new String[7];
    Bitmap image[] = new Bitmap[7];
    String base[] =new String[7];
    String icon[] = new String[7];

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView=(ImageView)getActivity().findViewById(R.id.imageView2);
        lv=(ListView) getActivity().findViewById(R.id.listView2);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_2, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("LOADING...");
        progressDialog.show();

        queue = Volley.newRequestQueue(getContext());
        // Getting the data from previous activity and passing that data into url and displaying all the informations related to that particular data.
        bundle =  getActivity().getIntent().getExtras();
        data =bundle.getString("data");
        url = "http://api.openweathermap.org/data/2.5/forecast?q="+data+"&cnt=7&APPID=45df4fca7d202600be0e657e2d0a9dcd";
        jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            @Override
            // JSON response will be obtained in this method if there are no network issues
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                System.out.println("FRAGMENT2 RESPONSE " + response);
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = response.getJSONArray("list");
                    System.out.println("JSON ARRAY "+jsonArray);

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                         time[i] = jsonObject.getString("dt_txt");
                        JSONArray jsonArray2 = jsonObject.getJSONArray("weather");
                        JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
                        icon[i] = jsonObject2.getString("icon");
                        base[i]= "http://api.openweathermap.org/img/w/"+icon[i]+".png";
                        LoadImageTask loadImageTask = new LoadImageTask();

                        try {
                            image[i] = loadImageTask.execute(base[i]).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        description[i] = jsonObject2.getString("description");
                        JSONObject jsonObject3 = jsonObject.getJSONObject("main");
                        temperature[i] = jsonObject3.getString("temp");
                        System.out.println("TIME "+time[i]);
                        System.out.println("DESCRIPTION "+description[i]);
                        System.out.println("TEMPERATURE " + temperature[i]);
                        System.out.println("IMAGE "+image[i]);
                    }
                    lv.setAdapter(new CustomAdapter(getContext(),time,description,temperature,image));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            //If there is any error in network connection ,then this method will be executed
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Network Error ", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsObjRequest);

    }



