package de.passsy.friendbattle.games;

import android.content.Context;
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
	    player.setPoints(player.getPoints()+1);
	    if (NextGameListener != null) {
	        NextGameListener.onNextGame(MiniGame.this);
	    }
	} else {
	    player.setPoints(player.getPoints()-1);
	}
    };
    
    public void start(){
	showIntroductions(HOWTO_TIME);
    };
    
    abstract protected void showIntroductions(int seconds);

    abstract public void end();
    
    abstract public void reset();
    
    public void onNextGame(final View v) {
        if (NextGameListener != null) {
            NextGameListener.onNextGame(this);
        }
    }

    public void setOnNextGameListener(final OnNextGameListener l) {
	NextGameListener = l;
    }

}
