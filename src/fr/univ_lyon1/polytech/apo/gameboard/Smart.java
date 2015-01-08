/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Vladimir
 */
public class Smart extends Stupid {
    
    private int nb_coups_joues;
    private List<Position> mes_coups;

    public Smart(int i)
    {
        super(i);
        mes_coups = new ArrayList<>();
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
    public Turn smart_play(Turn tour, List<Position> liste)
    {
        set_last_turn(tour);
        Random voisin = new Random();
        Position position=null, last_pos;
        last_pos=new Position(get_last_turn().position.x,get_last_turn().position.y);
        switch(nb_coups_joues)
        {
            case 0:
                position = new Position(1,1);
                break;
            case 2:
                if(last_pos.equals(new Position(1,2)))
                {
                    if(voisin.nextInt(2)==0)
                    {
                        position = new Position(0,2);
                    } else {
                        position = new Position(2,2);
                    }
                } else if(last_pos.equals(new Position(1,0)))
                {
                    if(voisin.nextInt(2)==0)
                    {
                        position = new Position(0,0);
                    } else {
                        position = new Position(2,0);
                    }
                } else if(last_pos.equals(new Position(0,1)))
                {
                    if(voisin.nextInt(2)==0)
                    {
                        position = new Position(0,2);
                    } else {
                        position = new Position(0,0);
                    }
                } else if(last_pos.equals(new Position(2,1)))
                {
                    if(voisin.nextInt(2)==0)
                    {
                        position = new Position(2,0);
                    } else {
                        position = new Position(2,2);
                    }
                }
                break;
            case 4:
                if(!liste.isEmpty()){
                position = liste.get(voisin.nextInt(liste.size()));}
                else{
                    if(last_pos.x==2 && last_pos.y==2)
                        if(voisin.nextInt(2)==0)
                        {
                            position = new Position(last_pos.x-1,last_pos.y);
                        }else position = new Position(last_pos.x,last_pos.y-1);
                    if(last_pos.x==2 && last_pos.y==0)
                        if(voisin.nextInt(2)==0)
                        {
                            position = new Position(last_pos.x,last_pos.y+1);
                        }else position = new Position(last_pos.x-1,last_pos.y);
                    if(last_pos.x==0 && last_pos.y==0)
                        if(voisin.nextInt(2)==0)
                        {
                            position = new Position(last_pos.x+1,last_pos.y);
                        }else position = new Position(last_pos.x,last_pos.y+1);
                    if(last_pos.x==0 && last_pos.y==2)
                        if(voisin.nextInt(2)==0)
                        {
                            position = new Position(last_pos.x+1,last_pos.y);
                        }else position = new Position(last_pos.x,last_pos.y-1);
                }
                break; 
            case 6:
                if(!liste.isEmpty()){
                position = liste.get(voisin.nextInt(liste.size()));}
                else{
                    stupid_play(tour);
                }
            case 8:
                if(!liste.isEmpty()){
                position = liste.get(voisin.nextInt(liste.size()));}
                else{
                    stupid_play(tour);
                }
            default:
                                if(!liste.isEmpty()){
                position = liste.get(voisin.nextInt(liste.size()));}
                else{
                    stupid_play(tour);
                }
        }
        mes_coups.add(position);
        tour = new Turn(position, this);
        nb_coups_joues+=2;
        return tour;
    }
    @Override
    public Turn stupid_play(Turn tour) {
    return super.stupid_play(tour);
    }
    

}
