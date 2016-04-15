package com.example.machine2.ne;

/**
 * Created by machine3 on 4/11/16.
 */

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