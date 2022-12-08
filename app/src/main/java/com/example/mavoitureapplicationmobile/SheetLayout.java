package com.example.mavoitureapplicationmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class SheetLayout extends AppCompatActivity {
    private RecyclerView recyclerView;
    CarRVAdapter adapter;
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_layout);
        recyclerView = findViewById(R.id.idRVCars);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        FirebaseRecyclerOptions<CarRvModal> options
                = new FirebaseRecyclerOptions.Builder<CarRvModal>()
                .setQuery(mbase, CarRvModal.class)
                .build();
        adapter = new CarRVAdapter(options);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity for detail of a course.
                Intent i= new Intent(SheetLayout.this, SheetLayout.class);
                startActivity(i);
            }

        });
    }
}
