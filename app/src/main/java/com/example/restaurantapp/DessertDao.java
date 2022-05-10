package com.example.restaurantapp;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DessertDao {
    //insert query
    @Insert(onConflict = REPLACE)
    void insert(DessertData dessertData);

    //Delete query
    @Delete
    void delete(DessertData dessertData);

    //Delete all Query
    @Delete
    void reset(List<DessertData> dessertData);

    //Update Query
    @Query("UPDATE Dessert SET text = :sText, price = :sPrice, description = :sDesc WHERE ID = :sID ")
    void update(int sID, String sText, String sPrice, String sDesc);

    //Get All data query
    @Query("SELECT * FROM Dessert")
    List<DessertData> getAll();

}
