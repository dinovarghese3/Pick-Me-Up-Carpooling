package com.example.pickmeup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class userdata extends AppCompatActivity {
    EditText userName,email,phoneNumber,address;
    RadioGroup radioGroup;
    RadioButton male,female;
    Button Save;
    String ismale;
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db;
    Map<String, Object> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        radioGroup = findViewById(R.id.gender);
        male = findViewById(R.id.radio_male);
        female = findViewById(R.id.radio_female);
        address = findViewById(R.id.address);
        user = new HashMap<>();
        db = FirebaseFirestore.getInstance();
        userName=findViewById(R.id.userName);
        email=findViewById(R.id.email_id);
        phoneNumber=findViewById(R.id.phoneNumber);
        Save=findViewById(R.id.signUp);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
            Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName,email_id,PhoneNumber;
                UserName=userName.getText().toString();
                email_id=email.getText().toString();
                String Address=address.getText().toString();

                int gender = radioGroup.getCheckedRadioButtonId();
                if (gender==R.id.radio_male){
                    ismale="male";
                }
                else if (gender==R.id.radio_female){
                    ismale="Female";
                }
                PhoneNumber=phoneNumber.getText().toString();
                    if(UserName.isEmpty()|| email_id.isEmpty()||PhoneNumber.isEmpty()|| ismale.isEmpty()||Address.isEmpty()){
                        Toast.makeText(userdata.this,"Fill the Fields", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {

                       // Toast.makeText(userdata.this, "Fill the Fields", Toast.LENGTH_SHORT).show();
                        user.put("username",UserName);
                        user.put("email",email_id);
                        user.put("gender",ismale);
                        user.put("address",Address);
                        db.collection("users")
                                .add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(userdata.this, "database updated", Toast.LENGTH_SHORT).show();
                                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
                                startActivity(new Intent(userdata.this,Dissition_activity.class));
                            }
                        });

                    }

            }
        });
    }
}
