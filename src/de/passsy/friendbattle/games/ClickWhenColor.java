package de.passsy.friendbattle.games;

import java.util.Timer;
import java.util.TimerTask;

import de.passsy.friendbattle.controllers.FirstGets;
import de.passsy.friendbattle.controllers.PointProvider;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;

public class ClickWhenColor extends MiniGame{
    
    private Timer timer = new Timer();
    private final Handler mHandler = new Handler();
    
    
    final Runnable mFireTimer = new Runnable() {
        public void run() {
            Action();
        }
    };

    public ClickWhenColor(Context context) {
	super(context);
    }
    
    public ClickWhenColor(Context context, AttributeSet attrs) {
	super(context, attrs);
    }
    
    private void Action() {
	setBackgroundColor(Color.RED);
	setCorrectness(true);
    }

    @Override
    protected void showIntroductions(int seconds) {
	
    }

    @Override
    public void startGame() {
	int time = (int) Math.round((Math.random()*10000));
	
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
