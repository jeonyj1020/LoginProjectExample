package com.example.loginprojectexample.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginprojectexample.MainActivity;
import com.example.loginprojectexample.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;
    private static final String TAG = "SignUpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();

        findViewById(R.id.signUpButton).setOnClickListener(mOnClickListener);
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.signUpButton:
                    signUp();
                    break;
            }
        }
    };

    private void signUp() {

        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.passwordCheckEditText)).getText().toString();
        String bedNum = ((EditText) findViewById(R.id.bedNumberEditText)).getText().toString();

        if (email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0 && bedNum.length() > 0) {

            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            MemberInfo memberInfo = new MemberInfo(bedNum);
                            if (user != null) {
                                mDb.collection("users").document(user.getUid()).set(memberInfo)
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                toast("???????????? ?????? ??????");
                                            }
                                        });

                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                //????????? UI ??????
                            } else {
                                // If sign in fails, display a message to the user.

                                //????????? UI ??????
                                if (task.getException() != null) {
                                    toast("???????????? ??????");
                                }

                            }
                        }
                    }
                });

            } else {
                toast("???????????? ?????????");
            }
        } else {
            toast("???????????? ???????????? ?????? ??????");
        }
    }

    private void toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}