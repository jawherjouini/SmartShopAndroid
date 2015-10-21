package tn.esprit.autoidsys.smartshop.utils;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

/**
 * Created by Jawher on 19/10/2015.
 */
public class SmartShopApplication extends Application {
    private static SmartShopApplication smartShopApplication;

    public static Context getContext() {
        return smartShopApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        smartShopApplication = this;
        smartShopApplication.initializeInstance();
    }

    //Configure locally for each project
    private void initializeInstance() {
        //Register Custom Parse Objects (mapped to the backend Classes)
        // Enable Local Datastore
        Parse.enableLocalDatastore(this);
        // Add your initialization code here
        Parse.initialize(this, "z7QffxJLRKIucW67A7eGrALrp0aOU6i2nwcqC1hU", "2FmEX5GnzTmyd7EwtVdDtoyseoFT24KCnKaipIks");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }


    @Override
    public void onTerminate() {
        // Do your application wise Termination task
        super.onTerminate();
    }
}
