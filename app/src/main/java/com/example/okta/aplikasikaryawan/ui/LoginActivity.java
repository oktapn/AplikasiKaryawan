package com.example.okta.aplikasikaryawan.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okta.aplikasikaryawan.R;
import com.example.okta.aplikasikaryawan.widget.SharePreferencesManager;

import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.TVHolderUsername)
    TextView TVHolderUsername;
    @BindView(R.id.ETUsername)
    EditText ETUsername;
    @BindView(R.id.TVHolderPassword)
    TextView TVHolderPassword;
    @BindView(R.id.ETPassword)
    EditText ETPassword;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnTextChanged(value = R.id.ETUsername, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterUsernameTextChanged() {
        int length = ETUsername.getText().length();
        if (length == 0) {
            TVHolderUsername.setVisibility(View.VISIBLE);
        } else {
            TVHolderUsername.setVisibility(View.GONE);
        }
    }

    @OnTextChanged(value = R.id.ETPassword, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPasswordTextChanged() {
        int length = ETPassword.getText().length();
        if (length == 0) {
            TVHolderPassword.setVisibility(View.VISIBLE);
        } else {
            TVHolderPassword.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.BtnLogin)
    void ButtonLogin() {
        if (ETUsername.getText().toString().isEmpty()) {
            afterUsernameTextChanged();
        } else if (ETPassword.getText().toString().isEmpty()) {
            afterPasswordTextChanged();
        } else {
            hideSoftKeyboard();
            SharePreferencesManager.setLoginPreference(this,ETUsername.getText().toString(),ETPassword.getText().toString());
            finish();
            Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainIntent);
        }
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
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
