package com.example.studentcook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.studentcook.NetworkUtils.generateURL;
import static com.example.studentcook.NetworkUtils.getResponseFromUrl;

public class MainActivity extends AppCompatActivity {

    // SOURCE FOR GET DATA FROM RECYCLERVIEW
    private ArrayList<String> mImgUrl = new ArrayList<>(); // #SOURCE
    private ArrayList<String> mImgNames = new ArrayList<>(); // #SOURCE
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
                URL generatedURL = generateURL(etSearch.getText().toString());

                // Запуск задачи вынесенной в отдельный поток
                new RecipeQueryTasks().execute(generatedURL);
            }
        });

//        initSource();
    }

    // КЛАСС ДЛЯ ВЫПОЛНЕНИЯ ЗАДАЧИ В ОТДЕЛЬНОМ ПОТОКЕ
    // выполняем запрос в Интернет и получаем ответ в виде JSON
    class RecipeQueryTasks extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response="";
            try {
                response = getResponseFromUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray jsonArray = jsonResponse.getJSONArray("results");
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject recipes = jsonArray.getJSONObject(i);
                    name = recipes.getString("title");
//                    ingredients = recipes.getString("ingredients");
                    imglink = recipes.getString("thumbnail");
                    mImgNames.add(name);
                    mImgUrl.add(imglink);
                }
                initRecyclerView();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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