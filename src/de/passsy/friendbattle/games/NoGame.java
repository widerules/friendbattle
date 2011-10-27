package de.passsy.friendbattle.games;

import de.passsy.friendbattle.Player;

public class NoGame extends MiniGame {

    @Override
    protected void showIntroductions(int seconds) {
	
    }
    
    @Override
    public void onGuess(Player player) {
	player.getBuzzer().setTooLateBuzz(true);
    }

}
