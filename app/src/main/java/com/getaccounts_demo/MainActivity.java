package com.getaccounts_demo;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ACCOUNTS_PERMISSION = 1;
    private TextView googleAccounts, allAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleAccounts = (TextView) findViewById(R.id.googleAccounts);
        allAccounts = (TextView) findViewById(R.id.allAccounts);

        requestGetAccountsPermission();
    }

    public void requestGetAccountsPermission() {
        //Check if permission is granted or not
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.GET_ACCOUNTS},
                    REQUEST_ACCOUNTS_PERMISSION);
        } else {
            fetchAccounts();
        }

    }

    /**
     * method to fetch all device accounts
     */
    private void fetchAccounts() {
        //Get all Google Accounts
        try {
            String googleAccount = "";
            Account[] accounts = AccountManager.get(MainActivity.this).getAccountsByType("com.google");//Use com.google

            //Loop all accounts
            for (Account account : accounts) {
                System.out.println("Google Account Name - " + account.name + " : Account Type - " + account.type + "\n");//Get account name and type
                googleAccount += account.name + "\n";
            }
            googleAccounts.setText(googleAccount);//Set Text

        } catch (Exception e) {
            Log.i("Exception", "Exception:" + e);
        }


        //Get All registered accounts on device
        try {

            String allRegisteredAccounts = "";
            Account[] accounts = AccountManager.get(MainActivity.this).getAccounts();//Get All accounts

            //Loop to all accounts
            for (Account account : accounts)
                allRegisteredAccounts += " Account Name - " + account.name + "\nAccount Type - " + account.type + "\n\n";//get Account name and type


            allAccounts.setText(allRegisteredAccounts);//set text and type
        } catch (Exception e) {
            Log.i("Exception", "Exception:" + e);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ACCOUNTS_PERMISSION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // task you need to do.
                    fetchAccounts();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
