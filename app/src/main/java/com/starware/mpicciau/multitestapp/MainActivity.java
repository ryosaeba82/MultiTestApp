package com.starware.mpicciau.multitestapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences AppInfo = getPreferences(Context.MODE_PRIVATE);
        Boolean value = AppInfo.getBoolean(getString(R.string.pref_SplashScreen),false);
        if(!value)
        {
            SharedPreferences.Editor editor = AppInfo.edit();
            editor.putBoolean(getString(R.string.pref_SplashScreen),true);
            editor.commit();
            //lancio splashscreen
            Intent intent = new Intent(this,SplashScreenActivity.class);
            startActivity(intent);
            //TODO : pulire la variabile alla chiusura dell'app
            //TODO : usare una variabile globale?
        }
        else
            setContentView(R.layout.activity_main);
    }
}
