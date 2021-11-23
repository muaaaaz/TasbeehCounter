package com.kodecaptain.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kodecaptain.myapplication.data.DataManagement;

public class EditTasbeehActivity extends AppCompatActivity {
    Tasbeeh tasbeeh;
    TextView titleText, countText;
    Button cancelBtn, updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tasbeeh);

        Intent in = getIntent();
        if (in == null)
            finish();
        tasbeeh = new Tasbeeh();
        tasbeeh.id = in.getIntExtra(Keys.ID, -1);
        tasbeeh.title = in.getStringExtra(Keys.TITLE);
        tasbeeh.count = in.getIntExtra(Keys.COUNT, -1);
        // exiting from activity if id is invalid
        if (tasbeeh.id == -1)
            finish();

        titleText = findViewById(R.id.text_title_editTasbeeh);
        countText = findViewById(R.id.text_count_editTasbeeh);
        cancelBtn = findViewById(R.id.btn_cancel_editTasbeeh);
        updateBtn = findViewById(R.id.btn_edit_editTasbeeh);

        titleText.setText(tasbeeh.title);
        countText.setText(String.valueOf(tasbeeh.count));

        cancelBtn.setOnClickListener(v -> {
            finish();
        });

        updateBtn.setOnClickListener(v -> {
            if (titleText.getText().toString().length() < 3)
                titleText.setError("Minimum length 3.");
            else {
                DataManagement dataManagement = new DataManagement(getApplicationContext());
                tasbeeh.title = titleText.getText().toString();
                tasbeeh.count = Integer.parseInt(countText.getText().toString());
                dataManagement.updateTasbeeh(tasbeeh);
                finish();
            }
        });
    }
}