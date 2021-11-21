package com.kodecaptain.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kodecaptain.myapplication.data.DataManagement;

public class CounterActivity extends AppCompatActivity {
    private static final String TAG = "CounterActivity";
    TextView titleText, counterText;
    Button resetBtn, backBtn;
    int count, currentID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        titleText = findViewById(R.id.text_title);
        counterText = findViewById(R.id.text_counter);
        resetBtn = findViewById(R.id.btn_reset);
        backBtn = findViewById(R.id.btn_back);

        // setting data from intent
        Intent in = getIntent();
        if (in == null) {
            finish();
        }
        currentID = in.getIntExtra(Keys.ID, -1);
        titleText.setText(in.getStringExtra(Keys.TITLE));
        count = in.getIntExtra(Keys.COUNT, -1);
        counterText.setText(String.valueOf(count));


        counterText.setOnClickListener(v -> {
            ++count;
            counterText.setText(String.valueOf(count));
        });

        resetBtn.setOnClickListener(v -> {
            count = 0;
            counterText.setText(String.valueOf(count));
        });

        backBtn.setOnClickListener(v -> {
            saveCurrentDataToFile();
            finish();
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Keys.ID, currentID);
        outState.putString(Keys.TITLE, titleText.getText().toString());
        outState.putInt(Keys.COUNT, count);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentID = savedInstanceState.getInt(Keys.ID);

        String savedTitle = savedInstanceState.getString(Keys.TITLE);
        titleText.setText(savedTitle);

        count = savedInstanceState.getInt(Keys.COUNT);
        counterText.setText(String.valueOf(count));
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveCurrentDataToFile();
    }

    protected void saveCurrentDataToFile() {
        DataManagement dataManagement = new DataManagement(getApplicationContext());
        dataManagement.updateTasbeeh(currentID, count);
    }
}