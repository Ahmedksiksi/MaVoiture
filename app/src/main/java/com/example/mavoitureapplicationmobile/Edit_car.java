package com.example.mavoitureapplicationmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;



public class Edit_car extends AppCompatActivity {

    private TextInputEditText carModelEdt, carDescEdt, carPriceEdt, bestSuitedEdt, carImgEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Button addCarBtn;
    CarRvModal carRVModal;
    private ProgressBar loadingPB;
    // creating a string for our course id.
    private String carID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);
        // initializing all our variables on below line.
        Button addCarBtn = findViewById(R.id.idBtnAddCar);
        carModelEdt = findViewById(R.id.idEdtCarModel);
        carDescEdt = findViewById(R.id.idEdtCarDescription);
        carPriceEdt = findViewById(R.id.idEdtCarPrice);
        bestSuitedEdt = findViewById(R.id.idEdtSuitedFor);
        carImgEdt = findViewById(R.id.idEdtCarImageLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line we are getting our modal class on which we have passed.
        carRVModal = getIntent().getParcelableExtra("car");
        Button deleteCarBtn = findViewById(R.id.idBtnDeleteCar);

        if (carRVModal != null) {
            // on below line we are setting data to our edit text from our modal class.
            carModelEdt.setText(carRVModal.getCarModel());
            carPriceEdt.setText(carRVModal.getCarPrice());
            bestSuitedEdt.setText(carRVModal.getBestSuitedFor());
            carImgEdt.setText(carRVModal.getCarImg());
            carDescEdt.setText(carRVModal.getCarDescription());
            carID = carRVModal.getCarId();
        }

        // on below line we are initialing our database reference and we are adding a child as our course id.
        databaseReference = firebaseDatabase.getReference("Cars").child(carID);
        // on below line we are adding click listener for our add course button.
        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are making our progress bar as visible.
                loadingPB.setVisibility(View.VISIBLE);
                // on below line we are getting data from our edit text.
                String carModel = carModelEdt.getText().toString();
                String carDesc = carDescEdt.getText().toString();
                String carPrice = carPriceEdt.getText().toString();
                String bestSuited = bestSuitedEdt.getText().toString();
                String carImg = carImgEdt.getText().toString();
                // on below line we are creating a map for
                // passing a data using key and value pair.
                Map<String, Object> map = new HashMap<>();
                map.put("carModel", carModel);
                map.put("carDescription", carDesc);
                map.put("carPrice", carPrice);
                map.put("bestSuitedFor", bestSuited);
                map.put("carsImg", carImg);
                map.put("carId", carID);

                // on below line we are calling a database reference on
                // add value event listener and on data change method
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // making progress bar visibility as gone.
                        loadingPB.setVisibility(View.GONE);
                        // adding a map to our database.
                        databaseReference.updateChildren(map);
                        // on below line we are displaying a toast message.
                        Toast.makeText(Edit_car.this, "Car Updated..", Toast.LENGTH_SHORT).show();
                        // opening a new activity after updating our coarse.
                        startActivity(new Intent(Edit_car.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // displaying a failure message on toast.
                        Toast.makeText(Edit_car.this, "Fail to update Car..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // adding a click listener for our delete course button.
        deleteCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete a course.
                deleteCar();
            }
        });

    }

    private void deleteCar() {
        // on below line calling a method to delete the course.
        databaseReference.removeValue();
        // displaying a toast message on below line.
        Toast.makeText(this, "Car Deleted..", Toast.LENGTH_SHORT).show();
        // opening a main activity on below line.
        startActivity(new Intent(Edit_car.this, MainActivity.class));
    }
}