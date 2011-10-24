package de.passsy.friendbattle;

import java.util.ArrayList;

import de.passsy.friendbattle.games.ClickWhenWhite;
import de.passsy.friendbattle.games.MiniGame;
import de.passsy.friendbattle.utility.Tools;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameMenu extends Activity {

    public final static String GAME_PREF = "GamePreferences";
    private int mPlayers = 6;
    private int mRounds = 3;
    private String mGameVersion = "0.01";

    private final static int EASY = 0;
    private final static int NORMAL = 1;
    private final static int HARD = 2;

    private ArrayList<String> mGames = new ArrayList<String>();
    

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	Tools.setCurrentActivity(this);
	setContentView(R.layout.gamemenu);
	Button start_btn = (Button) findViewById(R.id.startgame);
	start_btn.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		startGame();
	    }
	});
	
	loadPreferences();
	TextView players = (TextView) findViewById(R.id.players);
	players.setText(String.valueOf(getPlayers()));
	

    }

    private void loadPreferences() {
	SharedPreferences settings = getSharedPreferences(GAME_PREF, 0);
	//if the Game Version is different run a firstStart();
	if(settings.getString("version", null) == mGameVersion){
	    Log.v("tag", settings.getString("version", "null")+" =? " +mGameVersion);
	    Log.v("tag",mGameVersion.equals(settings.getString("version", "null"))+"");
	    getGames();
	    firstStart();
	}	
	
	mPlayers = settings.getInt("players", 2);
	// TODO 10 to maxgames
	for (int i = 0; i < 10; i++) {
	    String key = "game" + i;
	    if (settings.contains(key)) {
		String get = settings.getString(key, "noDefValue");
		if (get != "noDefValue") {
		    mGames.add(get);
		}
		
	    }
	}

    }

    private void getGames() {
	addGames(ClickWhenWhite.class);
	
    }

    private void savePreferences() {
	SharedPreferences settings = getSharedPreferences(GAME_PREF, 0);
	SharedPreferences.Editor editor = settings.edit();
	editor.putInt("players", mPlayers);
	editor.putString("version", mGameVersion);
	editor.putInt("rounds", mRounds);
	for (int i = 0; i < mGames.size() - 1; i++) {
	    editor.putString("game" + i, mGames.get(i));
	}
	editor.commit();
    }

    private void startGame() {
	savePreferences();
	Bundle arguments = new Bundle();
	arguments.putInt("players", mPlayers);
	arguments.putInt("difficulty", NORMAL);
	arguments.putInt("rounds", mRounds);
	arguments.putStringArrayList("games", mGames);
	Tools.StartActivity(FriendBattle.class, arguments);
    }

    private int getPlayers() {
	return mPlayers;
    }

    private void setPlayers(int players) {
	mPlayers = players;
    }

    private void addGames(Class<?> game) {
	mGames.add(game.getName());
    }

    /**
     * set initial Options Parameters
     */
    private void firstStart() {
	SharedPreferences settings = getSharedPreferences(GAME_PREF, 0);
	SharedPreferences.Editor editor = settings.edit();
	editor.putInt("players", 4);
	editor.putInt("rounds", 3);
	int i = 1;
	for(String gameClassName:mGames){
	    editor.putString("game"+i, gameClassName);
	    i++;
	}
	//editor.putString("game" + 1, ClickWhenWhite.class.getName());
	
	//Log.v("tag", ClickWhenWhite.class.getName());
	editor.commit();
	
    }
}