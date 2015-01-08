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
    

    public Smart(int i,int j)
    {
        super(i,j);
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
        if(tour==null)//Si c'est le premier coup, on joue au milieu
        {
         position = new Position(1,1);
         return new Turn(position, this);            
        }   
        set_last_turn(tour);

        last_pos=new Position(tour.position.x,tour.position.y);
        
        switch(nb_coups)
        {
            case 2://Si c'est la seconde fois qu'on joue et que l'adversaire
                    // s'est placé sur la croix non diagonale alors on joue
                    // dans un des coins adjacents à son pion
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
            case 4://Si c'est la 3e fois qu'on joue
                if(!liste.isEmpty()){//Si on peut gagner, on gagne
                position = liste.get(voisin.nextInt(liste.size()));}
                else{
                    if(last_pos.x==2 && last_pos.y==2)
                        if(voisin.nextInt(2)==0)//Sinon on joue dans certains
                        {                       // endroits favorables selon le coup de l'autre
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
            default:
                if(!liste.isEmpty()){
                position = liste.get(voisin.nextInt(liste.size()));}
                else{
                    tour = stupid_play(tour, liste);
                }
           }
        if(position!=null)
        {tour = new Turn(position, this);}
        return tour;
    }
    
    @Override
    public Turn stupid_play(Turn tour, List<Position> liste) {
    return super.stupid_play(tour, liste);
    }
    

}
