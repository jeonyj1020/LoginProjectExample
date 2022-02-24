package com.example.loginprojectexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else{
            //회원가입 or 로그인

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {

                            }
                            else {
                                Log.d(TAG, "No such document");
                                Intent intent = new Intent(MainActivity.this, MemberInitActivity.class);
                                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }


                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                }
            });

        }
        findViewById(R.id.logoutButton).setOnClickListener(mOnClickListener);
        findViewById(R.id.LaundryReservation).setOnClickListener(mOnClickListener);
        findViewById(R.id.MyCurrentLaundry).setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Intent intent;
            switch (view.getId()){

                    case R.id.logoutButton:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(MainActivity.this,"로그아웃 되었습니다!", Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, SignUpActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.LaundryReservation:
                        intent = new Intent(MainActivity.this, LaundryReservationActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.MyCurrentLaundry:
                        intent = new Intent(MainActivity.this, MyCurrentLaundryActivity.class);
                        startActivity(intent);
                        break;
                }
        }
    };

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }


    }

}