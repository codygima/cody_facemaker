package com.example.facemaker;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/***
 * @author Cody Gima
 * @version 2/24/23
 */
public class FaceController implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    // instance variables
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

        //extract the red, green and blue numbers from the seekbar
        String redNumStr = redText.getText().toString();
        int redNum = Integer.parseInt(redNumStr);
        String greenNumStr = greenText.getText().toString();
        int greenNum = Integer.parseInt(greenNumStr);
        String blueNumStr = blueText.getText().toString();
        int blueNum = Integer.parseInt(blueNumStr);

        int newColor = Color.rgb(redNum, greenNum, blueNum);

        //find out which radio button is checked
        RadioGroup rg = myActivity.findViewById(R.id.RG_Features);
        if (rg.getCheckedRadioButtonId() == R.id.radioSkin) {
            fm.skinColor = newColor;
        }
        else if (rg.getCheckedRadioButtonId() == R.id.radioEyes) {
            fm.eyeColor = newColor;
        }
        else if (rg.getCheckedRadioButtonId() == R.id.radioHair) {
            fm.hairColor = newColor;
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

        // updates the seekbar
        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);

        fv.invalidate();
    }

    /**
     * Sets the hair style based on the given int option, 1-Afro, 2-Three-Strand, 3-Box Cut
     * @param styleIndex
     */
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
     * Determines which option was selected in the Spinner and sets the hair style to such
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item is selected. You can retrieve the selected item using
        String item = (String) parent.getItemAtPosition(pos);

        if (item.equals("Afro")) {
            setHairStyle(1);
        }
        else if (item.equals("Three-Strand")) {
            setHairStyle(2);
        }
        else if (item.equals("Box Cut")) {
            setHairStyle(3);
        }
        fv.invalidate();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // nothing
    }
} // FaceController
