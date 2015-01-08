/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Vladimir
 */
public class TicTacToe extends GameBoard {

    public TicTacToe()
    {
        super(3,3);
    }
    public TicTacToe(int width, int length) {
        super(width, length);
    }

    @Override
    public void play(Turn turn) {
        int X= turn.position.x;
        int Y=turn.position.y;
        super.set_board(X,Y, turn.player.number);
        super.to_history(turn);
    }
    @Override
    public void play_loop(Player player1, Player player2) 
    {
       boolean victory = false, tour_ok;
       Player winner=null;
        int player_type1 = 0, player_type2=0;
        while(victory != true) //Tant que personne n'a gagné
        {
            tour_ok=false;
            if(player1.getClass()==Human.class) //On regarde de quel type est le joueur1
            {
                player_type1=0;
            } else if (player1.getClass()==Random_player.class)
            {
                player_type1 = 1;
            }else if (player1.getClass()==Stupid.class)
            {
                player_type1 =2;
            }
            Turn tour=null;
            switch (player_type1) 
            { 
                case 0:// 0 => le joueur 1 est humain
                    while(tour_ok==false){
                        tour = player1.play();
                        tour_ok = check_tour(tour);
                            };
                    play(tour);// il joue donc comme un humain
                    break;
                case 1://1 => Le joueur 1 est Random
                    play(player1.random_play(true));// il fait n'importe quoi
                    break;
                case 2://2 -> Le joueur est stupide
                    play(player1.stupid_play(super.get_history(super.get_history().size()-1)));//Rien de très brillant
                    break;
                case 3:
                    List<Position> liste = new ArrayList<>();
                    liste=next_win();
                    while(tour_ok==false){
                        tour=player1.smart_play(super.get_history(super.get_history().size()-1),liste);
                        tour_ok=check_tour(tour);
                    }
                    play(tour);
                    }
            
            display_gameboard(); // on affiche son coup
            System.out.println("\n"); // on espace l'affichage
            tour_ok = false;
            
            /* test de victoire*/
            winner=win();
            if(winner!=null)
            {
                victory=true;
            }
            if(victory==false)
            {
            if(player2.getClass()==Human.class) //on applique les mêmes vérifications que pour le player1
            {
                player_type2=0;
            } else if (player2.getClass()==Random_player.class)
            {
                player_type2 = 1;
            }else if (player2.getClass()==Stupid.class)
            {
                player_type2 =2;
            }
            
            switch (player_type2) //idem
            { 
                case 0:
                    while(tour_ok==false){
                        tour = player2.play();
                        tour_ok = check_tour(tour);
                            };
                    play(tour);
                    break;
                case 1:
                    play(player2.random_play(true));
                    break;
                case 2:
                    while(tour_ok==false){
                        tour = player2.stupid_play(super.get_history(super.get_history().size()-1));
                        tour_ok = check_tour(tour);
                            };
                    play(tour);
             break;
                case 3:
                    List<Position> liste = new ArrayList<>();
                    liste=next_win();
                    while(tour_ok==false){
                        tour=player1.smart_play(super.get_history(super.get_history().size()-1),liste);
                        tour_ok=check_tour(tour);
                    }
                    play(tour);
                    break;
             }
            
            display_gameboard();
            }
            /* test de victoire*/
            winner=win();
            if(winner!=null)
            {
                victory=true;
            }
        }
        System.out.println("Le joueur "+winner.number+" a gagné");
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Turn lastTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<Position> next_win()
    {
        int i=0;
        List<Position> pos_tab = new ArrayList<>();
        // Pour chaque coin du gameboard on regarde d'où peuvent provenir les
        // coups gagnants, et si il y en a on les ajoute
        if((get_board()[0][0]==get_board()[1][0] && get_board()[0][0]!=0)||
                (get_board()[0][2]==get_board()[1][1] && get_board()[0][2]!=0)||
                (get_board()[2][2]==get_board()[2][1] && get_board()[2][2]!=0))
            pos_tab.add(new Position(2,0));
        
        if((get_board()[0][0]==get_board()[0][1] && get_board()[0][0]!=0)||
                (get_board()[2][0]==get_board()[1][1] && get_board()[2][0]!=0)||
                (get_board()[2][2]==get_board()[1][2] && get_board()[2][2]!=0))
            pos_tab.add(new Position(0,2));
            
        if((get_board()[0][0]==get_board()[1][1] && get_board()[0][0]!=0)||
                (get_board()[2][0]==get_board()[2][1] && get_board()[2][0]!=0)||
                (get_board()[0][2]==get_board()[1][2] && get_board()[0][2]!=0))
            pos_tab.add(new Position(2,2));
        
        if((get_board()[2][0]==get_board()[1][0] && get_board()[2][0]!=0)||
        (get_board()[0][2]==get_board()[0][1] && get_board()[0][2]!=0)||
        (get_board()[2][2]==get_board()[1][1] && get_board()[2][2]!=0))
            pos_tab.add(new Position(0,0));
        
        if(get_board()[0][1]==get_board()[1][1] && get_board()[0][1]!=0)
            pos_tab.add(new Position(2,1));
        if(get_board()[2][1]==get_board()[1][1] && get_board()[2][1]!=0)
            pos_tab.add(new Position(0,1));
        if(get_board()[1][0]==get_board()[1][1] && get_board()[1][0]!=0)
            pos_tab.add(new Position(1,2));
        if(get_board()[1][2]==get_board()[1][1] && get_board()[1][1]!=0)
            pos_tab.add(new Position(1,0));
        
    return pos_tab;
    }
    @Override
    public Player win() {
        
        if(((get_board()[0][0] == get_board()[0][1]) && (get_board()[0][0] == get_board()[0][2]) && (get_board()[0][0] != 0)) ||        //première colonne
                ((get_board()[1][0] == get_board()[1][1]) && (get_board()[1][0] == get_board()[1][2]) && (get_board()[1][0] != 0)) ||   //deuxième colonne
                ((get_board()[2][0] == get_board()[2][1]) && (get_board()[2][0] == get_board()[2][2]) && (get_board()[2][0] != 0)) ||   //troisième colonne
                ((get_board()[0][0] == get_board()[1][0]) && (get_board()[0][0] == get_board()[2][0]) && (get_board()[0][0] != 0)) ||   //première ligne
                ((get_board()[0][1] == get_board()[1][1]) && (get_board()[0][1] == get_board()[2][1]) && (get_board()[0][1] != 0)) ||   //deuxième ligne
                ((get_board()[0][2] == get_board()[2][1]) && (get_board()[0][2] == get_board()[0][2]) && (get_board()[0][2] != 0)) ||   //troisième ligne
                ((get_board()[0][0] == get_board()[1][1]) && (get_board()[0][0] == get_board()[2][2]) && (get_board()[0][0] != 0)) ||   //diagonale /
                ((get_board()[0][2] == get_board()[1][1]) && (get_board()[0][2] == get_board()[2][0]) && (get_board()[0][2] != 0)))     //diagonale \
        {
            return get_history(get_history().size() - 1).player;
        }
        else return null;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void display_gameboard() 
    {
       
        for(int j=super.length-1;j>=0;j--) 
        {
            for(int i = 0; i<width;i++)
            {
                System.out.print(super.get_board(i, j) + "|");
                if(i==2)
                {
                    System.out.print("\n");
                }
            }
        
        }   
    }
    public boolean check_tour (Turn tour){
        int X= tour.position.x;
        int Y=tour.position.y;
        int case_visee=0;
        try{
            if(X>=this.width || Y>=this.length //on vérifie qu'on est dans les
                    || X<0 || Y<0 )            //limites du tableau
                throw new ArrayIndexOutOfBoundsException();
        
            case_visee = super.get_board(X, Y);
            if(case_visee!=0)//Et que la case_visee est libre
                throw new PositionNotEmptyException();
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Impossible de placer "+tour.player.number+" en "+"["+ X +"]["+Y+"]");
            System.out.println("rejouez");
            return false;
        }catch(PositionNotEmptyException p){
            System.out.println("Impossible de placer "+tour.player.number+" en "+"["+ X +"]["+Y+"] car le joueur "+case_visee+" s'y trouve deja");
            System.out.println("rejouez");
            return false;
        }
        return !(tour.position.x>=this.width || tour.position.y>=this.length ||
                tour.position.x<0 || tour.position.y<0 );
    }
}
