package de.passsy.friendbattle;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.text.Layout;
import android.widget.FrameLayout;

import de.passsy.friendbattle.games.ClickWhenColor;
import de.passsy.friendbattle.games.ClickWhenWhite;
import de.passsy.friendbattle.games.MiniGame;
import de.passsy.friendbattle.games.MiniGame.OnNextGameListener;
import de.passsy.friendbattle.utility.Tools;

public class GameCycle {
    
    private List<MiniGame> mMiniGames = new ArrayList<MiniGame>();
    
    private int mGameNumber = 0;
    
    private MiniGame mCurrentGame;
    
    private int mRounds = 5;
    
    private int mCurrentRounds = 0;
    
    public MiniGame getCurrentGame() {
        return mCurrentGame;
    }

    private FrameLayout mRootLayout;
    
    public GameCycle(FrameLayout rootLayout,int rounds){
	mRootLayout = rootLayout;
	mRounds = rounds;
	loadGames();
    }
    
    public void start() {
	MiniGame nextGame = getNextGame();
	if (nextGame == null){
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
    
    private void end() {
	mRootLayout.removeAllViews();
    }

    private void loadGames(){
	mMiniGames.add(new ClickWhenWhite());
	mMiniGames.add(new ClickWhenColor());
    }

}
