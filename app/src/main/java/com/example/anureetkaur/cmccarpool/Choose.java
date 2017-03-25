package com.example.anureetkaur.cmccarpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Choose extends AppCompatActivity
{
    Button offer,take;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        offer=(Button)findViewById(R.id.btn);
        take=(Button)findViewById(R.id.btn1);
    }
}
