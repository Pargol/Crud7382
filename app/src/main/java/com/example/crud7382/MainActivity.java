package com.example.crud7382;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button addPrdoductButton , viewProductsButton , updateProductButton , deleteProductButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPrdoductButton = findViewById(R.id.addNewProduct);
        viewProductsButton = findViewById(R.id.viewAllProduct);
        updateProductButton = findViewById(R.id.updateProduct);
        deleteProductButton = findViewById(R.id.deleteProduct);


        addPrdoductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

        viewProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewProductActivity.class);
                startActivity(intent);
            }
        });


        updateProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateProductActivity.class);
                startActivity(intent);
            }
        });

        deleteProductButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteProductActivity.class);
                startActivity(intent);
                }
            });

    }
}