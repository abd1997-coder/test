package com.example.application_test.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application_test.Model.Product;
import com.example.application_test.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {

    private List<Product> list_Product;
    Context mCtx ;

    public ProductAdapter( List<Product> list_Product , Context mCtx) {
        this.list_Product =  list_Product ;
        this.mCtx = mCtx ;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_item, viewGroup,false);
        return new ProductAdapter.ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder productviewHolder, int i) {

        productviewHolder.item_name.setText(list_Product.get(i).getProduct_name());
        productviewHolder.item_price.setText(String.valueOf(list_Product.get(i).getProduct_price()));
        productviewHolder.item_quantity.setText(String.valueOf(list_Product.get(i).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return list_Product.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView item_price , item_quantity ,item_name ;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_price = (TextView) itemView.findViewById(R.id.item_price);
            item_quantity = (TextView) itemView.findViewById(R.id.item_quantity);

        }

    }


}
