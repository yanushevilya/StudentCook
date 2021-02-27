package com.example.studentcook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

import static com.example.studentcook.NetworkUtils.generateURL;

public class MainActivity extends AppCompatActivity {

    // SOURCE FOR GET DATA FROM RECYCLERVIEW
    private ArrayList<String> mImgUrl = new ArrayList<>(); // #SOURCE
    private ArrayList<String> mImgNames = new ArrayList<>(); // #SOURCE

    public void setmImgUrl(String s) {
        this.mImgUrl.add(s);
    }

    public void setmImgNames(String s) {
        this.mImgNames.add(s);
    }
// -------------------------------------

    private TextView etSearch;
    private Button btnSearch;
    String name = null;
    String imglink = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgNames.clear();
                mImgUrl.clear();
                URL generatedURL = generateURL(etSearch.getText().toString());

                // Запуск задачи вынесенной в отдельный поток
                new RecipeQueryTasks(MainActivity.this).execute(generatedURL);
            }
        });

//        initSource();
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