package com.example.machine2.ne;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;

import android.os.Bundle;
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



import static java.lang.Math.round;

public class DetailPage extends Activity {

    TextView des,temp,pre,hum,win,city;
    String url = "http://api.openweathermap.org/data/2.5/weather?q=muvattupuzha&units=metric&APPID=45df4fca7d202600be0e657e2d0a9dcd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);



        RequestQueue queue = Volley.newRequestQueue(this);

        final ProgressDialog  progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("LOADING...");
        progressDialog.show();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                progressDialog.dismiss();
                System.out.println("RESPONSE "+response);

                try {

                    JSONArray jsonArray = new JSONArray(response.getString("weather"));
                    System.out.println(jsonArray);

                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String description = jsonObject.getString("description");
                    des.setText(description);


                    JSONObject object = response.getJSONObject("main");
                    String tempincelsius= object.getString("temp");
                    temp.setText(tempincelsius+ " degree celsius");

                    String pressure = object.getString("pressure");
                    String humidity = object.getString("humidity");

                    pre.setText(pressure+ " hPa");
                    hum.setText(humidity+ "%");

                    JSONObject jsonObject1 = new JSONObject(response.getString("wind"));

                    String windspeed = jsonObject1.getString("speed");
                    String winddegree = jsonObject1.getString("deg");
                    win.setText(windspeed+ " mps "+winddegree+" degree");

                    String cityname = response.getString("name");

                    JSONObject jsonObj = new JSONObject(response.getString("sys"));
                    String countryname = jsonObj.getString("country");

                    city.setText(cityname + "," + countryname);
                }
                catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Network Error ",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsObjRequest);


    }


}