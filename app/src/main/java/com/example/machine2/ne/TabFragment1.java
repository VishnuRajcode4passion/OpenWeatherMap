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


             import java.util.Date;

public class TabFragment1 extends Fragment {

        TextView tvDesription;
        TextView tvTemparature;
        TextView tvPressure;
        TextView tvHumidity;
        TextView tvWind;
        TextView tvCity;
        TextView tvGroundLevel;
        TextView tvSeaLevel;
        ImageView imageView;
        RequestQueue queue;
        ProgressDialog progressDialog;
        Bundle bundle;
        String data;
        String url;
        JsonObjectRequest jsObjRequest;
        JSONArray jsonArray;
        JSONObject jsonObject;
        String description;
        String icon;
        String base;
        JSONObject object;
        String tempincelsius;
        String pressure;
        String humidity;
        String windspeed;
        JSONObject jsonObject1;
        String winddegree;
        String cityname;
        JSONObject jsonObj;
        String countryname;
        String groundLevel;
        String seaLevel;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvDesription = (TextView)getActivity().findViewById(R.id.textView8);
        imageView=(ImageView)getActivity().findViewById(R.id.showicon);
        tvTemparature = (TextView) getActivity().findViewById(R.id.textView9);
        tvPressure = (TextView) getActivity().findViewById(R.id.textView3);
        tvHumidity = (TextView) getActivity().findViewById(R.id.textView4);
        tvWind = (TextView)getActivity(). findViewById(R.id.textView7);
        tvCity = (TextView) getActivity().findViewById(R.id.textView5);
        tvGroundLevel = (TextView) getActivity().findViewById(R.id.textView10);
        tvSeaLevel = (TextView) getActivity().findViewById(R.id.textView12);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("LOADING...");
        progressDialog.show();

        queue = Volley.newRequestQueue(getContext());
        // Getting the data from previous activity and passing that data into url and displaying all the informations related to that particular data.
        bundle =  getActivity().getIntent().getExtras();
        data =bundle.getString("data");
        url = "http://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&APPID=45df4fca7d202600be0e657e2d0a9dcd";
        jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            @Override
            // JSON response will be obtained in this method if there are no network issues
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
               //  System.out.println("RESPONSE "+response);
                progressDialog.dismiss();
                try {
                    jsonArray = new JSONArray(response.getString("weather"));
                    jsonObject = jsonArray.getJSONObject(0);
                    description = jsonObject.getString("description");
                    tvDesription.setText(description);

                    icon=jsonObject.getString("icon");
                    base= "http://api.openweathermap.org/img/w/"+icon+".png";
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
                    tvGroundLevel.setText(groundLevel+" hPa");
                    tvSeaLevel.setText(seaLevel+" hPa");
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
