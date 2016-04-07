package com.example.machine2.ne;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by machine4 on 4/7/16.
 */
public class forecast_fragment extends AsyncTask<Void,Void,Void>
{
    private Context mCon;

    public forecast_fragment(Context con)
    {
        mCon = con;
    }



    @Override
    protected Void doInBackground(Void... params)
    {
        try {
            // Set a time to simulate a long update process.
            Thread.sleep(4000);

            return null;

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        Toast.makeText(mCon, "Finished complex background function!",
                Toast.LENGTH_LONG).show();

        // Change the menu back
        ((MainActivity) mCon).resetUpdating();
    }
}