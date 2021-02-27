package com.example.studentcook;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.studentcook.NetworkUtils.getResponseFromUrl;

class RecipeQueryTasks extends AsyncTask<URL, Void, String> {

    String name = null;
    String imglink = null;
    private ArrayList<String> mImgUrl = new ArrayList<>(); // #SOURCE
    private ArrayList<String> mImgNames = new ArrayList<>(); // #SOURCE

    @Override
    protected String doInBackground(URL... urls) {
        String response="";
        try {
            response = getResponseFromUrl(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(urls[0]);
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
            new MainActivity().initRecyclerView();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
