package com.example.studentcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class CheckboxRecipes extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.studentcook.MESSAGE";

    Button btnInCheckBox;
    CheckBox cbOnions;
    CheckBox cbGarlic;
    String [] components;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_recipes);

        btnInCheckBox = findViewById(R.id.btnInCheckBox);
        cbOnions = findViewById(R.id.cbOnions);
        cbGarlic = findViewById(R.id.cbGarlic);

        btnInCheckBox.setOnClickListener(v -> {
            startMainActivity();
        });
    }

    private void startMainActivity() {
        components = new String[]{cbOnions.getText().toString(), cbGarlic.getText().toString()};
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, components);
        System.out.println(intent.getStringArrayExtra(EXTRA_MESSAGE));
        startActivity(intent);
//        finish();
    }

}