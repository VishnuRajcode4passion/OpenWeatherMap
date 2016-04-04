package com.example.machine2.ne;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by machine2 on 02/04/16.
 */
public class Urldata extends Activity {
    TextView des,temp,pre,hum,win,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        des = (TextView) findViewById(R.id.textView8);
        temp = (TextView) findViewById(R.id.textView9);
        pre = (TextView) findViewById(R.id.textView3);
        hum = (TextView) findViewById(R.id.textView4);
        win = (TextView) findViewById(R.id.textView7);
        city = (TextView) findViewById(R.id.textView5);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("LOADING...");
        progressDialog.show();


    }
    public void DisplatData()
    {

    }
}
