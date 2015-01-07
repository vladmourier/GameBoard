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
        boolean a_joue=false;
        while(a_joue==false)
        {
            try{
               if(super.get_board(X,Y)!=0)
            {
                throw new PositionNotEmptyException();
            }
                super.set_board(X,Y, turn.player.number);
                super.to_history(turn);
                a_joue=true;
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Impossible de placer"+turn.player.number+"en"+"["+ X +"][+"+Y+"]");
                System.out.println("rejouez");
            } 
            catch (PositionNotEmptyException ex) {
                System.out.println("Cette case comporte déjà une piece");
                System.out.println("rejouez");
            }
        }
    }
    @Override
    public void play_loop(Player player1, Player player2) 
    {
       boolean victory = false, tour_ok;
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
             }
            
            display_gameboard(); // on affiche son coup
            System.out.println("\n"); // on espace l'affichage
            tour_ok = false;
            if(player2.getClass()==Human.class) //on applique les mêmes vérifications que pour le player1
            {
                player_type2=0;
            } else if (player2.getClass()==Random_player.class)
            {
                player_type2 = 1;
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
             }
            
            display_gameboard();
            /* test de victoire*/
            if(get_board(0,0)!=0)
            {
                victory = true;
            }
        }
        System.out.println("Le joueur "+get_board(0,0)+" a gagné");
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Turn lastTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player win() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return !(tour.position.x>=this.width || tour.position.y>=this.length || tour.position.x<0 || tour.position.y<0 );
    }
}