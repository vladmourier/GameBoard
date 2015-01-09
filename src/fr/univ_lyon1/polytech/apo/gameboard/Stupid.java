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
public class Stupid extends Random_player {

    public Stupid(int i, GameBoard b)
    {
        super(i, b);
    }
    
    private boolean jouable()
    {
        int x=0,y=0;
        if(board.get_history().isEmpty())
        {
            return true;
        }
        Turn last_turn = (board.get_history(board.get_history().size() - 1));
        if(last_turn == null)                                                   //il n'y a pas de dernière cases jouée, donc pas de cases jouables pour Stupid
        {
            return true;
        }
        for (int i = 1; i < 10; i ++)                                           //On parcourt toutes les cases adjacentes
        {
            Position adjacente = board.next_position(last_turn.position,i);
            if(adjacente!=null)
            {
                x=adjacente.x;
                y=adjacente.y;
                if(x>=3 || y>=3 || x<0 || y<0)
                    return false;
            }
            Position temp_pos = new Position(x,y);     //On récupère les positions des cases adjacentes
                if(board.get_board(temp_pos.x, temp_pos.y) == 0)                //Si la case est vide
                {
                    return true;
                }
            
        }
        return false;
    }
    
    public Turn play()
    {
        if(this.jouable())
        {
            Random dir = new Random();                                                //Direction aléatoire
            
            if(board.get_history().isEmpty())//Si c'est le premier coup, il joue au pif:
            {return new Turn (new Position(dir.nextInt(board.width),dir.nextInt(board.length)),this);}
            
            Turn last_turn = board.get_history(board.get_history().size() - 1);         //Récupération du dernier tour joué, null si aucun tour joué
            Position p = board.next_position(last_turn.position, dir.nextInt(9)+1);       //On génère la position du prochain coup, null si identique ou hors du tableau
            int nb_essais=0;
            while (p == null)
            {
                p = board.next_position(last_turn.position, dir.nextInt(9)+1);            //On boucle jusqu'à avoir une position dans le tableau
                nb_essais++;
                if(nb_essais>14 && p!=null)
                {
                    p= new Position(dir.nextInt(board.width),dir.nextInt(board.length));
                }
                         if(p!=null)
                {
                if(p.x<0 || p.x>=3 || p.y<0 ||p.y>=3)
                { p=null;}
                else if(board.get_board(p.x, p.y)!=0)
                { p=null;}
                }
            }

            Turn t = new Turn (p, this);
            System.out.println("Stupid joue en :"+p.x+" , "+p.y);
            return t;
        }
        else
        {
          return  super.play();           //Si on ne peut pas jouer de cases à la manière de Stupid, on joue au hasard
        }
        //return null;
    }
}
