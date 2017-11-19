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

/**
 * Created by Alex Chen on 2017-11-18.
 */

public class ItemManager {
    /**
     * Class for interacting with the database to manage items.
     */
    dbHelper myHelper;


    public ItemManager(Context context)
    {
        myHelper = new dbHelper(context);
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
        private static final int DATABASE_VERSION = 1;

        private Context context;

        public dbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //TODO
        }
    }
}
