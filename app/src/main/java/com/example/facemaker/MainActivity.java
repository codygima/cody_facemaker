package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creates face class to register events
        FaceView face = findViewById(R.id.surfaceView);
        FaceController fc = new FaceController(this, face);

        // finds each of the seekbars
        SeekBar rSB = findViewById(R.id.redSeekBar);
        SeekBar gSB = findViewById(R.id.greenSeekBar);
        SeekBar bSB = findViewById(R.id.blueSeekBar);

        // turns on the seekbar listener
        rSB.setOnSeekBarChangeListener(fc);
        gSB.setOnSeekBarChangeListener(fc);
        bSB.setOnSeekBarChangeListener(fc);

        // finds the radio buttons
        RadioGroup radioGroup = findViewById(R.id.RG_Features);
        radioGroup.setOnCheckedChangeListener(fc);

        // finds the random face button
        Button randomFaceButton = findViewById(R.id.randomFaceButton);
        randomFaceButton.setOnClickListener(fc);

        // finds the spinner and sets it up
        Spinner hairStyleSpinner = findViewById(R.id.hairStyleSpinner);
        fc.setupHairStyleSpinner(hairStyleSpinner);
    }

}
