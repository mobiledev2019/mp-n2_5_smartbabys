package com.ptit.android.kidslearning.Utils;

import java.util.concurrent.TimeUnit;

public class ConvertTime {
    public ConvertTime() {

    }

    public String miliToMinute(double time) {
        int minute = (int) TimeUnit.MILLISECONDS.toMinutes((long) time);
        int second = (int) (TimeUnit.MILLISECONDS.toSeconds((long) time) - 60 * TimeUnit.MILLISECONDS.toMinutes((long) time));
        String TimeFormat;
        if (second < 10) {
            TimeFormat = minute + ":0" + second;
        } else {
            TimeFormat = minute + ":" + second;
        }
        return TimeFormat;
    }
}
