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

public class CustomAdapter extends BaseAdapter{
    Context context;
    String time[];
    String description[];
    String temperature[];
    Bitmap image[];
  //  int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Context context, String[] time, String[] description, String[] temperature, Bitmap[] image) {
        // TODO Auto-generated constructor stub
        this.time=time;
        this.context=context;
        this.description=description;
        this.temperature=temperature;
        this.image=image;
        //imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return time.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tvTime;
        TextView tvDescription;
        TextView tvTemperature;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.single_row, null);
        holder.tvTime=(TextView) rowView.findViewById(R.id.textView13);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView2);
        holder.tvDescription=(TextView) rowView.findViewById(R.id.textView2);
        holder.tvTemperature=(TextView) rowView.findViewById(R.id.textView14);
        holder.tvTime.setText(time[position]);
        holder.tvTemperature.setText(temperature[position]+"°C");
        holder.tvDescription.setText(description[position]);
        holder.img.setImageBitmap(image[position]);

       // holder.img.setImageResource(imageId[position]);
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
