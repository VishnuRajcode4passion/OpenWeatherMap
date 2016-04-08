package com.example.machine2.ne;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

// Class for fetching the icon / image corresponding to the corrent weather situation

public class DownloadImageTask extends AsyncTask<String,Void,Bitmap>
{
    // Declaration of variables

    ImageView WeatherIcon;
    String urlOfImage;
    Bitmap image;
    InputStream inputStream;

    public DownloadImageTask(ImageView imageView) {

        this.WeatherIcon = imageView;
    }
    // fetching image from url
    protected Bitmap doInBackground(String...urls){
        urlOfImage = urls[0];
        image = null;
    try{
        inputStream = new URL(urlOfImage).openStream();
        image = BitmapFactory.decodeStream(inputStream);
    }catch(Exception e){
        e.printStackTrace();
    }
    return image;

}
    //set image on bitmap
    protected void onPostExecute(Bitmap result){

        WeatherIcon.setImageBitmap(result);

  }
 }
