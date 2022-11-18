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

public class Add_car extends AppCompatActivity {

    private Button addCarBtn;
    private TextInputEditText carModelEdt, carDescEdt, carPriceEdt, bestSuitedEdt, carImgEdt;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ma-voiture-application-mobile-default-rtdb.firebaseio.com/");
    private ProgressBar loadingPB;
    private String carID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        addCarBtn = findViewById(R.id.idBtnAddCar);
        carModelEdt = findViewById(R.id.idEdtCarModel);
        carDescEdt = findViewById(R.id.idEdtCarDescription);
        carPriceEdt = findViewById(R.id.idEdtCarPrice);
        bestSuitedEdt = findViewById(R.id.idEdtSuitedFor);
        carImgEdt = findViewById(R.id.idEdtCarImageLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line creating our database reference.
        databaseReference = firebaseDatabase.getReference("Car");
        // adding click listener for our add course button.
        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                // getting data from our edit text.
                String carModel= carModelEdt.getText().toString();
                String carDesc = carDescEdt.getText().toString();
                String carPrice = carPriceEdt.getText().toString();
                String bestSuited = bestSuitedEdt.getText().toString();
                String carImg = carImgEdt.getText().toString();
                carID = carModel;
                // on below line we are passing all data to our modal class.
                CarRvModal carRvModal = new CarRvModal(carID, carModel, carDesc, carPrice, bestSuited, carImg);
                // on below line we are calling a add value event
                // to pass data to firebase database.
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // on below line we are setting data in our firebase database.
                        databaseReference.child(carID).setValue(carRvModal);
                        // displaying a toast message.
                        Toast.makeText(Add_car.this, "Car Added..", Toast.LENGTH_SHORT).show();
                        // starting a main activity.
                        startActivity(new Intent(Add_car.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // displaying a failure message on below line.
                        Toast.makeText(Add_car.this, "Fail to add Car..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}