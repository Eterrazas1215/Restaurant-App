package com.example.restaurantapp;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.ViewHolder> {
    //initialize variables
    private List<DessertData> dataList;
    private Activity context;
    private RoomDB database;
    EditText editText, priceText, descText;


    //create constructor
    public DessertAdapter(Activity context, List<DessertData> dataList)
    {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dessert_list_row_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //initialize main data
        DessertData data = dataList.get(position);
        //Initialize database
        database = RoomDB.getInstance(context);
        //Set text on text view
        String foodItem = data.getText()+" "+ data.getPrice()+" "+ data.getDescription();
        holder.textView.setText(foodItem);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize main data
                DessertData d = dataList.get(holder.getAdapterPosition());
                //Get ID
                int sID = d.getId();
                //Get text
                String sText = d.getText();
                String sPrice = d.getPrice();
                String sDesc = d.getDescription();

                //create dialog
                Dialog dialog = new Dialog(context);
                //set content view
                dialog.setContentView(R.layout.dialog_update);
                //initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //Set Layout
                dialog.getWindow().setLayout(width,height);
                //show dialog
                dialog.show();

                //initialize and assign variable
                editText = dialog.findViewById(R.id.dessert_name);
                priceText = dialog.findViewById(R.id.dessert_edit_price);
                descText = dialog.findViewById(R.id.dessert_edit_description);
                Button btnUpdate = dialog.findViewById(R.id.dessert_btn_update);

                //set text on edit text
                editText.setText(sText);
                priceText.setText(sPrice);
                descText.setText(sDesc);

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dismiss dialog
                        dialog.dismiss();
                        //Get updated text from edit text
                        String uText = editText.getText().toString().trim();
                        String uPrice = priceText.getText().toString().trim();
                        String uDesc = descText.getText().toString().trim();

                        //fill alt data list

                        //Update text in database
                        database.dessertDao().update(sID,uText,uPrice,uDesc);
                        //Notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.dessertDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize main data
                DessertData d = dataList.get(holder.getAdapterPosition());
                //delete text from database
                database.dessertDao().delete(d);
                //notify when data is deleted
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());

            }
        });



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //initialize variables
        TextView textView;
        ImageView btnEdit, btnDelete, defaultImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            //defaultImage = itemView.findViewById(R.id.imageView2);
        }
    }
}
