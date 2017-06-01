package com.example.acer.bloodbankapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdminLogin extends AppCompatActivity {

    DataBase myDb;
    EditText AdminId;
    EditText Password;
    Button Admin_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        myDb = new DataBase(this);
        AdminId = (EditText)findViewById(R.id.AdminId);
        Password = (EditText)findViewById(R.id.Password);
        Admin_Login = (Button)findViewById(R.id.Admin_Login);
    }

    public void onclick(View view)
    {
        if(AdminId.getText().toString().equals("") || Password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please Fill All Fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor res = myDb.checkAdmin(AdminId.getText().toString(),Password.getText().toString());
        int flag=0;
        while(res.moveToNext())
        {
            if(res.getString(0).equals(AdminId.getText().toString()) && res.getString(1).equals(Password.getText().toString()))
            {
                flag=1;
                break;
            }
        }

        if(flag==1)
        {
            Intent i = new Intent(this,BloodStock.class);
            startActivity(i);
        }

        else
        {
            Toast.makeText(AdminLogin.this, "WRONG ID OR PASSWORD...!!!", Toast.LENGTH_SHORT).show();
        }

    }

}
