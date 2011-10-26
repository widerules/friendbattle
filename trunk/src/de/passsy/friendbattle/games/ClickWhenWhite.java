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
    
    private final Handler mHandler = new Handler();
    private Timer timer = new Timer();
    
    private final Runnable mFireTimer = new Runnable() {
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
	
	int time =  (int) Math.round((Math.random()*4000))+2000;
	
            timer.schedule(new TimerTask() {
        	    
                @Override
                public void run() {
                    mHandler.post(mFireTimer);
                }
            }, time);
	
	
    }
    
    private void Action() {
	setBackgroundColor(Color.WHITE);
	setCorrectness(true);
    }

    @Override
    protected void showIntroductions(int seconds) {
	
    }

}
