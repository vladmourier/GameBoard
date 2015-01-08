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
    
    private List<Position> mes_coups;

    public Smart(int i,int j)
    {
        super(i,j);
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

    public Turn smart_play(Turn tour, List<Position> liste)
    {
                Random voisin = new Random();
        Position position=null, last_pos;
        if(tour==null)
        {
         position = new Position(1,1);
         return new Turn(position, this);            
        }   
        set_last_turn(tour);

        last_pos=new Position(tour.position.x,tour.position.y);
        
        switch(nb_coups)
        {
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
                   tour =  stupid_play(tour, liste);
                }
            case 8:
                if(!liste.isEmpty()){
                position = liste.get(voisin.nextInt(liste.size()));}
                else{
                    tour = stupid_play(tour, liste);
                }
            default:
                                if(!liste.isEmpty()){
                position = liste.get(voisin.nextInt(liste.size()));}
                else{
                    tour = stupid_play(tour, liste);
                }
        }
        if(position!=null)
        {tour = new Turn(position, this);
            mes_coups.add(position);}
        nb_coups+=2;
        return tour;
    }
    
    @Override
    public Turn stupid_play(Turn tour, List<Position> liste) {
    return super.stupid_play(tour, liste);
    }
    

}
