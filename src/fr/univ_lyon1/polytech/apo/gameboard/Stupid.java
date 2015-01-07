/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_lyon1.polytech.apo.gameboard;
import java.util.Random;
/**
 *
 * @author Vladimir
 */
public class Stupid extends Player {
    private Turn last_turn;
    public Stupid(int i)
    {
        super(i);
        last_turn=null;
    }

    @Override
    public Turn play() {
        return stupid_play(null);
    }

    @Override
    public Turn random_play(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Turn stupid_play (Turn tour)
    {
        last_turn=tour;
        Position position=null, last_pos;
        last_pos=new Position(last_turn.position.x,last_turn.position.y);
        Random dir = new Random();
        int direction;//Direction représente les 8 possibilités
        //1->haut//2->bas//3->gauche//4->droite//5->haut_gauche//6->haut_droit//
        //7->bas_gauche//8->bas_droit
        direction=dir.nextInt(8);
        switch(direction)
        {
                case 0:
                    position = new Position(last_pos.x,last_pos.y+1);
                    break;
                case 1:
                    position = new Position(last_pos.x, last_pos.y-1);
                break;
                case 2:
                    position = new Position(last_pos.x-1, last_pos.y);
                    break;
                case 3:
                    position = new Position(last_pos.x+1, last_pos.y);
                    break;
                case 4:
                   position = new Position(last_pos.x-1, last_pos.y+1);
                    break;
                case 5:
                   position = new Position(last_pos.x+1, last_pos.y+1);
                    break;
                case 6: 
                   position = new Position(last_pos.x-1, last_pos.y-1);
                    break;
                case 7:
                   position = new Position(last_pos.x+1, last_pos.y-1);
                    break;
        }
         return new Turn (position, this);
    }
}
