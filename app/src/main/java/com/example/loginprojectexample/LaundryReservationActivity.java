package com.example.loginprojectexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaundryReservationActivity extends AppCompatActivity {

    private Button WashingMachine;
    private Button Dryer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_reservation);

        WashingMachine = (Button)findViewById(R.id.WashingMachine);
        Dryer = (Button)findViewById(R.id.Dryer);

        WashingMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaundryReservationActivity.this, WashingMachineActivity.class);
                startActivity(intent);
            }
        });
    }
}