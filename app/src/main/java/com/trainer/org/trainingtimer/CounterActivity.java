package com.trainer.org.trainingtimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CounterActivity extends AppCompatActivity {

    CountDownTimer timer;
    TextView statusText;
    TextView countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // the UI elements
        statusText = (TextView) findViewById(R.id.textView2);
        countdown = (TextView) findViewById(R.id.textView3);

        // Get the intent that started this activity and extract the string
        Intent intent = getIntent();
        String period = intent.getStringExtra(MainActivity.TRAINER_PERIOD);

        // start the timer
        setupCountDownTimer(Integer.parseInt(period)*1000, 1000);
        statusText.setText("Work!");
        timer.start();
    }

    private void setupCountDownTimer(long millisInFuture, long countDownInterval) {
        timer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("app", "timer > onTick()");
                countdown.setText("" + (millisUntilFinished / 1000 + 1));
            }

            @Override
            public void onFinish() {
                Log.d("app", "timer > onFinish()");
                countdown.setText(0 + "");
                statusText.setText("DONE!");
            }
        };
    }
}
