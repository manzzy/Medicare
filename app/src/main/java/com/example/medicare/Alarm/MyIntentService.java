package com.example.medicare.Alarm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import androidx.core.app.NotificationCompat;

import com.example.medicare.HomeActivity;
import com.example.medicare.R;

public class MyIntentService extends IntentService {
    private NotificationManager notificationManager;
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification("Take your Medicine");
    }
    private  void sendNotification(String s){
        notificationManager=(NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,new Intent(this, HomeActivity.class),0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_access_alarm_black_24dp).setStyle(new NotificationCompat.BigTextStyle().bigText(s)).setContentText(s);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(1,builder.build());
    }

}
