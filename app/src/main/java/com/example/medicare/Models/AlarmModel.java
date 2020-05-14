package com.example.medicare.Models;

public class AlarmModel  {
    private int hour;
    private int minute;
    private String name;

    public AlarmModel(int hour, int minute, String name) {
        this.hour = hour;
        this.minute = minute;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
