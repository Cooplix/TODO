package com.example.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
        getMenuInflater().inflate(R.menu.menu, menu);

        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_new_task) {
            final EditText userTaskGet = new EditText(this);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Add new task")
                    .setMessage("What are you wont add?")
                    .setView(userTaskGet).
                            setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String task = String.valueOf(userTaskGet.getText());
                                    dbHelper.insertData(task);
                                    loadAllTasks();
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .create();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}