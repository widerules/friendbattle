package de.passsy.friendbattle.games;

import java.util.Timer;

import android.content.Context;
import android.os.Handler;
import de.passsy.friendbattle.screenlayouts.Screen_TextViewsCenter;
import de.passsy.friendbattle.utility.GoodTimer;
import de.passsy.friendbattle.utility.GoodTimer.OnTimerListener;

public class GuessWhen extends MiniGame {

    private Screen_TextViewsCenter mTextViews;

    Timer mTimer = new Timer();

    private Handler mHandler = new Handler();
    
    private GoodTimer timer;
    
    private OnTimerListener onTimerListener = new OnTimerListener() {
        
	     int i = 0;
	    	int time = (int) Math.round((Math.random() * 6)) + 5;
	    	int max = time;
	
        @Override
        public void onTimer() {
       
    	    time--;

    	    if (time > (int)(max / 2)-1) {
    		mTextViews.setText("" + time);
    	    } else {
    		mTextViews.setText("?");
    	    }
    	    if (time == 0) {
    		setCorrectness(true);
    	    }
    	    if (time == -1) {
    		showStats();
    		timer.stop();
    	    }
    	    
    	
        }
    };
    
    private void showStats() {
	    
    }
    
    public GuessWhen(Context context) {
	super(context);
	mTextViews = new Screen_TextViewsCenter(context);
	this.addView(mTextViews);
	mTextViews.setTextSize(20);
	mTextViews.setText("?");
    }

    @Override
    protected void showIntroductions(int seconds) {

    }


   

    @Override
    public void startGame() {
	int time = (int) Math.round((Math.random() * 1000)) + 500;

	timer = new GoodTimer(time, true);
	
	timer.setOnTimerListener(onTimerListener);
	timer.start();
    }

    @Override
    public CharSequence getDescription() {
	return "Guess when it is";
    }

}
