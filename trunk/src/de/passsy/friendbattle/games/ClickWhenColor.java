package de.passsy.friendbattle.games;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;

public class ClickWhenColor extends MiniGame {

    private final Timer timer = new Timer();
    private final Handler mHandler = new Handler();

    final Runnable mFireTimer = new Runnable() {
	@Override
	public void run() {
	    Action();
	}
    };

    public ClickWhenColor(final Context context) {
	super(context);
    }

    public ClickWhenColor(final Context context, final AttributeSet attrs) {
	super(context, attrs);
    }

    private void Action() {
	setBackgroundColor(Color.RED);
	setCorrectness(true);
    }

    @Override
    protected void showIntroductions(final int seconds) {

    }

    @Override
    public void startGame() {
	final int time = (int) Math.round((Math.random() * 10000));

	timer.schedule(new TimerTask() {

	    @Override
	    public void run() {
		mHandler.post(mFireTimer);
	    }
	}, time);

    }

    @Override
    public CharSequence getDescription() {
	return "Click if the color is other then white";
    }

}
