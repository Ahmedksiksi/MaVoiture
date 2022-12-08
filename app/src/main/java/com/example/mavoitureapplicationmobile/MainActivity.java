package com.example.mavoitureapplicationmobile;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public  class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    CarRVAdapter adapter;
    DatabaseReference mbase;
    private FloatingActionButton idFABAddCar;
    private ImageView idIVCar ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idFABAddCar = findViewById(R.id.idFABAddCar);
        idIVCar= findViewById(R.id.idIVCar);
        mbase = FirebaseDatabase.getInstance().getReference("Car");
        idFABAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, Add_car.class);
                startActivity(i);
            }

        });


        recyclerView = findViewById(R.id.idRVCars);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        FirebaseRecyclerOptions<CarRvModal> options
                = new FirebaseRecyclerOptions.Builder<CarRvModal>()
                .setQuery(mbase, CarRvModal.class)
                .build();
        adapter = new CarRVAdapter(options);
        recyclerView.setAdapter(adapter);

    }
    public void navigation(){
        Intent i= new Intent(MainActivity.this, SheetLayout.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this,Edit_car.class);
startActivity(intent);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
    public void onButtonShowPopupWindowClick(View view) {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.bottom_sheet_layout, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}