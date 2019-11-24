package com.example.casper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class MustDoActivity extends AppCompatActivity {

    private ArrayList<String> texts = new ArrayList<>();
    private ArrayList<Integer> images = new ArrayList<>();
    private CardsDbHelper cardsDbHelper = new CardsDbHelper(this);
    ArrayList<Tasks> tasks;

    // initializing recycle View for multiple cards
    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recycler);
        final Adapter adapter = new Adapter(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Setting ClickListener for recyclerView
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                cardsDbHelper.deleteCard(tasks.get(position).getText());
                tasks.get(position).select();
                adapter.notifyItemChanged(position);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_must_do);
        tasks = new ArrayList<>();
        initRecycler();
        populate();

    }

    private void populate(){


        // SQLiteDatabase db = cardsDbHelper.getReadableDatabase();

        Cursor res = cardsDbHelper.getAllCards();
        if (res.getCount() ==0){
            return;
        }else {
            while (res.moveToNext()){
                tasks.add(new Tasks(R.drawable.done, res.getString(1)));
            }
        }

    }



}
