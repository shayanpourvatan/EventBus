package com.shayan.pourvatan.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import eventManager.EventManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.main_et);
        findViewById(R.id.main_passData).setOnClickListener(this);
        findViewById(R.id.main_detail).setOnClickListener(this);
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

                break;
        }
    }
}
