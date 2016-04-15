package com.example.machine2.ne;

/**
 * Created by machine3 on 4/11/16.
 */

//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.sql.Array;
//import java.util.ArrayList;
//
//public class CityListAdapter extends ArrayAdapter<Cities> {
//
//    Context context;
//    String temperature[];
//    String city[];
//    Bitmap image[];
//
//    ArrayList<Cities> cityList;
//
//    private static LayoutInflater inflater=null;
//    public CityListAdapter(Context context,  ArrayList<Cities> cityList) {
//        super(context,cityList);
//        // TODO Auto-generated constructor stub
//        this.context=context;
//        this.cityList=cityList;
//        this.temperature=temperature;
//        this.city=city;
//        this.image=image;
//        inflater = ( LayoutInflater )context.
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return city.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        // TODO Auto-generated method stub
//        return position;
//    }
//
//    public class Holder
//    {
//        TextView tvTemperature;
//        TextView tvCity;
//        ImageView img;
//    }
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        Holder holder=new Holder();
//        View rowView;
//        rowView = inflater.inflate(R.layout.city_list, null);
//        holder.img=(ImageView) rowView.findViewById(R.id.imageView3);
//        holder.tvTemperature=(TextView) rowView.findViewById(R.id.textView15);
//        holder.tvCity=(TextView) rowView.findViewById(R.id.textView6);
//        holder.tvTemperature.setText(temperature[position] + " °C");
//      //  holder.img.setImageBitmap(image[position]);
//        holder.tvCity.setText(city[position]);
//
//        rowView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                // Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
//
//            }
//        });
//        return rowView;
//    }
//
//}
//
//
import java.util.List;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CityListAdapter extends ArrayAdapter<Cities> {

    private Activity activity;

    public CityListAdapter(Activity activity, int resource, List<Cities> cities) {
        super(activity, resource, cities);
        this.activity = activity;

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.city_list, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        Cities city = getItem(position);

        holder.name.setText(city.getName());
        holder.temperature.setText(city.getTemperature());
        holder.image.setImageBitmap(city.getIcon());

        return convertView;

    }

    private static class ViewHolder {
        private TextView name;
        private TextView temperature;
        private ImageView image;

        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.textView6);
            image = (ImageView) v.findViewById(R.id.imageView3);
            temperature = (TextView) v.findViewById(R.id.textView15);
        }
    }

}