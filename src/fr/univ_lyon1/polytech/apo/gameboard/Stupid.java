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
public class Stupid extends Player {
    private Turn last_turn;
    private int nb_coups_joues;

    public Stupid(int i, int j)
    {
        super(i);
        if(j!=0)
            nb_coups_joues=1;
        last_turn=null;
    }
    public Turn get_last_turn(){
        return last_turn;
    }
    public void set_last_turn(Turn tour){
        this.last_turn=tour;
    }
    @Override
    public Turn play() {
        return stupid_play(null,null);
    }

    @Override
    public Turn random_play(boolean b) {
        Random r = new Random();
        return new Turn(new Position(r.nextInt(3),r.nextInt(3)),this);
    }
    @Override
    public Turn stupid_play (Turn tour, List<Position> liste)
    {
                Position position=null, last_pos;
                Random dir = new Random();
        if(!liste.isEmpty())
        {
                position = liste.get(dir.nextInt(liste.size()));
        return new Turn (position, this);}
        if(tour==null || nb_coups_joues>3)//Si personne n'a joué ou qu'on touche à la fin on joue au pif
        {
            nb_coups_joues++;
            return new Turn(new Position(dir.nextInt(3),dir.nextInt(3)), this);
        }
        last_turn=tour;
        last_pos=new Position(last_turn.position.x,last_turn.position.y);


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
        if(position!=null)
        {System.out.println("le coup précédent était en : ["+last_pos.x+"] ["+last_pos.y+" \n x="+position.x+" dir="+direction+" et y="+position.y);
        }this.nb_coups_joues++; 
        return new Turn (position, this);
    }

    public Turn smart_play(Turn tour, List<Position> liste, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
