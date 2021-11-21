package com.kodecaptain.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewTasbeehActivity extends AppCompatActivity {
    private static final String TAG = "AddNewTasbeehActivity";
    EditText tasbeehTitle, tasbeehCount;
    Button cancelBtn, addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_tasbeeh);

        tasbeehTitle = findViewById(R.id.text_title_addTasbeeh);
        tasbeehCount = findViewById(R.id.text_count_addTasbeeh);
        cancelBtn = findViewById(R.id.btn_cancel_addTasbeeh);
        addBtn = findViewById(R.id.btn_add_addTasbeeh);

        addBtn.setOnClickListener(v -> {
            if (tasbeehTitle.getText().toString().length() < 3) {
                tasbeehTitle.setError("Minimum length 3.");
            } else {
                Intent ni = new Intent();
                ni.putExtra(Keys.TITLE, tasbeehTitle.getText().toString());
                try {
                    ni.putExtra(Keys.COUNT, Integer.parseInt(tasbeehCount.getText().toString()));
                } catch (Exception e) {
                    ni.putExtra(Keys.COUNT, 0);
                    Log.e(TAG, "onCreate: " + e.getMessage(), null);
                }
                setResult(RESULT_OK, ni);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> finish());
    }
}