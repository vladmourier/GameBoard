/**
 * @author p1206512 & p1206617
 * COMTE Adrien & MOURIER Vladimir
 */

package fr.univ_lyon1.polytech.apo.gameboard;

import java.io.Serializable;

public class Turn implements Serializable {
    private static final long serialVersionUID = -2713278405794769091L;
    
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
