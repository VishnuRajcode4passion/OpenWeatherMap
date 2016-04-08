package com.example.machine2.ne;

/**
 * Created by machine3 on 4/7/16.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;


public class LoadImageTask extends AsyncTask<String,Void,Bitmap> {

    ImageView imageView;
    String urlOfImage;
    Bitmap image;
    InputStream inputStream;

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

}
