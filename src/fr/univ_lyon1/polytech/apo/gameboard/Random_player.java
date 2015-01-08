/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.List;
import java.util.Random;

/**
 *
 * @author p1206512
 */
public class Random_player extends Player {
        
    public Random_player(int i)
    {
        super(i);
    }
    @Override
    public Turn play(){
        return random_play(false);
    }
    @Override
public Turn random_play(boolean b)//b indique le jeu : false => puissance4
                                  //                   true  => morpion
{
            Turn  tour;
        Random abs = new Random();
        Random ord = new Random();
        Position position;
        if(b==false)
        {
        position = new Position(abs.nextInt(7),ord.nextInt(6));
        } else{
            position = new Position(abs.nextInt(3), ord.nextInt(3));
        }
        tour = new Turn(position,this);
        return tour;
}

    public Turn stupid_play(Turn _history) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Turn smart_play(Turn tour, List<Position> liste, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Turn stupid_play(Turn _history, List<Position> liste) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
