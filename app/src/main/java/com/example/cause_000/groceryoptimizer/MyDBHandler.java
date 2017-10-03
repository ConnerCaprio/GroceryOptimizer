package com.example.cause_000.groceryoptimizer;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASW_VERSION = 1;
    private static final String DATABSE_NAME = "grocery_List.db"; //.db tells android file type
    private static final String TABLE_ITEMS = "items"; //is the title of the table?
    private static final String COLUMN_ID = "_id"; //is the column for the id of the item in this case the number it was inputed as (later put in id for location in store)
    private static final String COLUMN_ITEMNAME = "itemName"; //is the column for the id of the item in this case the grocery item ie: bagels


    // contructor is for super class behind the scenes shit
    //context and factory is behind the scenes stuff
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABSE_NAME, factory, DATABASW_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ITEMS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ITEMNAME + " TEXT " +
                ")";

        db.execSQL(query); //makes table made above on create
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_ITEMS);
        onCreate(db); //makes new table
    }

    //add new row to database
    public void addItem(Items item) {
        ContentValues values = new ContentValues(); //allows inserting stuff really easy
        values.put(COLUMN_ITEMNAME, item.get_itemName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ITEMS, null, values); //inserts new item into table
        db.close(); //closes file to let android know it can use it again
    }

    //delete an item from the database
    public void deleteItem(String itemName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEMNAME + "=\"" + itemName + "\";"); //deletes if the name is the same name
    }

    //prints out the database as a string
    public String databseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_ITEMS + " WHERE 1";

        //cursor points to a location in your results
        //easier for android to manage shit
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst(); //moves cursor to first row in your results

        while (!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex("itemName")) != null){ //makes sure not in last row
                dbString += c.getString(c.getColumnIndex("itemName"));
                dbString += "\n"; //makes an item on different lines
            }
            c.moveToNext();
        }
        db.close();
        return dbString;

    }

    public void clearDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ITEMS, null, null);
    }
}
