package com.example.application_test.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.application_test.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class product_database extends SQLiteOpenHelper {


    public static final String DBname = "product_data.db" ;

    public product_database(Context context) {
        super(context, DBname, null, 3 );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE product_data (id_product INTEGER PRIMARY KEY AUTOINCREMENT ,product_name TEXT , product_quantity INTEGER , product_price INTEGER )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS product_data");
    }

    public List<Product> getProducts()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null ;
        List<Product> result = new ArrayList<>();

        try {
            c = db.rawQuery("SELECT * FROM product_data ", null);
            if(c.moveToFirst())
            {
                do
                {
                    result.add(new Product(c.getString(c.getColumnIndex("product_name")),
                            c.getInt(c.getColumnIndex("product_quantity")),
                            c.getInt(c.getColumnIndex("product_price"))
                    ));
                }while (c.moveToNext());

            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(null != c)
                c.close();
            if(null != db)
                db.close();
        }

        return result;
    }



    public void addToCart( String product_name , int product_quantity , int product_price)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("product_name" ,product_name);
        contentValues.put("product_quantity" , product_quantity);
        contentValues.put("product_price" , product_price);

        db.insert("product_data",null , contentValues);
        db.close();
    }


    public void CleanCart()
    {
        SQLiteDatabase db =  getWritableDatabase();
        db.execSQL("DELETE FROM product_data ");
        db.close();




    }

}

