package com.example.mavoitureapplicationmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText registerFullName,registerEmail,registerPassword,confPassword;
    Button registerUserBtn,gotoLogin;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFullName = findViewById(R.id.registerFullName);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        confPassword = findViewById(R.id.confPassword);
        registerUserBtn = findViewById(R.id.registerUserBtn);
        gotoLogin = findViewById(R.id.gotoLogin);

        fAuth = FirebaseAuth.getInstance() ;
        registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName = registerFullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confPass = confPassword.getText().toString();

                if(fullName.isEmpty()){
                    registerFullName.setError("Saisissez votre prénom et votre nom ");
                    return;
                }
                if(email.isEmpty()){
                    registerEmail.setError("Saisissez votre adresse e-mail ");
                    return;
                }
                if(password.isEmpty()){
                    registerPassword.setError("Saisissez votre mot de passe");
                    return;
                }if(!password.equals(confPass)){
                    confPassword.setError("veuillez confirmer votre mot de passe ");
                    return;
                }
                Toast.makeText(Register.this, "Bienvenue", Toast.LENGTH_SHORT).show();
                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Register.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}