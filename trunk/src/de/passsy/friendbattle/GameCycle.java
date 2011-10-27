package de.passsy.friendbattle;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.FrameLayout;

import de.passsy.friendbattle.games.ClickWhenColor;
import de.passsy.friendbattle.games.ClickWhenWhite;
import de.passsy.friendbattle.games.MiniGame;
import de.passsy.friendbattle.games.MiniGame.OnNextGameListener;
import de.passsy.friendbattle.games.NoGame;

public class GameCycle {
    
    public interface OnEndListener {
	public abstract void onEnd(GameCycle cycle);
    }
    
    private List<MiniGame> mMiniGames = new ArrayList<MiniGame>();
    
    private int mGameNumber = 0;
    
    private MiniGame mCurrentGame;
    
    private int mRounds = 5;
    
    private int mCurrentRounds = 0;
    
    public MiniGame getCurrentGame() {
	if (mCurrentGame != null){
	    return mCurrentGame;
	} else {
	    return new NoGame();
	}
        
    }

    private FrameLayout mRootLayout;

    private OnEndListener mEndListener;
    
    public GameCycle(FrameLayout rootLayout,int rounds){
	mRootLayout = rootLayout;
	mRounds = rounds;
	loadGames();
    }
    
    public void start() {
	MiniGame nextGame = getNextGame();
	if (nextGame == null){
	    end();
	    showWinner();
	    return;
	}
	try {
	    mCurrentGame = nextGame.getClass().newInstance();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	mCurrentGame.setOnNextGameListener(new OnNextGameListener() {
	    
	    @Override
	    public void onNextGame(MiniGame game) {
		start();
	    }
	});
	mRootLayout.removeAllViews();
	mRootLayout.addView(mCurrentGame);
	mCurrentGame.start();
    }
    
    private void showWinner() {
	
    }

    private MiniGame getNextGame(){
	if (mMiniGames.size() <= mGameNumber){
	    end();
	    return null;
	} else {
    	    MiniGame nextGame = mMiniGames.get(mGameNumber);
    	    mCurrentRounds++;
    	    if (mCurrentRounds >= mRounds){
    	        mGameNumber++;
    	        mCurrentRounds = 0;
    	    }	
    	    return nextGame;
	}
    }
    
    public void onEnd(final View v) {
	if (mEndListener != null) {
	    mEndListener.onEnd(this);
	}
    }

    public void setonEndListener(final OnEndListener l) {
	mEndListener = l;
    }
    
    private void end() {
	mRootLayout.removeAllViews();
	mEndListener.onEnd(this);
	
    }

    private void loadGames(){
	mMiniGames.add(new ClickWhenWhite());
	mMiniGames.add(new ClickWhenColor());
    }
    

}
