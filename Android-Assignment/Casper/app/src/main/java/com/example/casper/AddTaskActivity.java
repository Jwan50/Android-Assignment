package com.example.casper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    public void dataAdded(View view){

        CardsDbHelper dbHelper = new CardsDbHelper(this);

        TextView taskV = findViewById(R.id.taskTxt);  // setting the text in the tasks row
        TextView deadV = findViewById(R.id.deadTxt);  // setting the deadline text in the tasks deadline row
        String task = taskV.getText().toString();
        String dead = deadV.getText().toString();
        boolean status= dbHelper.insertCard(task,dead);

        // checking the status weather the all data filled toast message, if not toast message

        if(status = true){
            Toast.makeText(this,"Data are inserted",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this,"Data are Not inserted",Toast.LENGTH_LONG).show();
        }
                    // returning back to the main activity
                    Intent myIntent = new Intent(getBaseContext(),   MainActivity.class);
                    startActivity(myIntent);
        }


    }



