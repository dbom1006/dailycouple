package com.cntt.dbom.loveapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Bac Nice on 12/13/2016.
 */

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Create an Intent that will start the Menu-Activity. */
        Intent mainIntent = new Intent(Main.this, MainActivity.class);
        Main.this.startActivity(mainIntent);
        Main.this.finish();


    }
}
