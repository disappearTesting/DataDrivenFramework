package com.example.rough;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTimestamp {
    public static void main(String[] args) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        dateFormat.format(currentDate);
        System.out.println(dateFormat.format(currentDate));
    }
}
