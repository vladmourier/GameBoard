/**
 * @author p1206512 & p1206617
 * COMTE Adrien & MOURIER Vladimir
 */

package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.List;

public abstract class Player {
    public final int number;        //identifiant du joueur
    public GameBoard board;         //plateau de jeu
    

    public Player(int number, GameBoard board) {
        this.number = number;
        this.board = board;
    }
    
    public abstract Turn play();
}
