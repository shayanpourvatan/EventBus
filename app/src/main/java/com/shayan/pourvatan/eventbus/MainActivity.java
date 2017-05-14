package com.shayan.pourvatan.eventbus;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import eventManager.EventManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText editText;
    TextView textView;

    Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.main_et);
        textView = (TextView) findViewById(R.id.main_tv);

        findViewById(R.id.main_passData).setOnClickListener(this);
        findViewById(R.id.main_detail).setOnClickListener(this);

        uiHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_detail:
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
                break;


            case R.id.main_passData:

                EventManager.getInstance().postEvent(ApplicationClass.MAIN_TEST_EVENT_NUM,
                        editText.getText().toString(),
                        "another data",
                        12); // you can pass any data


                // use one of following way to update UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(String.format(Locale.getDefault(), "EditText value is : %s" , editText.getText().toString()));
                    }
                });

//                uiHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(String.format(Locale.getDefault(), "EditText value is : %s" , editText.getText().toString()));
//                    }
//                });

                break;
        }
    }
}
