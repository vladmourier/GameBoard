package fr.univ_lyon1.polytech.apo.gameboard;

import java.io.Serializable;

/**
 *
 * @author YOU
 */
public class Turn implements Serializable {
    
    public final Position position;
    public final Player player;

    public Turn(Position position, Player player) {
        this.position = position;
        this.player = player;
    }
    @Override
    public String toString() {
        return "Le joueur "+player.number+" se place en ["+position.x+", "+position.y+"];";
    }
    
    public void display_turn()
    {
        System.out.println(toString());
    }
   public void serialize ()
   {
       
   }
}
