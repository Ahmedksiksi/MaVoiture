package com.example.mavoitureapplicationmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {
    private TextInputEditText userNameEdt, passwordEdt;
    private Button loginBtn;
    private TextView newUserTV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEdt = findViewById(R.id.idEdtUserName);
        passwordEdt = findViewById(R.id.idEdtPassword);
        loginBtn = findViewById(R.id.idBtnLogin);
        newUserTV = findViewById(R.id.idTVNewUser);
        mAuth = FirebaseAuth.getInstance();
        loadingPB = findViewById(R.id.idPBLoading);

        newUserTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingPB.setVisibility(View.VISIBLE);

                String email = userNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();

                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Login Successful..", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Please enter valid user credentials..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}