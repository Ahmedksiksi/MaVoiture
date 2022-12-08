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
    DatabaseReference databaseReference;

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
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Car");
        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carModel= carModelEdt.getText().toString();
                String carDesc = carDescEdt.getText().toString();
                String carPrice = carPriceEdt.getText().toString();
                String bestSuited = bestSuitedEdt.getText().toString();
                String carImg = carImgEdt.getText().toString();
                CarRvModal carRvModal = new CarRvModal();
                carRvModal.setCarImg(carImg);
                carRvModal.setCarModel(carModel);
                carRvModal.setCarDescription(carDesc);
                carRvModal.setCarPrice(carPrice);
                carRvModal.setBestSuitedFor(bestSuited);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        databaseReference.child(carModel).setValue(carRvModal);
                        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+carRvModal);
                        Toast.makeText(Add_car.this, "Car Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Add_car.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Add_car.this, "Fail to add Car..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
