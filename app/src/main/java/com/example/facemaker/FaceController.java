package com.example.facemaker;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/***
 * @author Cody Gima
 * @version 2/14/23
 */
public class FaceController implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    public MainActivity myActivity;
    private FaceView fv;
    private FaceModel fm;

    public FaceController(MainActivity activity, FaceView faceView) {
        myActivity = activity;
        fv = faceView;
        fm = faceView.getFaceModel();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // gets a reference to what the values are
        TextView redText = myActivity.findViewById(R.id.redValue);
        TextView greenText = myActivity.findViewById(R.id.greenValue);
        TextView blueText = myActivity.findViewById(R.id.blueValue);

        // finds the correct seekbar id out of the red, green, and blue seekbars and changes the value
        if (seekBar.getId() == R.id.redSeekBar) {
            redText.setText("" + progress);
        }
        else if (seekBar.getId() == R.id.greenSeekBar) {
            greenText.setText("" + progress);
        }
        else if (seekBar.getId() == R.id.blueSeekBar) {
            blueText.setText("" + progress);
        }
        fv.invalidate();
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        int colorVal;
        // checks which button is selected
        if (checkedId == R.id.radioHair) {
            colorVal = fm.getHairColor();
        }
        else if (checkedId == R.id.radioEyes) {
            colorVal = fm.getEyeColor();
        }
        else if (checkedId == R.id.radioSkin) {
            colorVal = fm.getSkinColor();
        } else {
            // does nothing
            return;
        }
        fv.updateSeekBarProgress(colorVal);

        // Update the SeekBars based on the color value
        int red = Color.red(colorVal);
        int green = Color.green(colorVal);
        int blue = Color.blue(colorVal);

        // Assuming you have three SeekBars for Red, Green, and Blue
        SeekBar redSeekBar = myActivity.findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = myActivity.findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = myActivity.findViewById(R.id.blueSeekBar);

        // Update SeekBars
        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);

        fv.invalidate();
    }


    public void setHairStyle(int styleIndex) {
        fv.getFaceModel().setHairStyle(styleIndex);
        fv.invalidate();
    }

    @Override
    public void onClick(View view) {
        // Check if the clicked view is the random face button
        if (view.getId() == R.id.randomFaceButton) {
            // Generate random values for the face model
            fv.setRandomValues();

            // Update the FaceView to reflect the new values
            fv.invalidate();
        }
    }

    /**
     * Creates the ArrayList of Spinner then assigns it to what happens when a Spinner option is selected
      * @param hairStyleSpinner
     */
    public void setupHairStyleSpinner(Spinner hairStyleSpinner) {
        ArrayList<String> style = new ArrayList<>();
        style.add("Afro");
        style.add("Three-Strand");
        style.add("Box Cut");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(myActivity, android.R.layout.simple_spinner_item, style);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        hairStyleSpinner.setAdapter(adapter);

        // Set the randomly selected item as the selected item in the spinner
        hairStyleSpinner.setSelection(fv.getFaceModel().hairStyle);
        hairStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                setHairStyle(i);
                fv.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // nothing
            }
        });
    }

}
