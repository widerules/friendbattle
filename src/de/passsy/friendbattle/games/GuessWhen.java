package de.passsy.friendbattle.games;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.view.LayoutInflater;
import de.passsy.friendbattle.R;
import de.passsy.friendbattle.screenlayouts.Screen_TextViewsCenter;
import de.passsy.friendbattle.utility.Tools;

public class GuessWhen extends MiniGame {

    private Screen_TextViewsCenter mTextViews = new Screen_TextViewsCenter();

    Timer mTimer = new Timer();

    private Handler mHandler = new Handler();

    private Runnable UpdateScreen = new Runnable() {
	int i = 0;
	int time = (int) Math.round((Math.random() * 6)) + 5;
	int max = time;

	@Override
	public void run() {
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
		stopTimer();
	    }
	    

	}

	private void showStats() {
	    	    
	}
    };



    public GuessWhen() {
	this.addView(mTextViews);
	mTextViews.setTextSize(20);
	mTextViews.setText("?");
    }

    @Override
    protected void showIntroductions(int seconds) {

    }


    private void stopTimer() {
	mTimer.purge();
	mTimer.cancel();
	

    }

    @Override
    public void startGame() {
	int time = (int) Math.round((Math.random() * 1000)) + 500;

	startTimer(0, time, true, UpdateScreen);
    }

    private void startTimer(int delay, int time, boolean repeat,
	    final Runnable task) {
	// stops timer if running
	// mTimer.cancel();

	// starts the timer
	if (repeat) {
	    mTimer.schedule(new TimerTask() {

		@Override
		public void run() {
		    mHandler.post(task);

		}
	    }, time + delay, time);
	} else {
	    mTimer.schedule(new TimerTask() {

		@Override
		public void run() {
		    mHandler.post(task);

		}
	    }, time + delay);
	}

    }

    @Override
    public CharSequence getDescription() {
	return "Guess when it is";
    }

}
