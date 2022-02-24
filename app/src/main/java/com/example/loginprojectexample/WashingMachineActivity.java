package com.example.loginprojectexample;

import androidx.annotation.NonNull;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WashingMachineActivity extends AppCompatActivity {

    private final String packageName = "com.sec.android.app.clockpackage";
    private FirebaseFirestore db;
    private DocumentReference mRef;
    private Button to_alarm, rBtnCalendar, rBtnTime, btnStart, btnEnd;
    private Button btn6to7, btn7to8, btn8to9, btn9to10, btn10to11, btn11to12, btn12to13, btn13to14;
    private Button btn14to15, btn15to16, btn16to17, btn17to18, btn18to19, btn19to20, btn20to21, btn21to22;
    private Chronometer chrono;
    private DatePicker datePicker;
    private LinearLayout tPicker;
    private TextView pickedDT;
    //default값은 오늘로 설정

    private Date currentTime = Calendar.getInstance().getTime();

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    private SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());

    private String selectyear = yearFormat.format(currentTime);
    private String selectmonth = monthFormat.format(currentTime);
    //int selectYear, selectMonth , selectDay;
    private String selectday = dayFormat.format(currentTime);

    private int selectYear = Integer.parseInt(selectyear);
    private int selectMonth = Integer.parseInt(selectmonth);
    private int selectDay = Integer.parseInt(selectday);
    private int myHour = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washing_machine);

        db = FirebaseFirestore.getInstance();

        chrono = (Chronometer) findViewById(R.id.chrono);

        tPicker = (LinearLayout) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        pickedDT = (TextView) findViewById(R.id.picked_d_t);

        rBtnCalendar = (Button) findViewById(R.id.rBtnCalendar);
        rBtnTime = (Button) findViewById(R.id.rBtnTime);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        rBtnCalendar.setOnClickListener(onClickListener);
        rBtnTime.setOnClickListener(onClickListener);
        btnStart.setOnClickListener(onClickListener);
        btnEnd.setOnClickListener(onClickListener);

        setTimeBtns();

        tPicker.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.INVISIBLE);
        //오늘과 내일만 선택 가능하도록 수정

        datePicker.setMinDate(System.currentTimeMillis());
        datePicker.setMaxDate(System.currentTimeMillis() + 86400000);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month + 1;
                selectDay = dayOfMonth;

                setPickedDT(selectDay, myHour);
            }
        });

        //알람 앱으로 바로 이동
        to_alarm = (Button) findViewById(R.id.to_alarm);
        Intent intent = this.getPackageManager().getLaunchIntentForPackage(packageName);
        to_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WashingMachineActivity.this.startActivity(intent);
            }
        });
    }

    private void setTimeBtns(){
        btn6to7 = (Button) findViewById(R.id.btn_s6e7);
        btn7to8 = (Button) findViewById(R.id.btn_s7e8);
        btn8to9 = (Button) findViewById(R.id.btn_s8e9);
        btn9to10 = (Button) findViewById(R.id.btn_s9e10);
        btn10to11 = (Button) findViewById(R.id.btn_s10e11);
        btn11to12 = (Button) findViewById(R.id.btn_s11e12);
        btn12to13 = (Button) findViewById(R.id.btn_s12e13);
        btn13to14 = (Button) findViewById(R.id.btn_s13e14);
        btn14to15 = (Button) findViewById(R.id.btn_s14e15);
        btn15to16 = (Button) findViewById(R.id.btn_s15e16);
        btn16to17 = (Button) findViewById(R.id.btn_s16e17);
        btn17to18 = (Button) findViewById(R.id.btn_s17e18);
        btn18to19 = (Button) findViewById(R.id.btn_s18e19);
        btn19to20 = (Button) findViewById(R.id.btn_s19e20);
        btn20to21 = (Button) findViewById(R.id.btn_s20e21);
        btn21to22 = (Button) findViewById(R.id.btn_s21e22);

        btn6to7.setOnClickListener(onClickListener);
        btn7to8.setOnClickListener(onClickListener);
        btn8to9.setOnClickListener(onClickListener);
        btn9to10.setOnClickListener(onClickListener);
        btn10to11.setOnClickListener(onClickListener);
        btn11to12.setOnClickListener(onClickListener);
        btn12to13.setOnClickListener(onClickListener);
        btn13to14.setOnClickListener(onClickListener);
        btn14to15.setOnClickListener(onClickListener);
        btn15to16.setOnClickListener(onClickListener);
        btn16to17.setOnClickListener(onClickListener);
        btn17to18.setOnClickListener(onClickListener);
        btn18to19.setOnClickListener(onClickListener);
        btn19to20.setOnClickListener(onClickListener);
        btn20to21.setOnClickListener(onClickListener);
        btn21to22.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rBtnCalendar:
                    tPicker.setVisibility(View.INVISIBLE);
                    datePicker.setVisibility(View.VISIBLE);
                    break;
                case R.id.rBtnTime:
                    updateTimes();
                    tPicker.setVisibility(View.VISIBLE);
                    datePicker.setVisibility(View.INVISIBLE);
                    break;
                case R.id.btnStart:
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    chrono.setTextColor(Color.RED);
                    break;
                case R.id.btnEnd:
                    if (myHour == 0){
                        toast("시간을 선택하세요");
                    }
                    else{
                        mRef = db.collection("ws1").document("Q5tNLngSizOg1pSdzmeH");
                        mRef.update(String.valueOf(myHour), true).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                toast("선택 실패");
                            }
                        });
                        Intent intent = new Intent(WashingMachineActivity.this, MainActivity.class);
                        startActivity(intent);
                        toast("예약 완료!");
                        finish();
                    }
                    break;
                case R.id.btn_s6e7:
                    myHour = 6;
                    break;
                case R.id.btn_s7e8:
                    myHour = 7;
                    break;
                case R.id.btn_s8e9:
                    myHour = 8;
                    break;
                case R.id.btn_s9e10:
                    myHour = 9;
                    break;
                case R.id.btn_s10e11:
                    myHour = 10;
                    break;
                case R.id.btn_s11e12:
                    myHour = 11;
                    break;
                case R.id.btn_s12e13:
                    myHour = 12;
                    break;
                case R.id.btn_s13e14:
                    myHour = 13;
                    break;
                case R.id.btn_s14e15:
                    myHour = 14;
                    break;
                case R.id.btn_s15e16:
                    myHour = 15;
                    break;
                case R.id.btn_s16e17:
                    myHour = 16;
                    break;
                case R.id.btn_s17e18:
                    myHour = 17;
                    break;
                case R.id.btn_s18e19:
                    myHour = 18;
                    break;
                case R.id.btn_s19e20:
                    myHour = 19;
                    break;
                case R.id.btn_s20e21:
                    myHour = 20;
                    break;
                case R.id.btn_s21e22:
                    myHour = 21;
                    break;
            }
            setPickedDT(selectDay, myHour);
        }
    };

    private void setPickedDT(int day, int time){
        pickedDT.setText("현재 " + String.valueOf(day) +"일 : " + String.valueOf(time) + "시");
    }

    private void updateTimes() {
        DocumentReference docRef = db.collection("ws1").document("Q5tNLngSizOg1pSdzmeH");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Map<String, Object> docuDate = document.getData();
                    Boolean[] times = getTimes(docuDate);
                    refreshTimeBtns(times);
                }
            }
        });
    }

    private Boolean[] getTimes(Map<String, Object> docuDate) {
        Boolean[] mTimes = new Boolean[22];

        for (String key : docuDate.keySet()) {
            mTimes[Integer.valueOf(key)] = (Boolean) docuDate.get(key);
        }

        return mTimes;
    }

    private void refreshTimeBtns(Boolean[] times) {
        Button btnTime;
        for (int h = 6; h <= 21; h++) {
            int tId = getResources().getIdentifier(getIdOfTimeBtn(h), "id", "com.example.loginprojectexample");
            btnTime = (Button) findViewById(tId);

            if (times[h]) {
                btnOff(btnTime);
            } else {
                btnOn(btnTime);
            }
        }
    }

    private void btnOn(Button btn){
        btn.setBackgroundColor(getColor(R.color.knu_red));
        btn.setClickable(true);
    }

    private void btnOff(Button btn){
        btn.setBackgroundColor(Color.GRAY);
        btn.setClickable(false);
    }

    private String getIdOfTimeBtn(int h) {
        return "btn_s" + String.valueOf(h) + "e" + String.valueOf(h + 1);
    }

    private void toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}