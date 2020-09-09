package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.controller;

import android.app.Application;

/**
 * Main Application
 */
public class VocabApplication extends Application {

    private static Application context;

    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Application getApplication() {
        return context;
    }
}
