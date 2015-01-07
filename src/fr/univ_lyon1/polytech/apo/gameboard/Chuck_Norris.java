/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_lyon1.polytech.apo.gameboard;

/**
 *
 * @author Vladimir
 */
public class Chuck_Norris extends Player {
    
    public Chuck_Norris(int number) {
        super(number,0);
    }

    @Override
    public Turn play() {
        Turn tour=null;
        Position position;
        switch (nb_coups)
        {
            case 0:
                position = new Position (3,0);
                tour = new Turn(position, this);
                nb_coups++;
                break;
            case 1:
                position = new Position(1,0);
                tour = new Turn (position, this);
                nb_coups++;
                break;
            case 2:
                position = new Position(2,0);
                tour = new Turn(position, this);
                nb_coups++;
                break;
        }
        return new Turn (new Position(1,2),this);
        }

    @Override
    public Turn random_play(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
