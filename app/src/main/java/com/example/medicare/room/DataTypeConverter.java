package com.example.medicare.room;

import androidx.room.TypeConverter;
import androidx.room.TypeConverter;

import com.google.android.gms.common.util.NumberUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;
public class DataTypeConverter {



        @TypeConverter
        public static String calendarToTimestamp(Calendar calendar){
            if(calendar==null){
                return null;
            }
            return "" + calendar.getTimeInMillis()/1000;
        }
        @TypeConverter
        public static Calendar calendarFromTimestamp(String value){
            if(value==null) {
                return null;
            }
            Calendar calendar=new GregorianCalendar();
            calendar.setTimeInMillis(Long.parseLong(value)*1000);
            return calendar;
        }
    }


