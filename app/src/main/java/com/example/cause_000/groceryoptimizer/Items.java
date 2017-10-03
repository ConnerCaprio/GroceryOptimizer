package com.example.cause_000.groceryoptimizer;

/**
 * Created by cause_000 on 8/25/2017.
 */

public class Items {

    private int _id;
    private String _itemName;

    //CONSTRUCTOR
    public Items(String itemName) {
        this._itemName = itemName;
    }

    public Items() {

    }


    //Getters and Setters
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_itemName(String _itemName) {
        this._itemName = _itemName;
    }

    public int get_id() {
        return _id;
    }

    public String get_itemName() {
        return _itemName;
    }
}
