package com.example.medicare.Alarm;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.legacy.content.WakefulBroadcastReceiver;

public class AlarmReciever extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(uri == null) {
            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
//        Ringtone ringtone=RingtoneManager.getRingtone(context,uri);
//        ringtone.play();
        MediaPlayer mediaPlayer=MediaPlayer.create(context,uri);
        mediaPlayer.start();
        ComponentName componentName=new ComponentName(context.getPackageName(),MyIntentService.class.getName());
        startWakefulService(context,(intent.setComponent(componentName)));
        setResultCode(Activity.RESULT_OK);
    }
}