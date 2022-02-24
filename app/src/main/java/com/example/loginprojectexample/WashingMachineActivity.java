package com.example.loginprojectexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WashingMachineActivity extends AppCompatActivity {

    private final String packageName = "com.sec.android.app.clockpackage";
    Button to_alarm;
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rBtnCalendar, rBtnTime;
    DatePicker datePicker;
    TimePicker tPicker;
    //default값은 오늘로 설정

    Date currentTime = Calendar.getInstance().getTime();

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd",Locale.getDefault());

    String selectyear = yearFormat.format(currentTime);
    String selectmonth= monthFormat.format(currentTime);
    //int selectYear, selectMonth , selectDay;
    String selectday = dayFormat.format(currentTime);

    int selectYear = Integer.parseInt(selectyear);
    int selectMonth = Integer.parseInt(selectmonth);
    int selectDay = Integer.parseInt(selectday);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washing_machine);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnEnd = (Button)findViewById(R.id.btnEnd);

        chrono = (Chronometer) findViewById(R.id.chrono);

        rBtnCalendar = (RadioButton)findViewById(R.id.rBtnCalendar);
        rBtnTime =(RadioButton)findViewById(R.id.rBtnTime);

        tPicker = (TimePicker)findViewById(R.id.timePicker);
        datePicker = (DatePicker)findViewById(R.id.datePicker);

        tPicker.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.INVISIBLE);

        rBtnCalendar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.VISIBLE);
            }
        });

        rBtnTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

                Toast.makeText(getApplicationContext(),
                        Integer.toString(selectYear)+"년 " +
                                Integer.toString(selectMonth)+"월 " +
                                Integer.toString(selectDay)+"일 " +
                                Integer.toString(tPicker.getCurrentHour())+"시 " +
                                Integer.toString(tPicker.getCurrentMinute())+"분 저장됨"

                        , Toast.LENGTH_LONG).show();


            }



        });

        //오늘과 내일만 선택 가능하도록 수정

        datePicker.setMinDate(System.currentTimeMillis());
        datePicker.setMaxDate(System.currentTimeMillis()+86400000);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                selectYear =year;
                selectMonth =month + 1;
                selectDay = dayOfMonth;
            }
        });

        //알람 앱으로 바로 이동
        to_alarm = (Button)findViewById(R.id.to_alarm);
        Intent intent = this.getPackageManager().getLaunchIntentForPackage(packageName);
        to_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WashingMachineActivity.this.startActivity(intent);
            }
        });
    }
}