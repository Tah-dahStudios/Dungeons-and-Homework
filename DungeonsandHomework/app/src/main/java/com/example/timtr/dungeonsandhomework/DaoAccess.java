package com.example.timtr.dungeonsandhomework;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface DaoAccess {
    @Insert
    void insertNewItem(HeldItem h);

    @Query("Select qty from HeldItem where item_name =:itemName;")
    int getItemAmount(String itemName);

    @Query("Update HeldItem set qty = (qty + :amount) where item_name =:itemName;")
    void addItemAmount(String itemName, int amount);

    @Query("Insert into HeldItem values(:android_id, :itemName, :amount);")
    void addNewItem(int android_id, String itemName, int amount);


}
