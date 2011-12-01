package de.passsy.friendbattle.utility;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class GoodTimer {

    private final Runnable timeoutRunnable;

    private boolean mRepeat = false;

    private int mDelay = 0;

    Timer mTimer = new Timer();

    private Handler mHandler = new Handler();

    private int mTimeout;

    private boolean mRunning;

    public GoodTimer(final Runnable timeoutRunnable, final int timeout,
	    final int delay, final boolean repeat) {

	this.timeoutRunnable = timeoutRunnable;
	this.mRepeat = repeat;
	this.mDelay = delay;
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
		}, mTimeout + mDelay, mTimeout);
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
	    }, mTimeout + mDelay);
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

    public void pause() {
	if (mRunning) {
	    try {
		synchronized (mTimer) {
		    mTimer.wait();
		}
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	mRunning = false;
    }

}
