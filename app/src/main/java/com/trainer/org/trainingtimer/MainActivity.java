package com.trainer.org.trainingtimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String WORK_PERIOD = "TrainerPeriod";
    public static final String REST_PERIOD = "RestPeriod";
    public static final String REPETITIONS = "Repetitions";

    EditText workInput;
    EditText restInput;
    EditText repsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        workInput = (EditText) findViewById(R.id.workInput);
        restInput = (EditText) findViewById(R.id.restInput);
        repsInput = (EditText) findViewById(R.id.repsInput);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // This method is attached to a button in content_main.xml
    public void startCounter(View view) {
        Intent intent = new Intent(this, CounterActivity.class);

        String workSec = workInput.getText().toString();
        String restSec = restInput.getText().toString();
        String reps = repsInput.getText().toString();

        intent.putExtra(WORK_PERIOD, workSec);
        intent.putExtra(REST_PERIOD, restSec);
        intent.putExtra(REPETITIONS, reps);

        startActivity(intent);
    }

}
