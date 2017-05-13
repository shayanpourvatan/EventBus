package com.shayan.pourvatan.eventbus;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import eventManager.EventListener;
import eventManager.EventManager;

/**
 * Created by shayanpourvatan on 5/13/17.
 */

public class DetailActivity extends AppCompatActivity implements EventListener {

    private final String TAG = "detailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EventManager.getInstance().addEventListener(ApplicationClass.DETAIL_TEST_EVENT_NUM, this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // simulate one delay job from other class.
                EventManager.getInstance().postEvent(ApplicationClass.DETAIL_TEST_EVENT_NUM, "firstObj", "everyThing you want");
            }
        }, 3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventManager.getInstance().removeEventListener(ApplicationClass.DETAIL_TEST_EVENT_NUM, this);
    }

    @Override
    public void receivedMessage(int id, Object... message) {

        switch (id) {
            case ApplicationClass.DETAIL_TEST_EVENT_NUM:

                // data received from detail


                // get data with message object like follow:
                String fistObj = (String) message[0];

                Log.d(TAG, "Data received");

                break;
        }

    }
}
