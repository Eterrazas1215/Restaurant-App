package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DessertInsert extends AppCompatActivity{
    //initialize variables
    EditText editText, price, description;
    Button btnAdd,btnReset, btnImage;
    RecyclerView recyclerView;
    List<DessertData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    DessertAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dessert_menu);
        //assign variables
        editText = findViewById(R.id.dessert_name);
        price = findViewById(R.id.dessert_price);
        description = findViewById(R.id.dessert_description);
        btnAdd = findViewById(R.id.dessert_btn_add);
//        btnReset = findViewById(R.id.btn_reset);
        btnImage = findViewById(R.id.dessert_btn_image);
        recyclerView = findViewById(R.id.dessert_recycler_view);
        //imageView = findViewById(R.id.imageView2);

        //initialize database
        database = RoomDB.getInstance(this);
        //Store database value in data list
        dataList = database.dessertDao().getAll();
        //initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        //Set Layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        //initialize adapter
        adapter = new DessertAdapter(DessertInsert.this,dataList);
        //set adapter
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get string from edit text
                String sText = editText.getText().toString().trim();
                String sPrice = price.getText().toString().trim();
                String sDescription = description.getText().toString().trim();

                //check condition
                if(!sText.equals(""))
                {
                    //When text is not empty
                    //Initialize main data
                    DessertData data = new DessertData();
                    //set tet on Main data
                    //sText = sText+ " " + sPrice+ " " + sDescription;
                    data.setText(sText);
                    data.setPrice(sPrice);
                    data.setDescription(sDescription);

                    //insert text in database
                    database.dessertDao().insert(data);
                    //clear edit text
                    editText.setText("");
                    price.setText("");
                    description.setText("");
                    //Notify when data inserted
                    dataList.clear();
                    dataList.addAll(database.dessertDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Decline all data from database
//                database.mainDao().reset(dataList);
//                //Notify when all deleted
//                dataList.clear();
//                dataList.addAll(database.mainDao().getAll());
//                adapter.notifyDataSetChanged();
//            }
//        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DessertInsert.this, UploadImage.class);
                startActivity(intent);
            }
        });


    }

}