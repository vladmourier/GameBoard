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
 * @author Vladimir
 */
public class Stupid extends Random_player {

    public Stupid(int i, GameBoard b)
    {
        super(i, b);
    }
    
    private boolean jouable()
    {
        Turn last_turn = board.get_history(board.get_history().size() - 1);
        if(last_turn == null)                                                   //il n'y a pas de dernière cases jouée, donc pas de cases jouables pour Stupid
        {
            return false;
        }
        for (int i = 1; i < 10; i ++)                                           //On parcourt toutes les cases adjacentes
        {
            Position temp_pos = board.next_position(last_turn.position, i);     //On récupère les positions des cases adjacentes
            if(temp_pos != null)                                                //Si la case récupérée dans la direction existe
            {
                if(board.get_board(temp_pos.x, temp_pos.y) == 0)                //Si la case est vide
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Turn play()
    {
        if(this.jouable())
        {
            Random dir = new Random();                                                  //Direction aléatoire
            Turn last_turn = board.get_history(board.get_history().size() - 1);         //Récupération du dernier tour joué, null si aucun tour joué
            Position p = board.next_position(last_turn.position, dir.nextInt(9));       //On génère la position du prochain coup, null si identique ou hors du tableau

            while (p == null)
            {
                p = board.next_position(last_turn.position, dir.nextInt(9));            //On boucle jusqu'à avoir une position dans le tableau
            }

            Turn t = new Turn (p, this);
            return t;
        }
        else
        {
            super.play();           //Si on ne peut pas jouer de cases à la manière de Stupid, on joue au hasard
        }
        return null;
    }
}
