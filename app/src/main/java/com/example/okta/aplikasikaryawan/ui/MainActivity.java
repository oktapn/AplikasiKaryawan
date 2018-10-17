package com.example.okta.aplikasikaryawan.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.okta.aplikasikaryawan.R;
import com.example.okta.aplikasikaryawan.widget.SharePreferencesManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.FormKaryawanBtn)
    void openFormKaryawan(){
        Intent formIntent = new Intent(getApplicationContext(),FormInputKaryawanActivity.class);
        startActivity(formIntent);
    }

    @OnClick(R.id.BtnLogout)
    void logout() {
        SharePreferencesManager.logout(this);
        finish();
        Intent LoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(LoginIntent);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
