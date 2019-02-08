package com.getaccounts_demo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static TextView googleAccounts, allAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleAccounts = (TextView) findViewById(R.id.googleAccounts);
        allAccounts = (TextView) findViewById(R.id.allAccounts);


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

}
