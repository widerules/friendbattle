package de.passsy.friendbattle.data;

import android.graphics.Color;
import de.passsy.friendbattle.controls.Buzzer;

public class Player {
    private int mId;
    private int mPoints = 0;
    private Buzzer mBuzzer;
    private Color mColor;

    /**
     * 
     * @param id
     *            ID for this player
     */
    public Player(final int id) {
	mId = id;
    }

    public int getId() {
	return mId;
    }

    public void setId(final int id) {
	mId = id;
    }

    public int getPoints() {
	return mPoints;
    }

    public void setPoints(final int points) {
	mPoints = points;
	mBuzzer.setText(String.valueOf(points));
    }

    public Buzzer getBuzzer() {
	return mBuzzer;
    }

    public void setBuzzer(final Buzzer buzzer) {
	mBuzzer = buzzer;
    }

    public Color getColor() {
	return mColor;
    }

    public void setColor(final Color mColor) {
	this.mColor = mColor;
    }

}
