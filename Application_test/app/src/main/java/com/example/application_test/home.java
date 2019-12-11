package com.example.application_test;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application_test.Adapter.ProductAdapter;
import com.example.application_test.Database.product_database;
import com.example.application_test.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Product> list_prodict;
    product_database data = new product_database(this);
    ProductAdapter adapter;

    EditText product_name, product_quantity, product_price;
    TextView Total;
    Button Button_product, Button_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list_prodict = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        product_name = (EditText) findViewById(R.id.product_name);
        product_quantity = (EditText) findViewById(R.id.product_quantity);
        product_price = (EditText) findViewById(R.id.product_price);

        Total = (TextView) findViewById(R.id.total);

        Button_product = (Button) findViewById(R.id.Button_product);
        Button_clear = (Button) findViewById(R.id.Button_clear);

        loadListProduct();

        Button_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!product_name.getText().toString().isEmpty() && !product_quantity.getText().toString().isEmpty() && !product_price.getText().toString().isEmpty()) {
                    data.addToCart(product_name.getText().toString(), Integer.valueOf(product_quantity.getText().toString()), Integer.valueOf(product_price.getText().toString()));
                    loadListProduct();
                    product_price.setText("");
                    product_quantity.setText("");
                    product_name.setText("");
                } else
                    Toast.makeText(home.this, "كل المعلومات مطلوبة", Toast.LENGTH_SHORT).show();
            }
        });

        Button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.CleanCart();
                loadListProduct();
            }
        });
    }

    private void loadListProduct() {

        final product_database database = new product_database(this);
        list_prodict = database.getProducts();

        adapter = new ProductAdapter(list_prodict, home.this);

        recyclerView.setAdapter(adapter);


        int total = 0;

        for (Product product : list_prodict) {
            total += ((product.getProduct_price()));
        }

        Total.setText(String.valueOf(total));
    }

}
