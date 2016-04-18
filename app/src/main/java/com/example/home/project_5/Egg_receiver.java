package com.example.home.project_5;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class Egg_receiver extends BroadcastReceiver {
    public Egg_receiver() {
    }

    private SharedPreferences prefs = null;
    private String EGGS_IN_BASKET = "eggs_in_basket";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Bundle items = intent.getExtras();

        boolean is_breakfast = items.getBoolean("Breakfast?");
        int eggs_in_basket = items.getInt("Eggs");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.notification);
        int mNotificationId;

        String out = "";
        if (is_breakfast) {
            mBuilder.setContentTitle("Breakfast has been made!");
            mNotificationId = 001;
            if (eggs_in_basket >= 6) { // OMELET TIME MOTHER TRUCKERS
                eggs_in_basket -= 6;

                out= "We are having omelets, we have "+ eggs_in_basket +" eggs\n" +
                        "available.";

                SharedPreferences.Editor edit = prefs.edit();
                edit.putInt(EGGS_IN_BASKET,eggs_in_basket); //store new total
                edit.apply();

                Log.e("Number_of_Eggs_af_Bfast",""+prefs.getString(EGGS_IN_BASKET,""));
            } else { // IT'S TIME TO GGGGG-GRUEL
                out = "We are having gruel, we have " + eggs_in_basket + " eggs available.";
            }
        } else {
            // Send notification with current # of eggs
            mBuilder.setContentTitle("Current Eggs in basket");
            mNotificationId = 002;
            out = "We have "+ eggs_in_basket + "available";

        }
        mBuilder.setContentText(out);

        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        mNotifyMgr.notify(mNotificationId,mBuilder.build());

      //  throw new UnsupportedOperationException("Not yet implemented");
    }
}
