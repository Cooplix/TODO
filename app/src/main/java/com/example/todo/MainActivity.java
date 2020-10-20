package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBase dbHelper;
    private  ListView all_tasks;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DataBase(this);
        all_tasks = findViewById(R.id.list_tasks);

        loadAllTasks();
    }

    private void loadAllTasks() {
        ArrayList<String> taskList = dbHelper.getAllTasks();
        if(adapter == null) {
            adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.list_tasks, taskList);
            all_tasks.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(taskList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {

    }
}