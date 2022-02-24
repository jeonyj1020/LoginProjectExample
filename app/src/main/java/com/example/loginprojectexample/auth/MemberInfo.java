package com.example.loginprojectexample.auth;

import android.widget.EditText;

public class MemberInfo {

    private String name;
    private String bedNumber;

    public MemberInfo(String bedNumber) {
        this.name = name;
        this.bedNumber = bedNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
}
