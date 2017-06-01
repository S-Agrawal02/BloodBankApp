package com.example.acer.bloodbankapp;

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


public class UpdateStock extends AppCompatActivity {

    DataBase myDb;
    EditText type;
    EditText amount;
    Button btn_don;
    Button btn_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stock);
        myDb = new DataBase(this);
        type = (EditText)findViewById(R.id.type);
        amount = (EditText)findViewById(R.id.amount);
        btn_don = (Button)findViewById(R.id.btn_don);
        btn_acc = (Button)findViewById(R.id.btn_acc);

        btn_don.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(type.getText().toString().equals("") || amount.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Boolean response = myDb.addStock(type.getText().toString().toUpperCase(),amount.getText().toString());
                        if(response==true)
                            Toast.makeText(UpdateStock.this,"Blood Stock Updated",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(UpdateStock.this,"Blood Group Not Available",Toast.LENGTH_SHORT).show();
                    }
                }
        );

        btn_acc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(type.getText().toString().equals("") || amount.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Boolean response = myDb.redStock(type.getText().toString().toUpperCase(), amount.getText().toString());
                        if(response==true)
                            Toast.makeText(UpdateStock.this,"Blood Stock Updated",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(UpdateStock.this,"Blood Group Not Available OR Less Stock",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}
