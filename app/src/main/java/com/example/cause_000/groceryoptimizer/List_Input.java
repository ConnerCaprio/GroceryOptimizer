package com.example.cause_000.groceryoptimizer;

import android.os.Bundle;
import android.widget.EditText;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class List_Input extends AppCompatActivity {

    MyDBHandler dbHandler;
    EditText connersInput;
    TextView connersText;

    /*
    public void switchActivity (View view) {
        startActivity(new Intent(List_Input.this, Current_List.class));
    }
    */
    //TODO add a clear list button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__input);
        connersInput = (EditText) findViewById(R.id.connersInput);
        connersText = (TextView) findViewById(R.id.connersText);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    //add a product to the database
    public void printDatabase() {
        String dbString = dbHandler.databseToString();
        connersText.setText(dbString);
        connersInput.setText("");
    }

    public void deleteFromList(View view) {
        String inputText = connersInput.getText().toString();
        dbHandler.deleteItem(inputText);
        printDatabase();
    }

    public void addToList(View view) {
        Items item = new Items(connersInput.getText().toString());
        dbHandler.addItem(item);
        printDatabase();
    }

    //clears the list by deleting and making new database
    public void clearDatabase(View view) {
        dbHandler.clearDatabase();
        //this.addToList(view);
        printDatabase();
    }

}
