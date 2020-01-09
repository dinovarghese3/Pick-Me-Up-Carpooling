package com.example.pickmeup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dissition_activity extends AppCompatActivity {
    Button createRide,searchRide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dissition_activity);
        createRide=findViewById(R.id.create_ride);
        searchRide=findViewById(R.id.search_Ride);
        createRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Dissition_activity.this,MainActivity.class));
            }
        });
        searchRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dissition_activity.this, Search_Ride.class));
                finish();
            }
        });
    }
}
