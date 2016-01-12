package com.marsshotinteractive.numtar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class Numtar extends AppCompatActivity {

    private Button normalModeButton;
    private Button continuousModeButton;
    private Button hiddenModeButton;
    private Button howPlayButton;
    private ImageButton settingsImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numtar);

        normalModeButton = (Button)findViewById(R.id.normalModeButton);
        normalModeButton.setOnClickListener(normalModeButtonListener);

        continuousModeButton = (Button)findViewById(R.id.continuousModeButton);
        continuousModeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continuousModeIntent = new Intent(Numtar.this, ContinuousMode.class);
                startActivity(continuousModeIntent);
                finish();
            }
        });

        hiddenModeButton = (Button)findViewById(R.id.hiddenModeButton);
        hiddenModeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hiddenModeIntent = new Intent(Numtar.this, HiddenMode.class);
                startActivity(hiddenModeIntent);
                finish();
            }
        });

        howPlayButton = (Button)findViewById(R.id.howToPlayButton);
        howPlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent howToPlayIntent = new Intent(Numtar.this, HowToPlay.class);
                startActivity(howToPlayIntent);
                finish();
            }
        });
        settingsImageButton = (ImageButton)findViewById(R.id.settingsImageButton);
        settingsImageButton.setOnClickListener(settingsImageButtonListener);
    }

    //listener for Settings button
    public OnClickListener settingsImageButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent settingsIntent = new Intent(Numtar.this, Settings.class);
            startActivity(settingsIntent);
            finish();
        }
    };

    //listener for normalModeButton
    public OnClickListener normalModeButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent normalModeIntent = new Intent(Numtar.this, NormalMode.class);
            startActivity(normalModeIntent);
            finish();
        }
    };
}
