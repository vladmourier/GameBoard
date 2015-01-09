package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.List;

/**
 *
 * @author YOU
 */
public abstract class Player {
    public final int number;        //identifiant du joueur
    public GameBoard board;         //plateau de jeu
    

    public Player(int number, GameBoard board) {
        this.number = number;
        this.board = board;
    }
    
    public abstract Turn play();
}
