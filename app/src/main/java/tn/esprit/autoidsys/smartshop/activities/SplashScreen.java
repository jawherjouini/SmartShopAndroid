package tn.esprit.autoidsys.smartshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tn.esprit.autoidsys.smartshop.R;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
                finish();
            }
        }, 2000);

    }

}