package com.glexer.smartlamp.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.glexer.smartlamp.R;
import com.glexer.smartlamp.data.SmartStoreSharedPreferences;

public class FlashActivity extends AppCompatActivity {

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        token = SmartStoreSharedPreferences.getInstence().getSecretKey();
        if (token.isEmpty()) {
            new Handler().postDelayed(r, 1000);
        } else {

        }
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(FlashActivity.this, LoginActivity.class);
            startActivity(intent);
            supportFinishAfterTransition();
            overridePendingTransition(R.anim.flash_in, R.anim.flash_out);
        }
    };
}
