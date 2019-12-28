package com.example.pickmeup;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mFirebase=FirebaseAuth.getInstance();
    EditText emailId,password,username,phonenumber;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onStart () {
        super.onStart();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView signIn_text = findViewById(R.id.signIn_text);
        Button signUP = findViewById(R.id.signup);
        emailId = findViewById(R.id.emailID);
        password = findViewById(R.id.passWord);
        username=findViewById(R.id.userName);
        phonenumber=findViewById(R.id.phoneNumber);
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String Passw = password.getText().toString();
                String Username=username.getText().toString();
                String phone= phonenumber.getText().toString();
                if (email.isEmpty()) {
                    emailId.setText("Please enter the Email id");
                    emailId.requestFocus();
                } else if (Passw.isEmpty()) {
                    password.setText("enter the password");
                    password.requestFocus();
                } else if (email.isEmpty() && Passw.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Fields Are Empty!!!", Toast.LENGTH_SHORT).show();
                } else if ((!email.isEmpty()) && !(Passw.isEmpty())) {
                    mFirebase.createUserWithEmailAndPassword(email,Passw).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //if (!task.isSuccessful()) {
                               // Toast.makeText(RegistrationActivity.this,"Error!!", Toast.LENGTH_SHORT).show();
                            //} else {
                                startActivity(new Intent(RegistrationActivity.this, userdata.class));
                           // }
                        }
                    });
                } else {
                    Toast.makeText(RegistrationActivity.this, "Error!!!!", Toast.LENGTH_SHORT).show();
                }
            }

        });
        signIn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
};

