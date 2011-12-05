package de.passsy.friendbattle.controllers;

import de.passsy.friendbattle.data.Player;
import de.passsy.friendbattle.games.MiniGame;
import de.passsy.friendbattle.games.MiniGame.Correctness;

public abstract class PointProvider {

    abstract public Correctness evalCorrectness(Boolean correctness, Player player, MiniGame miniGame);
    
}
