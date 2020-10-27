package com.example.androcid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;



public class MainActivity extends AppCompatActivity
{

    Task t;
    TextView textView,taskview,descview;
    Button listallbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        textView = findViewById(R.id.textview);
        taskview=findViewById(R.id.textViewTask);
        descview = findViewById(R.id.textViewDesc);

        listallbt=findViewById(R.id.button);
        saveTask();
    }

    public void additem(View view)
    {
        Intent intent = new Intent(this,AddItem.class);
        startActivity(intent);
    }

    private void saveTask() {

        /*

            we created an AsyncTask to perform our operation because if we will try to
            perform the database operation in main thread it will crash our application.

        */
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {


     int task = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().countUsers();

            List<Task> taskList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().getLatest();
            if(taskList.isEmpty())
            {
                textView.setText("There is no Data first add data in list ");


            }
            else {

                listallbt.setVisibility(View.VISIBLE);
                textView.setText("Total Number of Task :- "+String.valueOf(task));
                t = taskList.get(0);
                Log.d("sumit",t.getTask());
                taskview.setText(t.getTask());
                descview.setText(t.getDesc());
            }

            return null;
            }
        }
        SaveTask st = new SaveTask();
        st.execute();

    }

    public void listall(View view)
    {

        Intent intent=new Intent(getApplicationContext(),list_all.class);
        startActivity(intent);
    }
}