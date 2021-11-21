package com.kodecaptain.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kodecaptain.myapplication.data.DataManagement;

public class HomeActivity extends AppCompatActivity {
    private static final int REQ_CODE_TASBEEH_DATA = 1001;
    ImageButton addTasbeehBtn;
    RecyclerView tasbeehRView;
    DataManagement dataManagement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataManagement = new DataManagement(getApplicationContext());

        addTasbeehBtn = findViewById(R.id.btn_add_tasbeeh_home);
        tasbeehRView = findViewById(R.id.recycler_view_tasbeeh);
        tasbeehRView.setLayoutManager(new LinearLayoutManager(this));
        tasbeehRView.setAdapter(new TasbeehAdaptor(getApplicationContext()));

        addTasbeehBtn.setOnClickListener(v -> {
            Intent ni = new Intent(this, AddNewTasbeehActivity.class);
            startActivityForResult(ni, REQ_CODE_TASBEEH_DATA);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQ_CODE_TASBEEH_DATA) {
                String title = data.getStringExtra(Keys.TITLE);
                int count = data.getIntExtra(Keys.COUNT, 0);
                Tasbeeh tasbeeh = new Tasbeeh(title, count);
                dataManagement.addNewTasbeeh(tasbeeh);
                update();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    private void update() {
        tasbeehRView.setAdapter(new TasbeehAdaptor(getApplicationContext()));
    }
}