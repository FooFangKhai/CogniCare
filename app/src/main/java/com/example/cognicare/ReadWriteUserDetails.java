package com.example.cognicare;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ReadWriteUserDetails
{
    public String doB, gender;
    public int attempts1, i;
    public ReadWriteUserDetails(){};

    public ReadWriteUserDetails(String textDoB, String textGender) {
        this.doB = textDoB;
        this.gender = textGender;
        this.attempts1 = 0;
        this.i = 1;
    }
}
