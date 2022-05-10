package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseMenuSelection extends AppCompatActivity {
    Button btn_food, btn_drinks, btn_dessert, btn_appetizers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_menu_selection_layout);

        btn_food = findViewById(R.id.db_food_btn);
        btn_dessert = findViewById(R.id.db_dessert_btn);
        btn_appetizers = findViewById(R.id.db_appetizer_btn);
        btn_drinks =  findViewById(R.id.db_drinks_btn);

        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseMenuSelection.this, NewInsertMenu.class);
                startActivity(intent);
            }
        });

        btn_dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseMenuSelection.this, DessertInsert.class);
                startActivity(intent);
            }
        });

        btn_appetizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseMenuSelection.this, AppetizerInsert.class);
                startActivity(intent);
            }
        });

        btn_drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseMenuSelection.this, DrinksInsert.class);
                startActivity(intent);
            }
        });


    }



}
