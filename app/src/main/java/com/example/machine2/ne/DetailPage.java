package com.example.machine2.ne;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.widget.ImageView;
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

public class DetailPage extends Activity {


<<<<<<< HEAD
    TextView desription,temparature,pressure,humidity,wind,city;
=======
    TextView tvDesription,tvTemparature,tvPressure,tvHumidity,tvWind,tvCity;
>>>>>>> 9e06b083f319bdf19a2f13e850e87592999e2663
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        tvDesription = (TextView) findViewById(R.id.textView8);
        imageView=(ImageView)findViewById(R.id.showicon);
<<<<<<< HEAD
        temparature = (TextView) findViewById(R.id.textView9);
        pressure = (TextView) findViewById(R.id.textView3);
        humidity = (TextView) findViewById(R.id.textView4);
        wind = (TextView) findViewById(R.id.textView7);
        city = (TextView) findViewById(R.id.textView5);
=======
        tvTemparature = (TextView) findViewById(R.id.textView9);
        tvPressure = (TextView) findViewById(R.id.textView3);
        tvHumidity = (TextView) findViewById(R.id.textView4);
        tvWind = (TextView) findViewById(R.id.textView7);
        tvCity = (TextView) findViewById(R.id.textView5);
>>>>>>> 9e06b083f319bdf19a2f13e850e87592999e2663
        RequestQueue queue = Volley.newRequestQueue(this);

        final ProgressDialog  progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("LOADING...");
        progressDialog.show();
        Bundle bundle =  getIntent().getExtras();
        String data =bundle.getString("data");
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&APPID=45df4fca7d202600be0e657e2d0a9dcd";

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
                    tvDesription.setText(description);

                    String icon=jsonObject.getString("icon");
                     String base= "http://openweathermap.org/img/w/"+icon+".png";
                     new DownloadImageTask(imageView).execute(base);
                            JSONObject object = response.getJSONObject("main");
                    String tempincelsius= object.getString("temp");
<<<<<<< HEAD
                    temparature.setText(tempincelsius+ " °C");
=======
                    tvTemparature.setText(tempincelsius+ " degree celsius");
>>>>>>> 9e06b083f319bdf19a2f13e850e87592999e2663

                    String pressure = object.getString("pressure");
                    String humidity = object.getString("humidity");


<<<<<<< HEAD
                    pressure.setText(pressure+ " hPa");
                    humidity.setText(humidity+ "%");
=======
                    tvPressure.setText(pressure+ " hPa");
                    tvHumidity.setText(humidity+ "%");
>>>>>>> 9e06b083f319bdf19a2f13e850e87592999e2663

                    JSONObject jsonObject1 = new JSONObject(response.getString("wind"));

                    String windspeed = jsonObject1.getString("speed");
                    String winddegree = jsonObject1.getString("deg");
<<<<<<< HEAD


                    wind.setText(windspeed+ " mps "+winddegree+" °");
=======
                    tvWind.setText(windspeed+ " mps "+winddegree+" degree");
>>>>>>> 9e06b083f319bdf19a2f13e850e87592999e2663

                    String cityname = response.getString("name");

                    JSONObject jsonObj = new JSONObject(response.getString("sys"));
                    String countryname = jsonObj.getString("country");
                    tvCity.setText(cityname + "," + countryname);




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
