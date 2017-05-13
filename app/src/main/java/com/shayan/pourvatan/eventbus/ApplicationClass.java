package com.shayan.pourvatan.eventbus;

import android.app.Application;
import android.util.Log;

import eventManager.ErrorHandler;
import eventManager.EventListener;
import eventManager.EventManager;

import java.util.Locale;

/**
 * Created by shayanpourvatan on 5/13/17.
 */

public class ApplicationClass extends Application implements EventListener {

    public static final int MAIN_TEST_EVENT_NUM = 1;
    public static final int DETAIL_TEST_EVENT_NUM = 2;

    private final String EVENT_TAG = "EventManager";
    private final String TAG = "Application";


    @Override
    public void onCreate() {
        super.onCreate();

        ErrorHandler errorHandler = new ErrorHandler() {
            @Override
            public void onFailure(Throwable e) {
                Log.e(EVENT_TAG, "error in onMessageReceived happened", e);
            }
        };


        // build EventManager object in your project.
        // you can don't do this, in default we create one eventManager object with 3 threadPool size
        new EventManager.Builder().setThreadPoolSize(3)
                .setErrorHandler(errorHandler)
                .enableLoggingInDebug()
                .build();


        // register this class to listen on TEST_EVENT_NUM.
        EventManager.getInstance().addEventListener(MAIN_TEST_EVENT_NUM, this);

    }

    @Override
    public void receivedMessage(int id, Object... message) {

        switch (id) {
            case MAIN_TEST_EVENT_NUM:

                String firstObj = (String) message[0];
                String secondObj = (String) message[1];
                int thirdObj = (int) message[2];

                Log.d(TAG, String.format(Locale.getDefault(), "receive following objects: \n1- %s \n2- %s\n3- %d", firstObj, secondObj, thirdObj));
                break;
        }

    }
}
