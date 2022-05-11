package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class item_menu extends AppCompatActivity {
    public Button appetizer, food,dessert,drinks;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_menu);
        appetizer = findViewById(R.id.appetizer_bttn);
        food = findViewById(R.id.food_bttn);
        dessert = findViewById(R.id.dessert_bttn);
        drinks = findViewById(R.id.drinks_bttn);

        food.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(item_menu.this, CustomerImageRecycler.class);
                startActivity(intent);
            }
        });
        drinks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent3 = new Intent(item_menu.this, foodmenu.class);
                startActivity(intent3);
            }
        });
        dessert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent4 = new Intent(item_menu.this, foodmenu.class);
                startActivity(intent4);
            }
        });
        appetizer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent2= new Intent(item_menu.this, foodmenu.class);
                startActivity(intent2);
            }
        });
    }
}