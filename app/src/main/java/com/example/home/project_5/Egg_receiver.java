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

        Log.e("RecieverStatus","Entered");

        //prefs = PreferenceManager.getDefaultSharedPreferences(context);
        //Bundle items = intent.getExtras();

        //Context.startService(Intent);


        String intent_name = intent.getAction();
        Intent out = new Intent();
        out.setAction(intent_name);
        out.setClass(context,Egg_service.class);

        if (intent_name.equals(context.getString(R.string.intent_add_one))) {
            Log.e("Button","ADD EGG");
            out.putExtra("Eggs",1);

        }
        else if (intent_name.equals(context.getString(R.string.intent_add_two))) {
            Log.e("Button","ADD 2 EGG");
            out.putExtra("Eggs", 2);

        }
        else if (intent_name.equals(context.getString(R.string.intent_remove_one))) {
            Log.e("Button","REMOVE EGG");
            out.putExtra("Eggs",-1);

        } else if (intent_name.equals(context.getString(R.string.intent_make_breakfast))) { //make breakfast
            Log.e("Button","BREAKFAST");
            out.putExtra("Breakfast",true);
        } else { //needs to update a notification
            Log.e("Button","UPDATE NOTIFICATION");
            out.putExtra("Update",true);
        }

        Log.e("Intent Action", intent_name);
        context.startService(out);

        Log.e("RecieverStatus", "Exit");




/*

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

                Log.e("Number_of_Eggs_af_Bfast",""+prefs.getInt(EGGS_IN_BASKET,0));

                Intent egg_Action = new Intent();

                egg_Action.setAction("com.example.home.project_5.EGG_NUMBER_MODIFICATION");
                egg_Action.setClass(context, Egg_receiver.class);
                egg_Action.putExtra("Breakfast?", false);
                egg_Action.putExtra("Eggs", eggs_in_basket);
                context.sendBroadcast(egg_Action);

            } else { // IT'S TIME TO GGGGG-GRUEL
                out = "We are having gruel, we have " + eggs_in_basket + " eggs available.";
            }
        } else {
            // Send notification with current # of eggs
            mBuilder.setContentTitle("Current Eggs in basket");
            mNotificationId = 002;
            out = "We have "+ eggs_in_basket + " available";

        }
        mBuilder.setContentText(out);
        mBuilder.setContentIntent(null);

        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        mNotifyMgr.notify(mNotificationId,mBuilder.build());
        Log.e("RecieverStatus", "Exiting");

      //  throw new UnsupportedOperationException("Not yet implemented");
      */
    }
}
