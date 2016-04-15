package com.example.machine2.ne;

/**
 * Created by machine3 on 4/11/16.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CityListAdapter extends BaseAdapter{

    Context context;
    String temperature[]=new String[10];
    String city[]= new String[10];
    Bitmap image[]=new Bitmap[10];

    private static LayoutInflater inflater=null;
    public CityListAdapter(Context context, String[] city, String[] temperature, Bitmap[] image) {
        // TODO Auto-generated constructor stub
        this.context=context;
        this.temperature=temperature;
        this.city=city;
        this.image=image;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return city.length;
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
        TextView tvTemperature;
        TextView tvCity;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.single_row2, null);
        holder.img=(ImageView) rowView.findViewById(R.id.image);
        holder.tvTemperature=(TextView) rowView.findViewById(R.id.text);
        holder.tvCity=(TextView) rowView.findViewById(R.id.text2);
        System.out.println("TEMPERATURE "+temperature[position]);
        holder.tvTemperature.setText(temperature[position] + " °C");
        holder.img.setImageBitmap(image[position]);
        holder.tvCity.setText(city[position]);

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


