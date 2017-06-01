package com.example.acer.bloodbankapp;

import android.app.AlertDialog;
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


public class BloodStock extends AppCompatActivity {

    DataBase myDb;
    EditText type;
    EditText amount;
    Button add_type;
    Button show_stock;
    Button btn_update;
    Button add_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_stock);
        type = (EditText)findViewById(R.id.type);
        amount = (EditText)findViewById(R.id.amount);
        add_type = (Button)findViewById(R.id.add_type);
        show_stock = (Button)findViewById(R.id.show_stock);
        btn_update = (Button)findViewById(R.id.btn_update);
        add_admin = (Button)findViewById(R.id.add_admin);
        myDb = new DataBase(this);
        AddType();
        ShowStock();
        AddAdmin();
        UpdateStock();
    }

    public void AddAdmin()
    {
        add_admin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(BloodStock.this, AddAdmin.class);
                        startActivity(i);
                    }
                }
        );
    }

    public void AddType()
    {
        add_type.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(type.getText().toString().equals("") || amount.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean insert = myDb.insertBlood(type.getText().toString().toUpperCase(), Integer.parseInt(amount.getText().toString()));
                        if (insert == true) {
                            Toast.makeText(BloodStock.this, "BLOOD STOCK UPDATED", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(BloodStock.this, "BLOOD GROUP ALREADY AVAILABLE", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }           //AddAdmin function

    public void ShowStock()
    {
        show_stock.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getStock();
                        if (res.getCount() == 0) {
                            //Toast.makeText(MainActivity.this,"NO ADMIN FOUND",Toast.LENGTH_LONG).show();
                            showmessage("SORRY", "NO BLOOD FOUND");
                            return;
                        }
                        StringBuffer bf = new StringBuffer();
                        while (res.moveToNext())
                        {
                            bf.append("BLOOD TYPE : " + res.getString(0) + "\n");
                            bf.append("AMOUNT : " + res.getString(1) + "\n\n");
                        }
                        showmessage("BLOOD STOCK", bf.toString());

                    }
                }
        );
    }       //ShowAdmin Function

    public void UpdateStock()
    {
        btn_update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(BloodStock.this,UpdateStock.class);
                        startActivity(i);
                    }
                }
        );
    }

    public void showmessage(String title,String data)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(data);
        builder.show();
    }

}
