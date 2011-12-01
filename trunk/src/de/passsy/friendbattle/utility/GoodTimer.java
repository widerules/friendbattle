package de.passsy.friendbattle.utility;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

public class GoodTimer {

    public interface OnTimerListener {
	public abstract void onTimer();
    }

    private OnTimerListener mTimerListener;

    private final Runnable timeoutRunnable = new Runnable() {

	@Override
	public void run() {
	    onTimer();

	}
    };

    private boolean mRepeat = false;

    private int mDelay = 0;

    Timer mTimer = new Timer();

    private Handler mHandler = new Handler();

    private int mTimeout;

    private boolean mRunning;

    public GoodTimer(final int timeout, final boolean repeat) {

	this.mRepeat = repeat;
	this.mTimeout = timeout;
    }

    public void start() {

	if (mRunning) {
	    stop();
	}

	if (mTimeout <= 0) {
	    throw new IllegalArgumentException("timeout can't be 0 or below");
	}

	if (mRepeat) {
	    try {

		mTimer.schedule(new TimerTask() {

		    @Override
		    public void run() {
			mRunning = true;
			mHandler.post(timeoutRunnable);

		    }
		}, mTimeout, mTimeout);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else {
	    mTimer.schedule(new TimerTask() {

		@Override
		public void run() {
		    mRunning = true;
		    mHandler.post(timeoutRunnable);
		}
	    }, mTimeout);
	}

    }

    public void reset(final int timeout) {

	stop();
	start();
    }

    public void stop() {
	if (mRunning) {
	    mTimer.cancel();
	}
	mRunning = false;
    }

    /**
     * EXPERIMENTAL, NOT TESTED pause the timer.
     */
    public void pause() {
	if (mRunning) {
	    try {
		synchronized (mTimer) {
		    mTimer.wait();
		    mRunning = false;
		}
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

    }

    /**
     * EXPERIMENTAL, NOT TESTED resumes the timer
     */
    public void resume() {
	if (!mRunning) {
	    mTimer.notify();
	    mRunning = true;
	}

    }

    public void onTimer() {
	if (mTimerListener != null) {
	    mTimerListener.onTimer();
	}
    }

    public void setOnTimerListener(final OnTimerListener l) {
	mTimerListener = l;
    }

}
