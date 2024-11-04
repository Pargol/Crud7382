package com.example.crud7382;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditeProduct extends AppCompatActivity {

    EditText edittextUpdateName, edittextUpdateDescription, edittextUpdatePrice;

    Button buttonSaveUpdate;

    DatabaseReference database;

    String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edite_product);
        database = FirebaseDatabase.getInstance().getReference("products");
        edittextUpdateName = findViewById(R.id.edittxtUpdateName);
        edittextUpdateDescription = findViewById(R.id.edittxtUpdateDescription);
        edittextUpdatePrice = findViewById(R.id.edittxtUpdatePrice);
        buttonSaveUpdate = findViewById(R.id.Save);

        productId = getIntent().getStringExtra("productId");
        String name = getIntent().getStringExtra("productName");
        String description = getIntent().getStringExtra("productDescription");
        double price = getIntent().getDoubleExtra("productPrice", 0);

        edittextUpdateName.setText(name);
        edittextUpdateDescription.setText(description);
        edittextUpdatePrice.setText(String.valueOf(price));

        buttonSaveUpdate.setOnClickListener(v -> updateProduct());

    }

    private void updateProduct() {
        String name = edittextUpdateName.getText().toString().trim();
        String description = edittextUpdateDescription.getText().toString().trim();
        String priceStr = edittextUpdatePrice.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "please fill all the fiield", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);

        Product product = new Product(productId, name, description, price);

        database.child(productId).setValue(product);

        Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show();

        finish();

    }
}