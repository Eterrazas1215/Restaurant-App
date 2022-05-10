package com.example.restaurantapp;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder>
{
    List arrayList;
    Context context;
    private RoomDB db;

    public ImageAdapter(Context context, List arrayList)
    {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_row,parent,false);
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
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageData d = (ImageData) arrayList.get(holder.getAdapterPosition());
                db.imageDao().deleteImage(d);
                int pos = holder.getAdapterPosition();
                arrayList.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, arrayList.size());

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
        Button btnDelete, btnReset;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
            btnDelete = itemView.findViewById(R.id.btn_image_delete);
        }
    }

}
