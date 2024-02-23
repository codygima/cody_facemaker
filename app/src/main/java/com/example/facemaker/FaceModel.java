package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.TextView;

/***
 * @author Cody Gima
 * @version 2/14/23
 */
public class FaceModel {
    // public instance variables to control the color of each variable
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;
    FaceView faceView;

    // getter methods to get values
    public int getSkinColor() {
        return this.skinColor;
    }

    public int getEyeColor() {
        return this.eyeColor;
    }

    public int getHairColor() {
        return this.hairColor;
    }

    public int getHairStyleIndex() {
        return hairStyle;
    }

    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public void setHairStyle(int hairStyleIndex) {
        hairStyle = hairStyleIndex;
    }

    public int getGreen() {
        return Color.green(eyeColor);
    }

    public int getBlue() {
        return Color.blue(eyeColor);
    }

    public int getRed() {
        return Color.red(eyeColor);
    }
}
