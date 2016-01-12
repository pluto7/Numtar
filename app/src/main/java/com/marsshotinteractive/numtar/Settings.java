package com.marsshotinteractive.numtar;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class Settings extends AppCompatActivity {

    private ImageButton backImageButton;
    private Button nightThemeButton;
    private Button orangeThemeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backImageButton = (ImageButton)findViewById(R.id.backImageButton);
        backImageButton.setOnClickListener(backImageButtonListener);

        nightThemeButton = (Button)findViewById(R.id.nightThemeButton);
        nightThemeButton.setOnClickListener(nightThemeButtonListener);

        orangeThemeButton = (Button)findViewById(R.id.orangeThemeButton);
        //orangeThemeButton.setOnClickListener(orangeThemeButtonListener);
    }

    public OnClickListener backImageButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent homeIntent = new Intent(Settings.this, Numtar.class);
            startActivity(homeIntent);
            finish();
        }
    };

    public OnClickListener nightThemeButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
