package com.example.machine2.ne;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class TabFragment1 extends Fragment
{

    // Variables are declared


        TextView tvDesription;
        TextView tvTemparature;
        TextView tvPressure;
        TextView tvHumidity;
        TextView tvWind;
        TextView tvCity;
        TextView SeaLevel;
        TextView GroundLevel;


        ImageView imageView;

        RequestQueue queue;

        ProgressDialog progressDialog;

        Bundle bundle;

        String data;
        String url;
        String description;
        String icon;
        String base;
        String tempincelsius;
        String pressure;
        String humidity;
        String windspeed;
        String winddegree;
        String cityname;
        String countryname;
        String groundLevel;
        String seaLevel;


    JsonObjectRequest jsObjRequest;
        JSONArray jsonArray;
        JSONObject jsonObject;
        JSONObject object;
        JSONObject jsonObject1;
        JSONObject jsonObj;










    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);



        tvDesription = (TextView)getActivity().findViewById(R.id.textView_weather_condition);
        imageView=(ImageView)getActivity().findViewById(R.id.showicon);
        tvTemparature = (TextView) getActivity().findViewById(R.id.textView_TemperatureOfCity);
        tvPressure = (TextView) getActivity().findViewById(R.id.textView_pressureValue);
        tvHumidity = (TextView) getActivity().findViewById(R.id.textView_humidityValue);
        tvWind = (TextView)getActivity(). findViewById(R.id.textView_windDescription);
        tvCity = (TextView) getActivity().findViewById(R.id.textView_Name_of_city);


         SeaLevel =(TextView)getActivity().findViewById(R.id.sl_value);
         GroundLevel =(TextView)getActivity().findViewById(R.id.gl_value);



        try {
            // Getting the data from previous activity and showing in list view.


        }

        catch (Exception e) { }



//        tvGroundLevel = (TextView) getActivity().findViewById(R.id.textView10);
//        tvSeaLevel = (TextView) getActivity().findViewById(R.id.textView12);


    }

    // Inflates the layout of tab_fragment1
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Showing the progress dialog when the device fetching the weather details from the network

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading Weather Details");
        progressDialog.show();

        queue = Volley.newRequestQueue(getContext());

        // Getting the data from previous activity and passing that data into url and displaying all the informations related to that particular data.

        bundle =  getActivity().getIntent().getExtras();
        data =bundle.getString("data");

        // Fetching the data using URL

        url = "http://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&APPID=45df4fca7d202600be0e657e2d0a9dcd";
        jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            @Override
            // JSON response will be obtained in this method if there are no network issues
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub

       //  System.out.println("RESPONSE "+response);


                // Fetching and assigninig data from the URL  to the desired views in the layout

                progressDialog.dismiss();
                try {
                    jsonArray = new JSONArray(response.getString("weather"));
                    jsonObject = jsonArray.getJSONObject(0);
                    description = jsonObject.getString("description");
                    tvDesription.setText(description);

                    icon=jsonObject.getString("icon");
                    base= "http://openweathermap.org/img/w/"+icon+".png";
                    new DownloadImageTask(imageView).execute(base);

                    object = response.getJSONObject("main");
                    tempincelsius= object.getString("temp");
                    tvTemparature.setText(tempincelsius+ " °C");


                    pressure = object.getString("pressure");
                    humidity = object.getString("humidity");

                    tvPressure.setText(pressure+ " hPa");
                    tvHumidity.setText(humidity+ "%");

                    jsonObject1 = new JSONObject(response.getString("wind"));
                    windspeed = jsonObject1.getString("speed");
                    winddegree = jsonObject1.getString("deg");
                    tvWind.setText(windspeed+ " mps "+winddegree+" °");

                    cityname = response.getString("name");
                    jsonObj = new JSONObject(response.getString("sys"));
                    countryname = jsonObj.getString("country");

                    tvCity.setText(cityname + "," + countryname);

                    jsonObj = new JSONObject(response.getString("main"));
                    groundLevel = jsonObj.getString("grnd_level");
                    seaLevel = jsonObj.getString("sea_level");
                    GroundLevel.setText(groundLevel + " hPa");
                    SeaLevel.setText(seaLevel + " hPa");



                }
                catch (JSONException e) {
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
}
