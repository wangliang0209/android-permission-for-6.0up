package com.wl.permission;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fastaccess.permission.base.PermissionHelper;
import com.fastaccess.permission.base.callback.OnPermissionCallback;

import java.util.Arrays;

/**
 * Created by wangliang on 17-1-16.
 */

public class PermissionActivityDemoActivity extends AppCompatActivity implements OnPermissionCallback {
    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String SINGLE_PERMISSION = Manifest.permission.GET_ACCOUNTS;

    private final static String[] MULTI_PERMISSIONS = new String[]{
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_activity);

        permissionHelper = PermissionHelper.getInstance(this);

    }

    public void onSingle(View v) {
        permissionHelper
                .setForceAccepting(false)// true if you had like force reshowing the permission dialog on Deny (not recommended)
                .request(SINGLE_PERMISSION);
    }

    public void onMulti(View v) {
        permissionHelper
                .setForceAccepting(false)// true if you had like force reshowing the permission dialog on Deny (not recommended)
                .request(MULTI_PERMISSIONS);
    }

    /**
     * Used to determine if the user accepted {@link android.Manifest.permission#SYSTEM_ALERT_WINDOW} or no.
     * <p/>
     * if you never passed the permission this method won't be called.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        permissionHelper.onActivityForResult(requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {
        Log.d(TAG, "onPermissionGranted");
        Log.d(TAG, "Permission(s) " + Arrays.toString(permissionName) + "已经赋予了");
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {
        Log.d(TAG, "onPermissionDeclined");
        Log.d(TAG, "Permission(s) " + Arrays.toString(permissionName) + "已经被拒绝了");
    }

    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName) {
        Log.d(TAG, "onPermissionPreGranted " + permissionsName + " 该权限已经被赋予过了");
    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String permissionName) {
        Log.d(TAG, "onPermissionNeedExplanation " + permissionName + " 申请权限已经被用户拒绝了，再申请权限就给用户一个解释");
    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName) {
        Log.d(TAG, "onPermissionReallyDeclined " + permissionName);
        Log.d(TAG, "Permission(s) " + permissionName + "选择了别再提醒我之后就走这，真的已经被拒绝了");
    }

    @Override
    public void onNoPermissionNeeded() {
        Log.d(TAG, "onNoPermissionNeeded. 6.0以下系统.");
    }
}
