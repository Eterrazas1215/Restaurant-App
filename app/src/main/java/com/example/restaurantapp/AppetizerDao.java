package com.example.restaurantapp;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppetizerDao {
    //insert query
    @Insert(onConflict = REPLACE)
    void insert(AppetizerData appetizerData);

    //Delete query
    @Delete
    void delete(AppetizerData appetizerData);

    //Delete all Query
    @Delete
    void reset(List<AppetizerData> appetizerData);

    //Update Query
    @Query("UPDATE Appetizer SET text = :sText, price = :sPrice, description = :sDesc WHERE ID = :sID ")
    void update(int sID, String sText, String sPrice, String sDesc);

    //Get All data query
    @Query("SELECT * FROM Appetizer")
    List<AppetizerData> getAll();

}
