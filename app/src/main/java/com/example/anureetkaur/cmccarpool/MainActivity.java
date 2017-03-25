package com.example.anureetkaur.cmccarpool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
{
    ActionBar ab;
    ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ab=getSupportActionBar();
        ab.hide();
        spinner=(ProgressBar)findViewById(R.id.pb);
        spinner.setVisibility(View.VISIBLE);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(5000);
                    Intent n=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(n);
                    finish();
                }
                catch(Exception ex)
                {
                   // Log.e(TAG, "run: ", );
                }
            }
        }).start();
    }
}
