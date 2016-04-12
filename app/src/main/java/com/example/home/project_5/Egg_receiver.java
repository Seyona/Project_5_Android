package com.example.home.project_5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Egg_receiver extends BroadcastReceiver {
    public Egg_receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Bundle items = intent.getExtras();

        boolean is_breakfast = items.getBoolean("Breakfast?");
        int eggs_in_basket = items.getInt("Eggs");

        if (is_breakfast) {
            if (eggs_in_basket >= 6) { // OMELET TIME MOTHER TRUCKERS

            } else { // IT'S TIME TO GGGGG-GRUEL

            }
        } else {
            // Send notification with current # of eggs

        }

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
