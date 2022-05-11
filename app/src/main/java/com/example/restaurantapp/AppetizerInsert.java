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

public class AppetizerInsert extends AppCompatActivity{
    //initialize variables
    EditText editText, price, description;
    Button btnAdd,btnReset, btnImage, btnItems;
    RecyclerView recyclerView;
    List<AppetizerData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    AppetizerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appetizer_menu);
        //assign variables
        editText = findViewById(R.id.appetizer_name);
        price = findViewById(R.id.appetizer_price);
        description = findViewById(R.id.appetizer_description);
        btnAdd = findViewById(R.id.appetizer_btn_add);
//        btnReset = findViewById(R.id.btn_reset);
        btnImage = findViewById(R.id.appetizer_btn_image);
        btnItems = findViewById(R.id.btn_items);
        recyclerView = findViewById(R.id.appetizer_recycler_view);
        //imageView = findViewById(R.id.imageView2);

        //initialize database
        database = RoomDB.getInstance(this);
        //Store database value in data list
        dataList = database.appetizerDao().getAll();
        //initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        //Set Layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        //initialize adapter
        adapter = new AppetizerAdapter(AppetizerInsert.this,dataList);
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
                    AppetizerData data = new AppetizerData();
                    //set tet on Main data
                    //sText = sText+ " " + sPrice+ " " + sDescription;
                    data.setText(sText);
                    data.setPrice(sPrice);
                    data.setDescription(sDescription);

                    //insert text in database
                    database.appetizerDao().insert(data);
                    //clear edit text
                    editText.setText("");
                    price.setText("");
                    description.setText("");
                    //Notify when data inserted
                    dataList.clear();
                    dataList.addAll(database.appetizerDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });


        btnItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppetizerInsert.this, ImageRecycler.class);
                startActivity(intent);
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
                Intent intent = new Intent(AppetizerInsert.this, UploadImage.class);
                startActivity(intent);
            }
        });


    }

}