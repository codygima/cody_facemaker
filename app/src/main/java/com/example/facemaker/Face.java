package com.example.facemaker;

import android.graphics.Canvas;
import android.graphics.Color;

/***
 * @author Cody Gima
 * @version 2/14/23
 */
public class Face {

    // public instance variables to control the color of each variable
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;

    // general constructor that sets instance variables to random values
    public Face() {
        this.skinColor = Color.rgb(randomize(), randomize(), randomize());
        this.eyeColor = Color.rgb(randomize(), randomize(), randomize());
        this.hairColor = Color.rgb(randomize(), randomize(), randomize());
        this.hairStyle = randomHairStyle();
    }

    // gets a random number from 0 to 255
    public int randomize() {
        return (int) (Math.random() * 256);
    }

    // generates a random hair style value between 1 and 3
    public int randomHairStyle() {
        return (int) (Math.random() * 3) + 1;
    }

    // draws the face object
    public void onDraw(Canvas c) {
        // to be implemented
    }

    // getter methods for the instance variables
    public int getSkinColor() {
        return this.skinColor;
    }
    public int getEyeColor() {
        return this.eyeColor;
    }
    public int getHairColor() {
        return this.hairColor;
    }
    public int getHairStyle() {
        return this.hairStyle;
    }
}
