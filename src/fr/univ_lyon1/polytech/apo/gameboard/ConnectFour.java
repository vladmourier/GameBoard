/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.List;

/**
 *
 * @author p1206512
 */
public class ConnectFour extends GameBoard {

    private enum Cell {
        BASDROITE("5,0"),
        BASGAUCHE("5,0"),
        BASMILIEU("5,0"),
        MILIEUDROITE("5,0"),
        MILIEUGAUCHE("5,0"),
        MILIEUMILIEU("5,0"),
        HAUTDROITE("5,0"),
        HAUTGAUCHE("5,0"),
        HAUTMILIEU("5,0");
        
        public String content;
        public static void spoon() { System.out.println("spoon"); }
        private Cell(String content) { this.content = content ; }
    }
    
    public ConnectFour(){
        super(7, 6);
    }
    
    public ConnectFour(List<Turn> history){
        super(7, 6, history);
    }
    

    @Override
    public void play(Turn turn)//implémenter : si un pion est déjà sur la case, impossible de jouer 
    {
        int X = turn.position.x;
        
        int altimetre;//altimetre représente la gravité :seule la position de X importe
        altimetre=super.width -1;//On initialise l'altimètre tout en haut du gmeboard
        while(altimetre != 0 && super.get_board(X, altimetre-1)==0)//On descend jusqu'à ce qu'on atteigne 0 ou qu'il y ait un pion en dessous
        {
            altimetre--;
            }
        
        try{
            super.set_board(X,altimetre, turn.player.number);
            super.to_history(turn);
        }catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Impossible de placer"+turn.player.number+"en"+"["+ X +"][+"+altimetre+"]");
        }
        
        
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
    public void play_loop(Player player1, Player player2) {
        boolean victory = false;
        int player_type1 = 0, player_type2=0;
        while(victory != true) //Tant que personne n'a gagné
        {
            if(player1.getClass()==Human.class) //On regarde de quel type est le joueur1
            {
                player_type1=0;
            } else if (player1.getClass()==Random_player.class)
            {
                player_type1 = 1;
            }
            
            switch (player_type1) 
            { 
                case 0:// 0 => le joueur 1 est humain
                    play(player1.play());// il joue donc comme un humain
                    break;
                case 1://1 => Le joueur 1 est Random
                    play(player1.random_play(false));// il fait n'importe quoi
                    break;
             }
            
            display_gameboard(); // on affiche son coup
            System.out.println("\n"); // on espace l'affichage
            
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
                    play(player2.play());
                    break;
                case 1:
                    play(player2.random_play(false));
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
    public void display_gameboard() {
       
             for(int j=length-1;j>=0;j--) 
        {
      for(int i = 0; i<width;i++)
            {
                System.out.print(super.get_board(i, j) + "|");
                if(i==length)
                {
                    System.out.print("\n");
                }
            }
        
    }   }
    
}
