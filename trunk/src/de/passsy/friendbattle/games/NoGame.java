package de.passsy.friendbattle.games;

import de.passsy.friendbattle.data.Player;

public class NoGame extends MiniGame {

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
