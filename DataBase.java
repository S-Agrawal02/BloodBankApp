package com.example.acer.bloodbankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBase extends SQLiteOpenHelper {


    private static final String bloodbank = "bloodbank.db";
    private static final String admin = "admin";
    private static final String AdminId = "adminId";
    private static final String Password = "password";

    private static final String stock = "stock";
    private static final String Type = "Type";
    private static final String Amount = "Amount";

    public DataBase(Context context) {
        super(context, bloodbank, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + admin + "(AdminId TEXT PRIMARY KEY,Password TEXT)");     //creating admin table
        db.execSQL("create table " + stock + "(Type TEXT PRIMARY KEY,Amount INTEGER)");
        String id="admin01";
        String pass="123";
        String sql="INSERT INTO admin (AdminId,Password) VALUES('"+id+"', '"+pass+"');";
        db.execSQL(sql);
        }

    public boolean insertAdmin(String adminId,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(AdminId, adminId);       //First one is the column name, second one is the value passed
        con.put(Password, password);
        long res = db.insert(admin,null,con);   //returns true if values inserted properly
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor getAdmin()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+admin,null);
        return res;
    }

    // TABLE FOR BLOOD STOCK
    public boolean insertBlood(String type,int amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(Type,type.toUpperCase());       //First one is the column name, second one is the value passed
        con.put(Amount,amount);
        long res = db.insert(stock,null,con);   //returns true if values inserted properly
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor getStock()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+stock,null);
        return res;
    }

    public Cursor checkAdmin(String id,String pass)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + admin, null);
        return res;
    }

    public boolean addStock(String type,String amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db1 = this.getReadableDatabase();
        int amt = Integer.parseInt(amount);

        String count = "select Amount from stock where Type ='"+type+"'";
        Cursor mcursor = db1.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getCount();
        if(icount>0)
        {
            String sql = "update stock set Amount = Amount + " + amt + " where Type ='" + type + "'";
            db.execSQL(sql);
            return true;
        }
        else
            return false;
    }

    public boolean redStock(String type,String amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db1 = this.getReadableDatabase();
        int amt = Integer.parseInt(amount);

        String count = "select Amount from stock where Type ='"+type+"'";
        Cursor mcursor = db1.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getCount();
        int avail = mcursor.getInt(0);
        if((icount>0) && (avail>=amt))
        {
            String sql = "update stock set Amount = Amount - " + amt + " where Type ='" + type + "'";
            db.execSQL(sql);
            return true;
        }
        else
            return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+admin);
        db.execSQL("DROP TABLE IF EXISTS "+stock);
        onCreate(db);
    }

}


