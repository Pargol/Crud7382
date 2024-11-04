package com.example.crud7382;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {

    private final Context context;
    private final List<Product> productList;

    public ProductListAdapter(Context context, List<Product> productList) {
        super(context, R.layout.item_product_update, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_update, parent, false);
        }

        Product product = productList.get(position);

        TextView textNameProduct = (TextView) convertView.findViewById(R.id.textviewProductName);
        TextView textPriceProduct = (TextView) convertView.findViewById(R.id.textviewProductPrice);
        Button buttonUpdate = (Button) convertView.findViewById(R.id.buttonUpdate);

        textNameProduct.setText(product.getName());
        textPriceProduct.setText("$" + product.getPrice());

        buttonUpdate.setOnClickListener(v ->  {
           Intent intent = new Intent(context, EditeProduct.class);
           intent.putExtra("productId", product.getId());
           intent.putExtra("productName", product.getName());
           intent.putExtra("productDescription", product.getDescription());
           intent.putExtra("productPrice", product.getPrice());
           context.startActivity(intent);
        });

        return convertView;
    }
}
