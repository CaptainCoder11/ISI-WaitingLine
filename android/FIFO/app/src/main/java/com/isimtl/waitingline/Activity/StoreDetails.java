package com.isimtl.waitingline.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waitbuddy.R;

import java.util.ArrayList;
import java.util.List;

public class StoreDetails extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.waitbuddy.R.layout.store_details);

        ArrayList<ExampleName> exampleList = new ArrayList<>();
        exampleList.add(new ExampleName(R.drawable.abc, "Walmart","Open : 10:00 AM","Close : 10:00 PM"));
        exampleList.add(new ExampleName(R.drawable.abc, "Costco","Open : 8:00 AM","Close : 8:00 PM"));
        exampleList.add(new ExampleName(R.drawable.abc, "Dollarama","Open : 10:00 AM","Close : 10:00 PM"));
        exampleList.add(new ExampleName(R.drawable.abc, "Tim","Open : 8:00 AM","Close : 8:00 PM"));

        mRecycleView = findViewById(R.id.recyclerView);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mAdapter);
    }
}
