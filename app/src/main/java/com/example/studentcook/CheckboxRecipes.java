package com.example.studentcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import java.net.URL;

import static com.example.studentcook.NetworkUtils.generateURL;
import static com.example.studentcook.NetworkUtils.generateURLCb;

public class CheckboxRecipes extends AppCompatActivity {

    Button btnInCheckBox;
    CheckBox cbOnions;
    CheckBox cbGarlic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_recipes);

        btnInCheckBox = findViewById(R.id.btnInCheckBox);
        cbOnions = findViewById(R.id.cbOnions);
        cbGarlic = findViewById(R.id.cbGarlic);
        String [] components = {cbOnions.getText().toString(), cbGarlic.getText().toString()};
        MainActivity mainActivity = new MainActivity();

        btnInCheckBox.setOnClickListener(v -> {
            mainActivity.getmImgNames().clear();
            mainActivity.getmImgUrl().clear();

            URL generatedURL = generateURLCb(components);

            // Запуск задачи вынесенной в отдельный поток
            new RecipeQueryTasks().execute(generatedURL);

            startMainActivity();
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}