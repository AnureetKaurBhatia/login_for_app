package com.example.anureetkaur.cmccarpool;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity
{
    TextView tv;
    EditText et,et1,et2;
    Button btn;
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tv=(TextView)findViewById(R.id.tv);
        tv.setTextColor(Color.BLUE);
        tv.setTextSize(34);
        myDB=new DatabaseHelper(this);
        et=(EditText)findViewById(R.id.et);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        btn=(Button)findViewById(R.id.btn);
        addUser();
    }
    public void addUser()
    {
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Phone=et.getText().toString();
                String Name=et1.getText().toString();
                String College=et2.getText().toString();
                if(Phone.length()==0 || Name.length()==0 || College.length()==0)
                {
                    if(Phone.length()==0)
                    {
                        et.setError("Phone Number Compulsory");
                    }
                    if(Name.length()==0)
                    {
                        et1.setError("Name Compulsory");
                    }
                    if(College.length()==0)
                    {
                        et2.setError("College Compulsory");
                    }
                    Toast.makeText(Register.this, "Phone Number, Name Or College Missing", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Boolean isInserted=myDB.insertUser(Phone,Name,College);
                    if(isInserted==true)
                    {
                        Toast.makeText(Register.this, "User Registered!!!", Toast.LENGTH_LONG).show();
                        Cursor res=myDB.getData(Phone);
                        res.moveToNext();
                        String pass=res.getString(3);
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(Phone,null,"Your Password is "+pass,null,null);
                        Intent n=new Intent(Register.this,Login1.class);
                        startActivity(n);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Register.this, "This Phone Number Already Exists!!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
