package com.marsshotinteractive.numtar;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.app.AlertDialog;

public class NormalMode extends AppCompatActivity{

    private ImageButton backImageButton;
    private ImageButton plusImageButton;
    private ImageButton minusImageButton;
    private TextView timerTextView;
    private TextView numGoalTextView;
    private TextView currentTotalTextView;
    private TextView numToChooseTextView;
    private TextView toolbarModeTextView;
    private TextView turnTextView;
    private Random rand;
    private int goalNumber = 0;
    private int choiceNumber = 0;
    private int currentTotal = 0;
    private int turnNumber = 0;
    private boolean timeUp = false;
    private CountDownTimer countDownTimer;
    private GestureDetectorCompat mDetector;
    private AlertDialog.Builder builder;
    private AlertDialog alert;

    private final String SECONDSFORMAT = ":%02d";
    private static final String DEBUG_TAG = "Gestures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_view);

        builder = new AlertDialog.Builder(this);

        timerTextView = (TextView) findViewById(R.id.mainTimeTextView);
        numGoalTextView = (TextView) findViewById(R.id.numGoalTextView);
        currentTotalTextView = (TextView) findViewById(R.id.currentTotalTextView);
        numToChooseTextView = (TextView) findViewById(R.id.numToChooseTextView);
        toolbarModeTextView = (TextView) findViewById(R.id.toolbarModeText);
        turnTextView = (TextView) findViewById(R.id.turnTextView);
        rand = new Random();
        mDetector = new GestureDetectorCompat(this, simpleOnGestureListener);

        plusImageButton = (ImageButton) findViewById(R.id.addImageButton);
        plusImageButton.setOnClickListener(plusImageButtonListener);

        minusImageButton = (ImageButton) findViewById(R.id.minusImageButton);
        minusImageButton.setOnClickListener(minusImageButtonListener);

        backImageButton = (ImageButton) findViewById(R.id.backImageButton);
        backImageButton.setOnClickListener(backImageButtonListener);

        currentTotalTextView.setText(String.valueOf(currentTotal));
        turnTextView.setText(String.valueOf(turnNumber));
        toolbarModeTextView.setText("Normal Mode");
        generateGoalNumber();
        generateChoiceNumber();

        countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.format(SECONDSFORMAT, millisUntilFinished / 1000));
            }

            public void onFinish() {
                timerTextView.setText(":00");
                timeUp = true;
                builder.setMessage("Time is up!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                goBackToHome();
                            }
                        });
                alert = builder.create();
                alert.show();
            }
        }.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //used to detected fling motion to change the choice number
    SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener()
    {
        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
            generateChoiceNumber();
            return true;
        }
    };

    //uses random object to generate a random number between 50 and 100 that will be used for the goal number
    private void generateGoalNumber() {
        //goalNumber = rand.nextInt(100 - 50 + 1) + 50;
        goalNumber = rand.nextInt(100 - (-100) +1) + -100;
        //set the goalNumber to show in the numGoalTextView
        numGoalTextView.setText(String.valueOf(goalNumber));
    }

    //uses random object to generate a random number between 1 and 10 that will be used for the choice number
    private void generateChoiceNumber() {
        choiceNumber = rand.nextInt(10 - 1 + 1) + 1;
        //set the goalNumber to show in the numGoalTextView
        numToChooseTextView.setText(String.valueOf(choiceNumber));
    }

    //determine if the user has reached end game
    private void endGame()
    {
        //check if the total number equals the goal number and the timer is not finished, game is won
        if(currentTotal == goalNumber && timeUp == false)
        {
            countDownTimer.cancel();
            builder.setMessage("You Won!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            goBackToHome();
                        }
                    });
            alert = builder.create();
            alert.show();
        }
    }

    //update the turnNumber and display it
    private void updateTurnNumber()
    {
        turnNumber++;
        turnTextView.setText(String.valueOf(turnNumber));
    }

    private void goBackToHome()
    {
        countDownTimer.cancel();
        Intent homeIntent = new Intent(NormalMode.this, Numtar.class);
        startActivity(homeIntent);
        finish();
    }

    public OnClickListener backImageButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            goBackToHome();
        }
    };

    //listener for when the plusImageButton is clicked
    public OnClickListener plusImageButtonListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            //add the choice number value to the total number value
            //display in the currentTotalTextView
            currentTotal = currentTotal + choiceNumber;
            currentTotalTextView.setText(String.valueOf(currentTotal));
            //check if end game
            endGame();

            //call the generateChoiceNumber to give the user a new number to choose
            generateChoiceNumber();
            //update the turnNumber
            updateTurnNumber();
        }
    };

    //listener for when the minusImageButton is clicked
    public OnClickListener minusImageButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //subtract the choice number value from the total number value
            //display in the currentTotalTextView
            currentTotal = currentTotal - choiceNumber;
            currentTotalTextView.setText(String.valueOf(currentTotal));
            //check if end game
            endGame();

            //call the generateChoiceNumber to give the user a new number to choose
            generateChoiceNumber();
            //update the turnNumber
            updateTurnNumber();
        }
    };
}



