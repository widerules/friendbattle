package de.passsy.friendbattle.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import de.passsy.friendbattle.FriendBattle;
import de.passsy.friendbattle.R;
import de.passsy.friendbattle.controllers.GameCycle;
import de.passsy.friendbattle.controllers.GameCycle.OnEndListener;
import de.passsy.friendbattle.controllers.GameCycle.OnNewGameListener;
import de.passsy.friendbattle.controls.Buzzer;
import de.passsy.friendbattle.controls.Buzzer.OnBuzzListener;
import de.passsy.friendbattle.controls.TextViewFlipped;
import de.passsy.friendbattle.data.Player;
import de.passsy.friendbattle.games.MiniGame;
import de.passsy.friendbattle.games.MiniGame.Correctness;
import de.passsy.friendbattle.screenlayouts.Screen_Winner;
import de.passsy.friendbattle.screenlayouts.Screen_Winner.OnRestartListener;

public class FriendBattleGame extends Activity {

    private static final int MAX_PLAYERS = 6;

    private int mDifficulty;
    private List<Player> mPlayers = new ArrayList<Player>();
    private int mPlayerNumber;
    private List<Buzzer> mBuzzer = new ArrayList<Buzzer>();
    private FrameLayout mGameModule;
    private MiniGame mCurrentGame;

    private Screen_Winner mWinnerScreen = new Screen_Winner();

    private GameCycle mGameCycle;

    private int mRounds;

    private TextViewFlipped mBot_txt;
    private TextViewFlipped mTop_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// hide titlebar
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	// run in fullscreenmode
	getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	getWindow().clearFlags(
		WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	FriendBattle.setCurrentActivity(this);
	setContentView(R.layout.friendbattle);
	findViews();
	readIntent();
	createListeners();
	linkPlayerAndButtons();
	loadGames();
	mGameCycle.start();
    }
    
    public void restart(){
	loadGames();
	for(Player player:mPlayers){
	    player.setPoints(0);
	}
	mGameCycle.start();
    }

    private void createListeners() {
	for (Buzzer buzzer : mBuzzer) {
	    buzzer.setOnBuzzListener(new OnBuzzListener() {

		@Override
		public void onBuzz(Buzzer btn) {
		    MiniGame.Correctness correctness = mGameCycle
			    .getCurrentGame().onGuess(btn.getPlayer());
		    dealPoints(correctness, btn.getPlayer());
		}
	    });
	}

    }

    protected void dealPoints(Correctness correctness, Player player) {
	switch (correctness) {
	case correct:

	    player.getBuzzer().setCorrectBuzz(true);
	    player.setPoints(player.getPoints() + 1);
	    break;

	case incorrect:
	    player.getBuzzer().setCorrectBuzz(false);
	    player.setPoints(player.getPoints() - 1);
	    break;

	case toolate:

	    player.getBuzzer().setTooLateBuzz(true);
	    break;
	default:
	    player.getBuzzer().setTooLateBuzz(true);
	    break;
	}

    }

    private void findViews() {
	// find Buzzers
	for (int i = 0; i < MAX_PLAYERS; i++) {
	    String buzzerID = "buzzer" + i;
	    Buzzer buzzer = (Buzzer) findViewById(getResources().getIdentifier(
		    buzzerID, "id", "de.passsy.friendbattle"));
	    mBuzzer.add(buzzer);
	}
	mGameModule = (FrameLayout) findViewById(R.id.gamemodule);

	mTop_txt = (TextViewFlipped) findViewById(R.id.top_txt);
	mBot_txt = (TextViewFlipped) findViewById(R.id.bot_txt);

	mWinnerScreen.setOnRestartListener(new OnRestartListener() {
	    
	    @Override
	    public void onRestart() {
		restart();
		
	    }
	});
    }

    private void readIntent() {
	Bundle extras = getIntent().getExtras();
	mDifficulty = extras.getInt("difficulty");
	mPlayerNumber = extras.getInt("players");
	mRounds = extras.getInt("rounds");
	int colors[] = extras.getIntArray("buzzercolors");
	for (int i = 0; i < colors.length; i++) {
	    mBuzzer.get(i).setColor(colors[i]);
	}
	Log.v("tag", mRounds + "");
    }

    private void linkPlayerAndButtons() {

	for (int i = 1; i <= mPlayerNumber; i++) {
	    Player player = new Player(i);
	    player.setBuzzer(mBuzzer.get(i - 1));
	    mPlayers.add(player);
	    player.getBuzzer().setPlayer(player);
	    player.getBuzzer().setText("0");
	}

	while (mBuzzer.size() > mPlayerNumber) {
	    mBuzzer.get(mPlayerNumber).setVisibility(View.GONE);
	    mBuzzer.remove(mPlayerNumber);
	}

    }

    private void loadGames() {
	mGameCycle = new GameCycle(getApplicationContext(),mGameModule, mRounds);
	mGameCycle.setonEndListener(new OnEndListener() {

	    @Override
	    public void onEnd(GameCycle cycle) {
		showResults();
	    }
	});
	mGameCycle.setOnNewGameListener(new OnNewGameListener() {
	    
	    @Override
	    public void onNewGame(CharSequence name, CharSequence description) {
		setDescription(description);
		//Tools.toast(name);
	    }
	});
    }

    protected void showResults() {
	mGameModule.addView(mWinnerScreen);
	//mWinnerScreen.setWinner(getWinner());
	setText("Player" + getWinner() + " won the Game");
    }
    
    private void setText(CharSequence text){
	mTop_txt.setText(text);
	mBot_txt.setText(text);
    }

    private int getWinner() {
	Player winner = new Player(-1);
	for (Player player : mPlayers) {

	    if (player.getPoints() > winner.getPoints()) {
		winner = player;
	    }
	}
	return winner.getId();
    }
    
    /**
     * sets the description of the current game
     * @param text
     */
    public void setDescription(CharSequence text){
	mBot_txt.setText(text);
	mTop_txt.setText(text);
    }
}
