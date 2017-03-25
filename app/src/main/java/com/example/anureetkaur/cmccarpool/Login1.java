package com.example.anureetkaur.cmccarpool;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login1 extends AppCompatActivity
{
    TextView tv;
    Button btn;
    EditText et,et1;
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        tv=(TextView)findViewById(R.id.tv);
        tv.setTextColor(Color.BLUE);
        tv.setTextSize(34);
        myDB=new DatabaseHelper(this);
        btn=(Button)findViewById(R.id.btn);
        et=(EditText)findViewById(R.id.et);
        et1=(EditText)findViewById(R.id.et1);
        LOG();
    }
    public void LOG()
    {
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Phone=et.getText().toString();
                String Password=et1.getText().toString();
                if(Phone.length()==0 || Password.length()==0)
                {
                    if(Phone.length()==0)
                    {
                        et.setError("Phone Number Compulsory");
                    }
                    if(Password.length()==0)
                    {
                        et1.setError("Password Compulsory");
                    }
                    Toast.makeText(Login1.this, "Phone Number Or Password Missing", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Cursor res=myDB.getData(Phone);
                    if(res.getCount()==0)
                    {
                        Toast.makeText(Login1.this, "This User Does Not Exist", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                    {
                        while(res.moveToNext())
                        {
                            String phn=res.getString(0);
                            String pass=res.getString(3);
                            if(phn.equals(Phone) && pass.equals(Password))
                            {
                                Toast.makeText(Login1.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                                Intent n=new Intent(Login1.this,Choose.class);
                                startActivity(n);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(Login1.this, "Phone Number Or Password Incorrect", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

            }
        });
    }
}
