package com.example.machine2.ne;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter
{

    //Variables are declared as array

    Context context;
    String time[];
    String description[];
    String temperature[];
    Bitmap image[];

    private static LayoutInflater inflater=null;

    public CustomAdapter(Context context, String[] time, String[] description, String[] temperature, Bitmap[] image)
    {
        // TODO Auto-generated constructor stub
        this.time=time;
        this.context=context;
        this.description=description;
        this.temperature=temperature;
        this.image=image;
        //imageId=prgmImages;
                inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);  // Accessing layout Inflater service
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return time.length;
    }

    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tvTime;
        TextView tvDescription;
        TextView tvTemperature;
        ImageView img_Weather_icon;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.single_row, null);  //Infalting the single row layout

        //Taking the ID's of time,icon,descreption,temparature,

        holder.tvTime=(TextView) rowView.findViewById(R.id.textView_date_time);
        holder.img_Weather_icon=(ImageView) rowView.findViewById(R.id.imageView_icon_weather_forecast);
        holder.tvDescription=(TextView) rowView.findViewById(R.id.textView_description);
        holder.tvTemperature=(TextView) rowView.findViewById(R.id.textView_Temparature);
        holder.tvTime.setText(time[position]);

        holder.tvTemperature.setText(temperature[position]+ " °C");

        holder.tvDescription.setText(description[position]);
        holder.img_Weather_icon.setImageBitmap(image[position]);

       //onclick method of rowView


        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               // Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}
