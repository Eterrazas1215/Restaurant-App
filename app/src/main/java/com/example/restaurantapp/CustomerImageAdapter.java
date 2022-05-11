package com.example.restaurantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerImageAdapter extends RecyclerView.Adapter<CustomerImageAdapter.MyViewHolder>
{
    List arrayList;
    Context context;
    private RoomDB db;

    public CustomerImageAdapter(Context context, List arrayList)
    {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customer_menu_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        ImageData images = (ImageData) arrayList.get(position);
        db = RoomDB.getInstance(context);
        holder.imageView.setImageBitmap(ImageBitmapString.getBitmapFromStr(images.getImages()));
        images.setImages(images.getImages());


        //btn delete
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //add to order code maybe recycler view

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        Button btnAdd;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_customer);
            btnAdd = itemView.findViewById(R.id.btn_image_add);
        }
    }

}
