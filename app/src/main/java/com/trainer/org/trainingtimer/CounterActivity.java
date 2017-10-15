package com.trainer.org.trainingtimer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CounterActivity extends AppCompatActivity {

    CountDownTimer workTimer;
    CountDownTimer restTimer;
    TextView statusText;
    TextView countdown;
    ConstraintLayout cLayout;
    int reps;

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
        statusText = (TextView) findViewById(R.id.motivText);
        countdown = (TextView) findViewById(R.id.countdownText);
        cLayout = (ConstraintLayout) findViewById(R.id.counter_content_layout_id);

        // Get the intent that started this activity and extract the data
        String workSec = getIntent().getStringExtra(MainActivity.WORK_PERIOD);
        String restSec = getIntent().getStringExtra(MainActivity.REST_PERIOD);
        String repetitions = getIntent().getStringExtra(MainActivity.REPETITIONS);

        // handle empty string cases etc.
        // todo

        // setup timers and trigger the loop
        workTimer = setupWorkTimer(Integer.parseInt(workSec)*1000, 1000);
        restTimer = setupRestTimer(Integer.parseInt(restSec)*1000, 1000);
        reps = Integer.parseInt(repetitions);
        resumeCountDownLoop();
    }

    public void resumeCountDownLoop() {
        if (reps > 0) {
            reps--;
            cLayout.setBackgroundColor(Color.parseColor("green"));
            workTimer.start();
        } else {
            statusText.setText("Done!");
        }
    }

    private CountDownTimer setupWorkTimer(long millisInFuture, long countDownInterval) {
        return new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("app", "workTimer > onTick()");
                statusText.setText("Work!");
                countdown.setText("" + (millisUntilFinished / 1000 + 1));
            }

            @Override
            public void onFinish() {
                Log.d("app", "workTimer > onFinish()");
                countdown.setText(0 + "");
                cLayout.setBackgroundColor(Color.parseColor("blue"));
                restTimer.start();
            }
        };
    }

    private CountDownTimer setupRestTimer(long millisInFuture, long countDownInterval) {
        return new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("app", "restTimer > onTick()");
                statusText.setText("Rest...");
                countdown.setText("" + (millisUntilFinished / 1000 + 1));
            }

            @Override
            public void onFinish() {
                Log.d("app", "restTimer > onFinish()");
                countdown.setText(0 + "");
                resumeCountDownLoop();
            }
        };
    }
}
