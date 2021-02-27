package com.example.studentcook;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.studentcook.NetworkUtils.getResponseFromUrl;

// КЛАСС ДЛЯ ВЫПОЛНЕНИЯ ЗАДАЧИ В ОТДЕЛЬНОМ ПОТОКЕ
// выполняем запрос в Интернет и получаем ответ в виде JSON
class RecipeQueryTasks extends AsyncTask<URL, Void, String> {

    private final MainActivity mainActivity;
    private final CheckboxRecipes checkboxRecipes;


    public RecipeQueryTasks(MainActivity mainActivity, CheckboxRecipes checkboxRecipes) {
        this.mainActivity = mainActivity;
        this.checkboxRecipes = checkboxRecipes;
    }

    @Override
    protected String doInBackground(URL... urls) {
        String response="";
        try {
            response = getResponseFromUrl(urls[0]);
            System.out.println(response.toString());
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
                mainActivity.name = recipes.getString("title");
//                    ingredients = recipes.getString("ingredients");
                mainActivity.imglink = recipes.getString("thumbnail");
                mainActivity.setmImgNames(mainActivity.name);
                mainActivity.setmImgUrl(mainActivity.imglink);
            }
            mainActivity.initRecyclerView();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
