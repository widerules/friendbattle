package de.passsy.friendbattle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.passsy.friendbattle.Buzzer.OnBuzzListener;
import de.passsy.friendbattle.games.ClickWhenWhite;
import de.passsy.friendbattle.games.MiniGame;
import de.passsy.friendbattle.games.MiniGame.OnNextGameListener;
import de.passsy.friendbattle.utility.Tools;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FriendBattle extends Activity {
    
    private static final int MAX_PLAYERS = 6;
    
    private int mDifficulty;
    private List<Player> mPlayers = new ArrayList<Player>();
    private int mPlayerNumber;
    private List<Buzzer> mBuzzer = new ArrayList<Buzzer>();
    private FrameLayout mGameModule;
    private MiniGame mCurrentGame;

    private GameCycle mGameCycle;

    private int mRounds;
    
    

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //run in fullscreenmode
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Tools.setCurrentActivity(this);
        setContentView(R.layout.friendbattle);
        findViews();
        readIntent();
        createListeners();
        linkPlayerAndButtons();
        loadGames();
        mGameCycle.start();
    }
    
    private void createListeners() {
	for (Buzzer buzzer:mBuzzer){
	    buzzer.setonBuzzListener(new OnBuzzListener() {
	        
	        @Override
	        public void onBuzz(Buzzer btn) {
	            mGameCycle.getCurrentGame().onGuess(btn.getPlayer());
	            
	        }
	    });
	}
	
    }

    private void findViews() {
	//find Buzzers
	for (int i = 0; i < MAX_PLAYERS; i++) {
	    String buzzerID = "buzzer" + i;
            Buzzer buzzer = (Buzzer) findViewById(getResources().getIdentifier(buzzerID, "id", "de.passsy.friendbattle"));
            mBuzzer.add(buzzer);
	}
	mGameModule = (FrameLayout) findViewById(R.id.gamemodule);
    }

    private void readIntent(){
        Bundle extras = getIntent().getExtras();
        mDifficulty = extras.getInt("difficulty");
        mPlayerNumber = extras.getInt("players");
        mRounds = extras.getInt("rounds");
        Log.v("tag",mRounds+"");
    }
    
    private void linkPlayerAndButtons(){

	for (int i = 1; i <= mPlayerNumber; i++) {
	    Player player = new Player(i);
	    player.setBuzzer(mBuzzer.get(i-1));
	    mPlayers.add(player);
	    player.getBuzzer().setPlayer(player);
	}
	
	while(mBuzzer.size() > mPlayerNumber){
	    mBuzzer.get(mPlayerNumber).setVisibility(View.GONE);
	    mBuzzer.remove(mPlayerNumber);
	}
    }
    
    @Deprecated
    /**
     * 
     * @param mCurrentGame
     */
    private void setCurrentGame(MiniGame mCurrentGame) {
        this.mCurrentGame = mCurrentGame;
        mGameModule.removeAllViews();
        mGameModule.addView(mCurrentGame);
        mCurrentGame.setOnNextGameListener(new OnNextGameListener() {
	    
	    @Override
	    public void onNextGame(MiniGame game) {
		startNextGame();
		
	    }
	});
    }

    
    private void loadGames(){
	mGameCycle = new GameCycle(mGameModule,mRounds);
    }
    
    private void startNextGame(){
	Tools.toast("no more Games");
    }
}
