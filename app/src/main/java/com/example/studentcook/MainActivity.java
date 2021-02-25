package com.example.studentcook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // SOURCE FOR GET DATA FROM RECYCLERVIEW
    private ArrayList<String> mImgUrl = new ArrayList<>(); // #SOURCE
    private ArrayList<String> mImgNames = new ArrayList<>(); // #SOURCE
    // -------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSource();
    }

    // #SOURCE
    private void initSource() {
        mImgNames.add("Baked Omelet With Broccoli &amp; Tomato");
        mImgUrl.add("http://img.recipepuppy.com/123889.jpg");
        mImgNames.add("Mild Curry Omelet");
        mImgUrl.add("");
        mImgNames.add("Light Italian Feta Omelet");
        mImgUrl.add("http://img.recipepuppy.com/36027.jpg");
        mImgNames.add("Awesome Cajun Omelet");
        mImgUrl.add("");
        mImgNames.add("Blue Cheese Omelet");
        mImgUrl.add("http://img.recipepuppy.com/177663.jpg");
        mImgNames.add("Canadian Bacon Omelet");
        mImgUrl.add("");
        mImgNames.add("Cauliflower and Feta Omelet");
        mImgUrl.add("");
        mImgNames.add("Chef Joey's Low Cal Breakfast Omelet");
        mImgUrl.add("");
        mImgNames.add("Vegan Omelet For One Recipe");
        mImgUrl.add("http://img.re cipepuppy.com/328574.jpg");
        mImgNames.add("Tomato Omelet Recipe");
        mImgUrl.add("");

        initRecyclerView();
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, mImgUrl, mImgNames); // #SOURCE
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}