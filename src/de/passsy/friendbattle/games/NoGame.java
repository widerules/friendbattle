package de.passsy.friendbattle.games;

import android.content.Context;
import de.passsy.friendbattle.controllers.FirstGets;
import de.passsy.friendbattle.controllers.PointProvider;
import de.passsy.friendbattle.data.Player;

public class NoGame extends MiniGame {

    public NoGame(Context context) {
	super(context);
    }

    @Override
    protected void showIntroductions(int seconds) {
	
    }
    
    @Override
    public Correctness onGuess(Player player) {
	return Correctness.toolate;
    }

    @Override
    public void startGame() {
	//do nothing
    }

    @Override
    public CharSequence getDescription() {
	return "";
    }

}
