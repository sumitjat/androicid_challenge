package com.example.androcid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class list_all extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        another();
    }

    // Learning RXjava ( first time )
    private void another()
    {
        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().getAll().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<List<Task>>() {
                    @Override
                    public void onSuccess(List<Task> users)
                    {
                        TaskAdapter adapter = new TaskAdapter(list_all.this, users);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(list_all.this, "Empty data",Toast.LENGTH_LONG).show();
                    }
                });

    }
}