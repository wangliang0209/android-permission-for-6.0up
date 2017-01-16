package com.wl.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoActicvity(View v) {
        Intent intent = new Intent(this, PermissionActivityDemoActivity.class);
        startActivity(intent);
    }

    public void gotoFragment(View v) {
        Intent intent = new Intent(this, PermissionFragmentDemoActivity.class);
        startActivity(intent);
    }

}
