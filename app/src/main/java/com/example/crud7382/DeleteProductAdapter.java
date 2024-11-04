package com.example.crud7382;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DeleteProductAdapter extends ArrayAdapter<Product> {

    private final Context context;
    private final List<Product> productList;

    private DatabaseReference databaseProducts;

    public DeleteProductAdapter(Context context, List<Product> productList) {
        super(context, R.layout.item_product_delete, productList);
        this.context = context;
        this.productList = productList;
        this.databaseProducts = FirebaseDatabase.getInstance().getReference("products");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_delete, parent, false);
        }

        Product product = productList.get(position);

        TextView textNameProduct = (TextView) convertView.findViewById(R.id.textviewProductName);
        TextView textPriceProduct = (TextView) convertView.findViewById(R.id.textviewProductPrice);
        Button buttonDelete = (Button) convertView.findViewById(R.id.buttonDelete);

        textNameProduct.setText(product.getName());
        textPriceProduct.setText("$" + product.getPrice());

        buttonDelete.setOnClickListener(v -> showDeleteConfirmationDialog(product));

        return convertView;
    }

    private void showDeleteConfirmationDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Product");
        builder.setMessage("Are you sure you want to delete this product?");
        builder.setPositiveButton("Yes", (dialog, which) -> deleteProduct(product.getId()));
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void deleteProduct(String productId) {
        databaseProducts.child(productId).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                productList.removeIf(product -> product.getId().equals(productId));
                notifyDataSetChanged();

            } else {
                Toast.makeText(context, "Fiald", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
