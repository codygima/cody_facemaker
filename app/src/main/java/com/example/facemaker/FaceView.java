package com.example.facemaker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

/***
 * @author Cody Gima
 * @version 2/24/23
 */
public class FaceView extends SurfaceView {
    // instance variables
    public FaceModel faceModel;
    public MainActivity myActivity;
    Paint myPaint = new Paint();

    // general constructor that sets instance variables to random values
    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // initializes the FaceModel variables
        faceModel = new FaceModel();
        faceModel.skinColor = Color.rgb(randomize(), randomize(), randomize());
        faceModel.eyeColor = Color.rgb(randomize(), randomize(), randomize());
        faceModel.hairColor = Color.rgb(randomize(), randomize(), randomize());
        faceModel.hairStyle = randomHairStyle();

        setWillNotDraw(false);
    }

    @Override
    public void onDraw(Canvas c) {
        drawFace(c);
    }

    /**
     * draws the full face on the SurfaceView
     * @param canvas
     */
    private void drawFace(Canvas canvas) {
        if (faceModel != null) {
            // draws face
            canvas.drawColor(Color.WHITE); // sets background color
            myPaint.setColor(faceModel.getSkinColor());
            // draws the head at half way mark of the surfaceview
            canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 250, myPaint);

            // draws eyes
            myPaint.setColor(faceModel.getEyeColor());
            // draws the eyes in the half of the head
            canvas.drawCircle((canvas.getWidth() / 2) - 125, (canvas.getHeight() / 2) - 100, 50, myPaint);
            canvas.drawCircle((canvas.getWidth() / 2) + 125, (canvas.getHeight() / 2) - 100, 50, myPaint);
            myPaint.setColor(Color.BLACK);
            canvas.drawCircle((canvas.getWidth() / 2) - 125, (canvas.getHeight() / 2) - 100, 20, myPaint);
            canvas.drawCircle((canvas.getWidth() / 2) + 125, (canvas.getHeight() / 2) - 100, 20, myPaint);


            // draws the random hair
             myPaint.setColor(faceModel.getHairColor());
             // draws the afro hair
             if (faceModel.getHairStyleIndex() == 1) {
                 int headRadius = 100;
                 int circleRadius = 50;

                 canvas.drawCircle(canvas.getWidth() / 2, (canvas.getHeight() / 2) - headRadius - 2 * circleRadius, circleRadius, myPaint);
                 canvas.drawCircle((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) - headRadius - 2 * circleRadius, circleRadius, myPaint);
                 canvas.drawCircle((canvas.getWidth() / 2) - 80, (canvas.getHeight() / 2) - headRadius - 2 * circleRadius, circleRadius, myPaint);
                 canvas.drawCircle((canvas.getWidth() / 2) + 60, (canvas.getHeight() / 2) - headRadius - 2 * circleRadius, circleRadius, myPaint);
             }
             // draws the three-strand hair
             if (faceModel.getHairStyleIndex() == 2) {
                 canvas.drawRect(canvas.getWidth() / 2 - 125, canvas.getHeight() / 2 - 300, canvas.getWidth() / 2 - 100, canvas.getHeight() / 2 - 175, myPaint);
                 canvas.drawRect(canvas.getWidth() / 2 + 10, canvas.getHeight() / 2 - 300, canvas.getWidth() / 2 - 15, canvas.getHeight() / 2 - 175, myPaint);
                 canvas.drawRect(canvas.getWidth() / 2 + 125, canvas.getHeight() / 2 - 300, canvas.getWidth() / 2 + 150, canvas.getHeight() / 2 - 175, myPaint);
             }

             }
             // draws a box cut
             if (faceModel.getHairStyleIndex() == 3) {
            canvas.drawRect(canvas.getWidth() / 2 - 125, canvas.getHeight() / 2 - 300, canvas.getWidth() / 2 + 125, canvas.getHeight() / 2 - 175, myPaint);
             }
        }


    // gets a random number from 0 to 255
    public int randomize() {
        return (int) (Math.random() * 256);
    }

    // generates a random hair style value between 1 and 3
    public int randomHairStyle() {
        return (int) (Math.random() * 3) + 1;
    }

    // returns the FaceModel object
    public FaceModel getFaceModel() {
        return this.faceModel;
    }

    public void updateSeekBarProgress(int colorValue) {
        // Update SeekBars based on the color value
        int red = Color.red(colorValue);
        int green = Color.green(colorValue);
        int blue = Color.blue(colorValue);

        // Assuming you have three SeekBars for Red, Green, and Blue
        SeekBar redSeekBar = ((Activity) getContext()).findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = ((Activity) getContext()).findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = ((Activity) getContext()).findViewById(R.id.blueSeekBar);

        // Update SeekBars
        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);

        // Update FaceModel based on the color value
        RadioGroup radioGroup = ((Activity) getContext()).findViewById(R.id.RG_Features);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        if (checkedRadioButtonId == R.id.radioHair) {
            faceModel.setHairColor(colorValue);
        } else if (checkedRadioButtonId == R.id.radioEyes) {
            faceModel.setEyeColor(colorValue);
        } else if (checkedRadioButtonId == R.id.radioSkin) {
            faceModel.setSkinColor(colorValue);
        }

        // Redraw the view
        invalidate();
    }


    public void setRandomValues() {
        if (this != null) {
            faceModel.skinColor = Color.rgb(this.randomize(), this.randomize(), this.randomize());
            faceModel.eyeColor = Color.rgb(this.randomize(), this.randomize(), this.randomize());
            faceModel.hairColor = Color.rgb(this.randomize(), this.randomize(), this.randomize());
            faceModel.hairStyle = this.randomHairStyle();
        }
    }
}
