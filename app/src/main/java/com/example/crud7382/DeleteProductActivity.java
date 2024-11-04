package com.example.crud7382;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteProductActivity extends AppCompatActivity {

    ListView listView;
    DeleteProductAdapter adapter;
    ArrayList<Product> productList;

    DatabaseReference databaseProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        listView = findViewById(R.id.listviewProduct);
        databaseProducts = FirebaseDatabase.getInstance().getReference("products");

        productList = new ArrayList<>();
        adapter = new DeleteProductAdapter(this, productList);

        listView.setAdapter(adapter);

        loadProducts();
    }

    private void loadProducts() {
        databaseProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Product product = postSnapshot.getValue(Product.class);
                    if (product!= null) {
                        productList.add(product);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Handle possible errors
            }
        });
    }
}