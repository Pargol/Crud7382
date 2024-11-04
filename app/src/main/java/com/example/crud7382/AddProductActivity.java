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

public class AddProductActivity extends AppCompatActivity {

    EditText editTextName, editTextDescription, editTextPrice;
    Button buttonSave;
    DatabaseReference database, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        editTextName = findViewById(R.id.edittxtName);
        editTextDescription = findViewById(R.id.edittxtDescription);
        editTextPrice = findViewById(R.id.edittxtPrice);

        buttonSave = findViewById(R.id.AddProduct);
        database = FirebaseDatabase.getInstance().getReference("products");
        reference2 = FirebaseDatabase.getInstance().getReference("Transaction");
        buttonSave.setOnClickListener(v -> saveProduct());

    }

    private void saveProduct() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();
        double dPrice = Double.parseDouble(price);

        if (name.isEmpty()  || description.isEmpty() || price.isEmpty()) {
          Toast.makeText(this, "Please fill all the Field", Toast.LENGTH_SHORT).show();
          return;
        }
        String id = database.push().getKey();
        Product product = new Product(id, name, description, dPrice);

        if (id!= null) {
            database.child(id).setValue(product);
            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
            editTextPrice.setText("");
            editTextDescription.setText("");
        }
    }

}