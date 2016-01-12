package com.marsshotinteractive.numtar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HowToPlay extends AppCompatActivity {

    private ImageButton menuImageButton;
    private TextView toolBarTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        toolBarTitleTextView = (TextView)findViewById(R.id.toolbarModeText);
        toolBarTitleTextView.setText("How To Play");

        menuImageButton = (ImageButton)findViewById(R.id.backImageButton);
        menuImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(HowToPlay.this, Numtar.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}