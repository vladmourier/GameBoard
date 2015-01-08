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
public class Smart extends Stupid {
    
    private int nb_coups_joues;

    public Smart(int i)
    {
        super(i);
    }
    
    
    @Override
    public Turn play() {
        return null;
    }

    @Override
    public Turn random_play(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Turn stupid_play(Turn tour) {
        set_last_turn(tour);
        Random voisin = new Random();
        Position position=null, last_pos;
        last_pos=new Position(get_last_turn().position.x,get_last_turn().position.y);
        switch(nb_coups_joues)
        {
            case 0:
                position = new Position(1,1);
                tour = new Turn(position, this);
                break;
            case 2:
                if(last_pos.equals(new Position(1,2)))
                {
                    if(voisin.nextInt(2)==0)
                    {
                        position = new Position(0,2);
                        tour = new Turn(position, this);
                    } else {
                        position = new Position(2,2);
                        tour = new Turn(position, this);
                    }
                } else if(last_pos.equals(new Position(1,0)))
                {
                    if(voisin.nextInt(2)==0)
                    {
                        position = new Position(0,0);
                        tour = new Turn(position, this);
                    } else {
                        position = new Position(2,0);
                        tour = new Turn(position, this);
                    }
                } else if(last_pos.equals(new Position(0,1)))
                {
                    if(voisin.nextInt(2)==0)
                    {
                        position = new Position(0,2);
                        tour = new Turn(position, this);
                    } else {
                        position = new Position(0,0);
                        tour = new Turn(position, this);
                    }
                } else if(last_pos.equals(new Position(2,1)))
                {
                    if(voisin.nextInt(2)==0)
                    {
                        position = new Position(2,0);
                        tour = new Turn(position, this);
                    } else {
                        position = new Position(2,2);
                        tour = new Turn(position, this);
                    }
                }
                break;
            case 4:
                
                
        }
        nb_coups_joues+=2;
        return tour;
    }
    
}
