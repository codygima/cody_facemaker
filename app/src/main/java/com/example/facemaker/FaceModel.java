package com.example.facemaker;

/***
 * @author Cody Gima
 * @version 2/24/23
 */
public class FaceModel {
    // public instance variables to control the color of each variable
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;

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
}
