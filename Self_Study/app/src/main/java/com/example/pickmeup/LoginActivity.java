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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;





public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mFirebase=FirebaseAuth.getInstance();
    EditText emailId,password;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onStart () {
        super.onStart();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Button signIN = findViewById(R.id.signin);
        emailId = findViewById(R.id.emailID);
        password = findViewById(R.id.passWord);
        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String Passw = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setText("Enter the Email id");
                    emailId.requestFocus();
                } else if (Passw.isEmpty()) {
                    //password.setText("enter the password");
                    password.requestFocus();
                } else if (email.isEmpty() && Passw.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields Are Empty!!!", Toast.LENGTH_SHORT).show();
                } else if ((!email.isEmpty()) && !(Passw.isEmpty())) {
                    mFirebase.createUserWithEmailAndPassword(email,Passw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {

                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(LoginActivity.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                                    //if account already sign in
                                    //bottom else is not required
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                   // Intent activity2Intent = new Intent(getApplicationContext(), MainActivity.class);
                                   // startActivity(activity2Intent);
                                }


                            }
                            else {
                                startActivity(new Intent(LoginActivity.this, MapsHome.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error!!!!", Toast.LENGTH_SHORT).show();
                }
            }

        });
        TextView signUp_text = findViewById(R.id.signUp_text);
        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });

    }


}
