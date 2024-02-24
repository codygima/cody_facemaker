package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/***
 * @author Cody Gima
 * @version 2/24/23
 */
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

        /**
         External Citation
         Date: 22 February 2024
         Problem: Needed to add a spinner and how to implement which option was selected.
         Resource:
         https://developer.android.com/develop/ui/views/components/spinner
         Solution: I used Android Studio's example code to help add the spinner
         */
        Spinner spinner = findViewById(R.id.hairStyleSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hairStylesArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        // finds the spinner and sets it up
        Spinner styleSpinner = findViewById(R.id.hairStyleSpinner);
        styleSpinner.setOnItemSelectedListener(fc);
    }

}
