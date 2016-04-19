package com.example.home.project_5;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by David & Jack on 4/19/16.
 */
public class Egg_service extends Service {

    private SharedPreferences prefs = null;
    private String EGGS_IN_BASKET = "eggs_in_basket";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("Service","Entering");

        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        boolean is_Breakfast = intent.getExtras().getBoolean("Breakfast", false);
        boolean update_needed = intent.getExtras().getBoolean("Update", false);
        int current_eggs = prefs.getInt(EGGS_IN_BASKET,0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getBaseContext());
        mBuilder.setSmallIcon(R.drawable.notification);
        int mNotificationId;

        String out;

        Log.e("Breakfast",""+is_Breakfast);

        if (update_needed) {
            mBuilder.setContentTitle("Current Eggs in basket");
            mNotificationId = 002;
            out = "We have "+ current_eggs + " available";
        }
        else if (is_Breakfast) {
            mBuilder.setContentTitle("Breakfast has been made!");
            mNotificationId = 001;
            if (current_eggs >= 6) {
                current_eggs = (current_eggs - 6 < 0)? 0 : current_eggs - 6;
                out= "We are having omelets, we have "+ current_eggs +" eggs\n" +
                        "available.";

                SharedPreferences.Editor edit = prefs.edit();
                edit.putInt(EGGS_IN_BASKET,current_eggs); //store new total
                edit.apply();

                Log.e("Number_of_Eggs_@_B_fast", "" + prefs.getInt(EGGS_IN_BASKET, 0));
                sendBroadcast(new Intent(getBaseContext().getString(R.string.intent_update)).setClass(getBaseContext(),Egg_receiver.class));

            } else {
                out = "We are having gruel, we have " + current_eggs + " eggs available.";
            }
        } else {
            mBuilder.setContentTitle("Current Eggs in basket");
            mNotificationId = 002;

            if (intent.getAction().equals(getBaseContext().getString(R.string.intent_add_one))) {
                current_eggs += 1;
            } else if (intent.getAction().equals(getBaseContext().getString(R.string.intent_add_two))){
                current_eggs += 2;
            } else { // remove one
                current_eggs = (current_eggs - 1 < 0)? 0 : current_eggs - 1;
            }

            SharedPreferences.Editor edit = prefs.edit();
            edit.putInt(EGGS_IN_BASKET, current_eggs); //store new total
            edit.apply();

            out = "We have "+ current_eggs + " eggs available";

        }

        mBuilder.setContentText(out);
        mBuilder.setContentIntent(null);

        NotificationManager mNotifyMgr =
                (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);


        mNotifyMgr.notify(mNotificationId, mBuilder.build());
        Log.e("Service", "Exiting");

        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // auto generated not needed other than for inheritance
        return null;
    }
}
