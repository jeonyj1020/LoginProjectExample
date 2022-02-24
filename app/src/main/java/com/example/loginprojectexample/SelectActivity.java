package com.example.loginprojectexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {

    private ImageButton ws1, ws2, ws3, ws4, ws5, ws6, ws7, ws8, ws9, ws10, ws11, dr1, dr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        ws1 = findViewById(R.id.ws1);
        ws2 = findViewById(R.id.ws2);
        ws3 = findViewById(R.id.ws3);
        ws4 = findViewById(R.id.ws4);
        ws5 = findViewById(R.id.ws5);
        ws6 = findViewById(R.id.ws6);
        ws7 = findViewById(R.id.ws7);
        ws8 = findViewById(R.id.ws8);
        ws9 = findViewById(R.id.ws9);
        ws10 = findViewById(R.id.ws10);
        ws11 = findViewById(R.id.ws11);
        dr1 = findViewById(R.id.dr1);
        dr2 = findViewById(R.id.dr2);

        ws1.setOnClickListener(onClickListener);
        ws2.setOnClickListener(onClickListener);
        ws3.setOnClickListener(onClickListener);
        ws4.setOnClickListener(onClickListener);
        ws5.setOnClickListener(onClickListener);
        ws6.setOnClickListener(onClickListener);
        ws7.setOnClickListener(onClickListener);
        ws8.setOnClickListener(onClickListener);
        ws9.setOnClickListener(onClickListener);
        ws10.setOnClickListener(onClickListener);
        ws11.setOnClickListener(onClickListener);
        dr1.setOnClickListener(onClickListener);
        dr2.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SelectActivity.this, WashingMachineActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private void toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}