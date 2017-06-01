package com.example.acer.bloodbankapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddAdmin extends AppCompatActivity {
    DataBase myDb;
    EditText adminId;
    EditText password;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myDb = new DataBase(this);
        adminId = (EditText)findViewById(R.id.adminId);
        password = (EditText)findViewById(R.id.password);
        btn_add = (Button)findViewById(R.id.btn_add);
        AddData();
    }
    public void AddData()
    {
        btn_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //If not Inserted all the Filed values
                        if(adminId.getText().toString().equals("") || password.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        boolean insert = myDb.insertAdmin(adminId.getText().toString(), password.getText().toString());
                        if (insert == true) {
                            Toast.makeText(AddAdmin.this, "ADMIN ADDED", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(AddAdmin.this,"Duplicate Admin ID",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }           //AddAdmin function

}
