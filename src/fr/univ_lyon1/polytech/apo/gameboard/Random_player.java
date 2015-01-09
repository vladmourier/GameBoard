/**
 * @author p1206512 & p1206617
 * COMTE Adrien & MOURIER Vladimir
 */

package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.List;
import java.util.Random;

public class Random_player extends Player {
        
    public Random_player(int i, GameBoard b)
    {
        super(i, b);
    }
    
    @Override
    public Turn play()
    {
        Random abs = new Random();
        Random ord = new Random();
        Position p =  new Position(abs.nextInt(board.width), ord.nextInt(board.length));        //Nouvelle position obligatoirement dans la gameboard
        while(board.get_board(p.x, p.y) != 0)
        {
            p =  new Position(abs.nextInt(board.width), ord.nextInt(board.length));             //Tant qu'on ne joue pas une case vide on rejoue
        }
        Turn t = new Turn(p, this);                                                             //Le tour créé est valide
        return t;
    }
}
