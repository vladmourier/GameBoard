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
        
        
        int[][] combinaisons=new int[2][8];//2 pour les deux joueurs, 4pour les 2axes + 2*2 diagonales.
        int curseur=0, player1_ind=0, player2_ind=0;
        boolean player_ok=false;
        
        while(player_ok==false){
                    
             for(int j=length-1;j>=0;j--) 
        {
            for(int i = 0; i<width;i++)
            {
             int k=j,l=i;
             int courant = get_board(i,j);
             if(courant != 0)
             {
                 player1_ind=courant;
             }
             if(courant != player1_ind)
             {
                 player2_ind= courant;
             }
             
             while(k>0)
             {
                 while(l<width)
                 {
                 if(courant!=0)
                 {
                     for(int direction=0;direction<8;direction++)
                     {
                         if(next_position(k,l,direction))
                         combinaisons[0][direction]+=1;
                     }
                 }
                 k--;
                }
                 l++;
             }
            }
        }
        }
        
             
             if((combinaisons[0][2]+combinaisons[0][3]+1>=4) ||(combinaisons[0][0]+combinaisons[0][1]+1>=4 )||
                     (combinaisons[0][4]+combinaisons[0][5]+1>=4 )|| (combinaisons[0][6]+combinaisons[0][7]+1>=4))
             {
                 return null;
             }
                Player vainqueur= new Human (0);
        return vainqueur;
    }
      public Player get_player (int i, int j)
    {
        int nb_coups_joues=super.get_history().size();
        for(int cpt = 1; cpt<=nb_coups_joues;cpt++)
        {
        if(super.get_history().get(nb_coups_joues-cpt).position.equals(new Position(i,j)));
        {
            return super.get_history().get(nb_coups_joues-cpt).player;
        }       

    }
        /*        int id = gameboard.get_board(i,j);
        if (player_1!=null && player_1.number==id)
        {
            return player_1;
        }
        if (player_1!=null || player_1.number==id)
        {
            return player_1;
        }
        
        if(random_player_1!=null && random_player_1.number == id){
            return random_player_1;
        }
        if(random_player_2!=null && random_player_2.number == id){
            return random_player_2;
        } else {
        return chuck;
        }*/
        return null;
    } 
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void play_loop(Player player1, Player player2) {
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
            }else if (player1.getClass()==Chuck_Norris.class)
            {
                player_type1=2;
            }
            
            Turn tour = null;
            switch (player_type1) 
            { 
                case 0:// 0 => le joueur 1 est humain
                    while(tour_ok==false)
                    {
                        tour = player1.play();
                        tour_ok = check_tour(tour);
                     }
                    play(tour);// il joue donc comme un humain
                    break;
                case 1://1 => Le joueur 1 est Random
                    play(player1.random_play(false));// il fait n'importe quoi
                    break;
                case 2://2 => Le joueur 1 est Chuck Norris
                    play(player1.play());
                    if(player1.nb_coups==3)
                    {
                        victory=true;
                    }
                    break;
             }
            System.out.println("\n"); // on espace l'affichage
            display_gameboard(); // on affiche son coup
            System.out.println("\n"); // on espace l'affichage
            
            
            
                        /* test de victoire*/
            if(get_board(0,0)!=0)
            {
                victory = true;
            }
            if(victory==false)
            {
            tour_ok = false;
            if(player2.getClass()==Human.class) //on applique les mêmes vérifications que pour le player1
            {
                player_type2=0;
            } else if (player2.getClass()==Random_player.class)
            {
                player_type2 = 1;
            } else if (player2.getClass()==Chuck_Norris.class)
            {
                player_type2=2;
            }
            
            switch (player_type2) //idem
            { 
                case 0:// 0 => le joueur 1 est humain
                    while(tour_ok==false){
                        tour = player2.play();
                        tour_ok = check_tour(tour);
                            };
                    play(tour);// il joue donc comme un humain
                    break;
                case 1:
                    play(player2.random_play(false));
                    break;
                    case 2://2 => Le joueur 2 est Chuck Norris
                    play(player2.play());
                    if(player2.nb_coups==3)
                    {
                        victory=true;
                    }
                    break;
             }
            
            display_gameboard();
            /* test de victoire*/
            if(get_board(0,0)!=0)
            {
                victory = true;
            }
        }
        }
        System.out.println("Le joueur "+get_board(super.get_history().get(super.get_history().size()-1).position.x,super.get_history().get(super.get_history().size()-1).position.x)+" a gagné");
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
    
    public boolean check_tour (Turn tour)
    {
        return !(tour.position.x >= this.width 
                || tour.position.x<0);
    }
}
