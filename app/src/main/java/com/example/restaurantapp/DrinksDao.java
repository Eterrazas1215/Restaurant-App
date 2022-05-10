package com.example.restaurantapp;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DrinksDao {
    //insert query
    @Insert(onConflict = REPLACE)
    void insert(DrinksData drinksData);

    //Delete query
    @Delete
    void delete(DrinksData drinksData);

    //Delete all Query
    @Delete
    void reset(List<DrinksData> drinksData);

    //Update Query
    @Query("UPDATE Drinks SET text = :sText, price = :sPrice, description = :sDesc WHERE ID = :sID ")
    void update(int sID, String sText, String sPrice, String sDesc);

    //Get All data query
    @Query("SELECT * FROM Drinks")
    List<DrinksData> getAll();

}
