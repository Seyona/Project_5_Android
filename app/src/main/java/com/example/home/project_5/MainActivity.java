package com.example.home.project_5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String PREFS_SETUP = "prefs_setup";
    private String EGGS_IN_BASKET = "eggs_in_basket";

    private SharedPreferences prefs = null;
    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Receiver","Receiver made");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        boolean is_setup  = prefs.getBoolean("prefs_setup",false);
        setup_preferences(is_setup);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Function that will setup the preferences on initial start up after fresh install
     * @param setup is preferences already set?
     */
    public void setup_preferences(boolean setup) {
        if (!setup) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(PREFS_SETUP,true);
            edit.putInt(EGGS_IN_BASKET,0);
            edit.apply();
        } else {
            Log.e("Preferences", "Preferences are setup");
        }
    }

    public void modify_Eggs(int magnitude) {
        int current_eggs = prefs.getInt(EGGS_IN_BASKET, 0);
        Log.e("Current Eggs in Basket", "" + current_eggs);
        SharedPreferences.Editor edit = prefs.edit();
        int new_eggs = current_eggs + magnitude;
        edit.putInt(EGGS_IN_BASKET, new_eggs);
        edit.apply();
        Log.e("Eggs after mag shift", "" + new_eggs);


        Intent egg_Action = new Intent(this,EGG_ACTION.class);
        egg_Action.putExtra("Eggs",new_eggs);
        startActivity(egg_Action);

    }

    public void addOneEgg(View view) {
        modify_Eggs(1);
    }

    public void addTwoEgg(View view) {
        modify_Eggs(2);
    }

    public void removeEgg(View view) {
        modify_Eggs(-1);
    }

    public void make_breakfast(View view) {
        if (prefs.getInt(EGGS_IN_BASKET,0) >= 6) {
            /** WE ARE HAVIN OMELETS*/
        } else {
            /** MEH GRUEL IT IS */
        }
    }




}
