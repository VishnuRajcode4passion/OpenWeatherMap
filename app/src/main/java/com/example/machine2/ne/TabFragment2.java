package com.example.machine2.ne;

        import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TabFragment2 extends Fragment {
    TextView tv,tv1;
    RequestQueue queue;
    String url;
    JsonObjectRequest jsObjRequest;
    JSONArray jsonArray;
    JSONObject jsonObject;
    String time;
    Bundle bundle;
    String data;
    int i=0;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv=(TextView)getActivity().findViewById(R.id.time1);
        tv1=(TextView)getActivity().findViewById(R.id.time2);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        bundle =  getActivity().getIntent().getExtras();
        data =bundle.getString("data");
        url = "http://api.openweathermap.org/data/2.5/forecast?q="+data+",us&appid=45df4fca7d202600be0e657e2d0a9dcd";
        jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            @Override
            // JSON response will be obtained in this method if there are no network issues
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                System.out.println("response" + response);
                try {
                    jsonArray = new JSONArray(response.getString("list"));
                    for ( i = 0; i < jsonArray.length(); i++)
                        jsonObject = jsonArray.getJSONObject(i);
                    time = jsonObject.getString("dt_txt");
                    tv.setText(time);

                }
                catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            //If there is any error in network connection ,then this method will be executed
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Network Error ", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsObjRequest);

    }


}



