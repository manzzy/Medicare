package com.example.medicare.room;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Calendar;
@Entity(tableName = "AlarmModel")
public class AlarmModel  {
    @PrimaryKey(autoGenerate = true)
    int id;
    @NonNull
    @TypeConverters({DateTypeConverter.class})
    private Calendar calendar;
    @NonNull
    private String name;
    @NonNull
    private int rsa;

    public int getRsa() {
        return rsa;
    }

    public void setRsa(int rsa) {
        this.rsa = rsa;
    }

    public AlarmModel() {
    }

    @Ignore
    public AlarmModel(Calendar calendar, String name,int rsa) {
        this.calendar=calendar;
        this.name = name;
        this.rsa=rsa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}