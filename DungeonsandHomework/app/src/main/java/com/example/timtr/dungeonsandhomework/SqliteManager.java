package com.example.timtr.dungeonsandhomework;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteCursor;
import android.database.Cursor;
import android.graphics.drawable.AnimatedStateListDrawable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import models.Item;

/**
 * Created by Alex Chen on 2017-11-18.
 */

public class SqliteManager {
    /**
     * Class for interacting with the database to manage items.
     */
    dbHelper myHelper;


    public SqliteManager(Context context)
    {
        myHelper = new dbHelper(context);
    }

    public int getNumItems() {
        int qty = 0;
        String query = "SELECT COUNT(*) FROM " + myHelper.ITEM_TABLE + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToNext()){
            qty = cursor.getInt(0);
        }

        // the count will count the "coin" item as well, so we return the qty - 1
        return qty - 1;
    }

    public int getItemQty(int itemID) {
        // returns the quantity of the item owned
        int qty = 0;
        String query = "SELECT " + myHelper.ITEM_QTY +" FROM " + myHelper.ITEM_HELD_TABLE + " WHERE " +
                myHelper.Android_IID + " = " + Integer.toString(itemID) + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            qty = cursor.getInt(0);
        }
        // no else is needed, if this move fails, the item is not found, so the qty will be 0

        db.close();
        return qty;
    }

    public String getItemName(int itemID) {
        // returns the name of the item
        String itemName = "";
        String query = "SELECT " + myHelper.ITEM_NAME +" FROM " + myHelper.ITEM_TABLE + " WHERE " +
                myHelper.Android_IID + " = " + Integer.toString(itemID) + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            itemName = cursor.getString(0);
        }
        // no else is needed, if this move fails, the item is not found, so the name will be blank

        db.close();
        return itemName;
    }

    public int getItemPrice(int itemID) {
        // returns the price of the item
        int price = -1;
        String query = "SELECT " + myHelper.ITEM_PRICE +" FROM " + myHelper.ITEM_TABLE + " WHERE " +
                myHelper.Android_IID + " = " + Integer.toString(itemID) + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            price = cursor.getInt(0);
        }
        // no else is needed, if this move fails, the item is not found, price will be -1

        db.close();
        return price;
    }

    public Item getItem(int itemID){
        Item item = new Item(getItemName(itemID), getItemPrice(itemID));
        return item;
    }

    public int useItem(int itemID){
        // uses the item specified by itemID and updates the qty in the database
        // returns the quantity remaining or
        // -1 if item was not used because there is none owned
        int qty = getItemQty(itemID);

        if (qty <= 0){
            return -1;
        }

        else{ //qty > 0, so there is one held to be used
            String query = "";
            SQLiteDatabase db = this.myHelper.getWritableDatabase();
            qty--;
            // TODO: Update the value of qty in the database
            ContentValues values = new ContentValues();
            values.put(myHelper.ITEM_QTY, qty);
            String[] whereArgs= {Integer.toString(itemID)};
            String whereClause = myHelper.Android_IID + " = ?";
            db.update(myHelper.ITEM_HELD_TABLE, values, whereClause, whereArgs);

            db.close();
        }

        return qty;
    }

    public int getNumBosses() {
        int qty = 0;
        String query = "SELECT COUNT(*) FROM " + myHelper.BOSS_TABLE + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToNext()){
            qty = cursor.getInt(0);
        }

        return qty;
    }

    public Boss getBoss(int bossID) {
        Boss boss = new Boss(getBossHp(bossID), getBossGold(bossID), getBossRegen(bossID), getBossName(bossID));
        return boss;
    }

    public String getBossName(int bossID){
        String bossName = "";
        String query = "SELECT " + myHelper.BOSS_NAME +" FROM " + myHelper.BOSS_TABLE + " WHERE " +
                myHelper.Android_IID + " = " + Integer.toString(bossID) + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            bossName = cursor.getString(0);
        }

        db.close();
        return bossName;
    }

    public int getBossHp(int bossID) {
        int hp = 0;
        String query = "SELECT " + myHelper.BOSS_HP +" FROM " + myHelper.BOSS_TABLE + " WHERE " +
                myHelper.Android_IID + " = " + Integer.toString(bossID) + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            hp = cursor.getInt(0);
        }

        db.close();
        return hp;
    }

    public int getBossRegen(int bossID) {
        int regen = 0;
        String query = "SELECT " + myHelper.BOSS_REGEN +" FROM " + myHelper.BOSS_TABLE + " WHERE " +
                myHelper.Android_IID + " = " + Integer.toString(bossID) + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            regen = cursor.getInt(0);
        }

        db.close();
        return regen;
    }

    public int getBossGold(int bossID) {
        int gold = 0;
        String query = "SELECT " + myHelper.BOSS_GOLD +" FROM " + myHelper.BOSS_TABLE + " WHERE " +
                myHelper.Android_IID + " = " + Integer.toString(bossID) + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            gold = cursor.getInt(0);
        }

        db.close();
        return gold;
    }


    static class dbHelper extends SQLiteAssetHelper {
//        private static final String DATABASE_NAME = "GameInfo";    // Database Name
        private static final String Android_IID = "_id";     // Column I (for android)

        // items table(_id, iid, name, price) TODO
        private static final String ITEM_TABLE = "items";
        private static final String ITEM_ID = "iid"; // Column II (primary key)
        private static final String ITEM_NAME = "name";    //Column III (Name of item shown)
        private static final String ITEM_PRICE = "price";
//        private static final String CREATE_ITEM_TABLE = "CREATE TABLE IF NOT EXISTS " + ITEM_TABLE +" (" +
//                Android_IID + "INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEM_ID + " TEXT, " + ITEM_NAME +
//                " TEXT ," + ITEM_PRICE + " INTEGER, " +
//                "UNIQUE(" + ITEM_ID +");";

        // boss table(_id, name, hp)
        private static final String BOSS_TABLE = "bosses";
        private static final String BOSS_NAME = "name";
        private static final String BOSS_HP = "hp";
        private static final String BOSS_GOLD = "coins";
        private static final String BOSS_REGEN = "regen";


        // items held table (_id, iid, name, qty)
        private static final String ITEM_HELD_TABLE = "held";   // Table Name
        private static final String ITEM_QTY = "qty";    //Column IV
        private static final String CREATE_HELD_TABLE = "CREATE TABLE IF NOT EXISTS" + ITEM_HELD_TABLE +
                " (" + Android_IID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEM_ID + " TEXT, " + ITEM_NAME +
                " TEXT ," + ITEM_QTY + " INTEGER, " +
                "UNIQUE(" + ITEM_ID +"), FOREIGN KEY;"; //TODO: finish
        private static final String DROP_HELD_TABLE = "DROP TABLE IF EXISTS " + ITEM_HELD_TABLE;
//        private Context context;
//
//        public dbHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_Version);
//            this.context = context;
//        }
//
//        public void onCreate(SQLiteDatabase db) {
//
//            try {
//                db.execSQL("PRAGMA foreign_keys=1;"); // enforce foreign key constraints
//                db.execSQL(CREATE_HELD_TABLE); //create table for owned items if it doesn't exist
//                db.execSQL(CREATE_ITEM_TABLE); //creates item table if it doesn't exist
//
//            } catch (Exception e) {
//                ;
//            }
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            try {
////            Message.message(context,"OnUpgrade");
//                db.execSQL(DROP_HELD_TABLE);
//                onCreate(db);
//            } catch (Exception e) {
////            Message.message(context,""+e);
//            }
//        }


        private static final String DATABASE_NAME = "gameinfo.db";
        private static final int DATABASE_VERSION = 4;

        private Context context;

        public dbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


    }
}
