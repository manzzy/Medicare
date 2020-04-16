package com.example.medicare;

import android.content.Context;
import android.content.Intent;

public class Share {
    String appLink = "App Link Will be placed here";
    Context context;

    public Share( Context context) {
        this.context = context;
        share(appLink);
    }

    public void share(String link){
        Intent intentInvite = new Intent(Intent.ACTION_SEND);
        intentInvite.setType("text/plain");
        String body = link;
        String subject =" Share Medicare to your loved ones ";
        intentInvite.putExtra(Intent.EXTRA_SUBJECT,subject);
        intentInvite.putExtra(Intent.EXTRA_TEXT,body);
        context.startActivity(Intent.createChooser(intentInvite,"Share Using"));
    }

    public String getAppLink() {

        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }
}
