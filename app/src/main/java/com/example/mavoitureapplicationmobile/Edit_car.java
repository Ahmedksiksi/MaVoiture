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
    private String carID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);

        Button addCarBtn = findViewById(R.id.idBtnAddCar);
        carModelEdt = findViewById(R.id.idEdtCarModel);
        carDescEdt = findViewById(R.id.idEdtCarDescription);
        carPriceEdt = findViewById(R.id.idEdtCarPrice);
        bestSuitedEdt = findViewById(R.id.idEdtSuitedFor);
        carImgEdt = findViewById(R.id.idEdtCarImageLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        carRVModal = getIntent().getParcelableExtra("car");
        Button deleteCarBtn = findViewById(R.id.idBtnDeleteCar);

        if (carRVModal != null) {
            carModelEdt.setText(carRVModal.getCarModel());
            carPriceEdt.setText(carRVModal.getCarPrice());
            bestSuitedEdt.setText(carRVModal.getBestSuitedFor());
            carImgEdt.setText(carRVModal.getCarImg());
            carDescEdt.setText(carRVModal.getCarDescription());
        }

        databaseReference = firebaseDatabase.getReference("Cars").child(carID);
        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String carModel = carModelEdt.getText().toString();
                String carDesc = carDescEdt.getText().toString();
                String carPrice = carPriceEdt.getText().toString();
                String bestSuited = bestSuitedEdt.getText().toString();
                String carImg = carImgEdt.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("carModel", carModel);
                map.put("carDescription", carDesc);
                map.put("carPrice", carPrice);
                map.put("bestSuitedFor", bestSuited);
                map.put("carsImg", carImg);
                map.put("carId", carID);


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(Edit_car.this, "Car Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Edit_car.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Edit_car.this, "Fail to update Car..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCar();
            }
        });

    }

    private void deleteCar() {
        databaseReference.removeValue();
        Toast.makeText(this, "Car Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Edit_car.this, MainActivity.class));
    }
}