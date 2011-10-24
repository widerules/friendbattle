package de.passsy.friendbattle.games;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import de.passsy.friendbattle.Player;
import de.passsy.friendbattle.utility.Tools;

public class ClickWhenWhite extends MiniGame{
    
    final Handler mHandler = new Handler();
    Timer timer = new Timer();
    
    final Runnable mFireTimer = new Runnable() {
        public void run() {
            Action();
        }
    };
    
    public ClickWhenWhite(){
	this(Tools.getContext());
    }

    public ClickWhenWhite(Context context) {
	super(context);
    }
    
    public ClickWhenWhite(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    @Override
    public void start() {
	super.start();
	
	int time =  (int) Math.round((Math.random()*7000));
	
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
	setBackgroundColor(Color.WHITE);
	setCorrectness(true);
    }

    @Override
    public void reset() {
	setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void showIntroductions(int seconds) {
	
    }

}
