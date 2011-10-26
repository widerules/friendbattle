package de.passsy.friendbattle.games;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import de.passsy.friendbattle.Player;
import de.passsy.friendbattle.utility.Tools;

public abstract class MiniGame extends RelativeLayout{
    
    public interface OnNextGameListener {
	public abstract void onNextGame(MiniGame game);
    }
    private static final int HOWTO_TIME = 5;
    private OnNextGameListener NextGameListener;
    
    private boolean mCorrectness = false;
    private boolean mSolved = false;
    
    private Timer mNewGameTimer = new Timer();
    private final Handler mHandler = new Handler();
    
    
    final Runnable mFireTimer = new Runnable() {
        public void run() {
            if (NextGameListener != null) {
	        NextGameListener.onNextGame(MiniGame.this);
	    }
        }
    };
    
    private boolean isSolved() {
        return mSolved;
    }

    private void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    private int mRounds;
    
    public int getRounds() {
        return mRounds;
    }

    public void setRounds(int mRounds) {
        this.mRounds = mRounds;
    }

    public boolean getCorrectness() {
        return mCorrectness;
    }

    public void setCorrectness(boolean isCorrect) {
        this.mCorrectness = isCorrect;
    }

    public MiniGame(){
	this(Tools.getContext());
    }
    
    public MiniGame(Context context) {
	super(context);
    }
    
    public MiniGame(Context context, AttributeSet attrs) {
	super(context, attrs);
    }
    /**
     * Standard GuessManagement.
     * @param player
     */
    public void onGuess(Player player){
	if (getCorrectness()) {
	    
	    player.getBuzzer().setCorrectBuzz(true);
	    if(!isSolved()){
		player.setPoints(player.getPoints()+1);
	    }
	    setSolved(true);
	    mNewGameTimer.schedule(new TimerTask() {
	        
	        @Override
	        public void run() {
	            mHandler.post(mFireTimer);
	        }
	    }, 1000);
	    
	} else {
	    if(!mSolved){
		player.setPoints(player.getPoints()-1);
	    }
	}
    };
    
    public void start(){
	showIntroductions(HOWTO_TIME);
    };
    
    /**
     * shows the introduction at the beginning of every Game
     * @param seconds
     */
    abstract protected void showIntroductions(int seconds);

    
    public void onNextGame(final View v) {
        if (NextGameListener != null) {
            NextGameListener.onNextGame(this);
        }
    }

    public void setOnNextGameListener(final OnNextGameListener l) {
	NextGameListener = l;
    }

}
