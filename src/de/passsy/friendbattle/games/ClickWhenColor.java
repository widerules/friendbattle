package de.passsy.friendbattle.games;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import de.passsy.friendbattle.Player;
import de.passsy.friendbattle.utility.Tools;

public class ClickWhenColor extends MiniGame{
    
    final Handler mHandler = new Handler();
    Timer timer = new Timer();
    
    final Runnable mFireTimer = new Runnable() {
        public void run() {
            Action();
        }
    };
    
    public ClickWhenColor(){
	this(Tools.getContext());
    }

    public ClickWhenColor(Context context) {
	super(context);
    }
    
    public ClickWhenColor(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    @Override
    public void start() {
	super.start();
	
	int time = 4000;
	
            timer.schedule(new TimerTask() {
        	    
                @Override
                public void run() {
                    mHandler.post(mFireTimer);
                }
            }, time);
	
	
    }

    @Override
    public void end() {
	
    }
    
    private void Action() {
	setBackgroundColor(Color.RED);
	setCorrectness(true);
    }

    @Override
    public void reset() {
	setBackgroundColor(Color.BLACK);
	setRounds(getRounds()-1);
	start();
    }

    @Override
    protected void showIntroductions(int seconds) {
	
    }

}
