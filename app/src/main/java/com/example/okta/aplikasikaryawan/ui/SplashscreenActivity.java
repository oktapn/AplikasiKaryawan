package com.example.okta.aplikasikaryawan.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.okta.aplikasikaryawan.R;
import com.example.okta.aplikasikaryawan.widget.SharePreferencesManager;

import butterknife.ButterKnife;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);
        final String user = SharePreferencesManager.getUser(getApplicationContext());
        final String pass = SharePreferencesManager.getPass(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user.isEmpty() && pass.isEmpty()) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        }, 2000);
    }
}
