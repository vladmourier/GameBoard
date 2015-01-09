/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.polytech.apo.gameboard;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author p1206512
 */
public class ConnectFour extends GameBoard {
   
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
        super.set_board(X,altimetre, turn.player.number);
        super.to_history(turn);       
        
    }
    @Override
    public void play_loop(Player player1, Player player2) {
        boolean victory = false, tour_ok;
        int premier_tour=0;
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
            String str = new String();
            Scanner sc = new Scanner(System.in);
       
            switch (player_type1) 
            { 
                case 0:// 0 => le joueur 1 est humain
                    if(premier_tour!=0)
                    {
                        System.out.println("Voulez vous annuler le tour précédent? y/n \n Sauvegarder? s");
                        str = sc.nextLine();
                        if(str.charAt(0)=='y')
                        {
                            cancel();
                            cancel();
                        }else if(str.charAt(0)=='s'){
                           try{ Game.save_game();                        
                                }catch(IOException e)
                                    {
                                        System.out.println("Impossible de sauvegarder");
                                    }
                        }
                    }
                    
                    premier_tour++;
                    while(tour_ok==false)
                    {
                        tour = player1.play();
                        tour_ok = check_tour(tour);
                       System.out.println("Valider le coup? y/n");
                       display_gameboard(this.toString(), tour);
                       str=sc.nextLine();
                       if(str.charAt(0)!='y')
                       {
                           tour_ok=true;
                       }
                     }
                    play(tour);// il joue donc comme un humain
                    break;
                case 1://1 => Le joueur 1 est Random
                    play(player1.play());// il fait n'importe quoi
                    break;
                case 2://2 => Le joueur 1 est Chuck Norris
                    play(player1.play());
                    if(((Chuck_Norris)player1).get_nb_coups()==3)
                    {
                        victory=true;
                        System.out.print("Bienheureux ceux qui espèrent gagner face à Chuck...");

                    }
                    break;
             }
            System.out.println("\n"); // on espace l'affichage
            display_gameboard(this.toString()); // on affiche son coup
            System.out.println("\n"); // on espace l'affichage
            
            
            
                        /* test de victoire*/
            if(win() != null)
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
                case 0:// 0 => le joueur 2 est humain
                    while(tour_ok==false){
                        tour = player2.play();
                        tour_ok = check_tour(tour);
                            };
                    play(tour);// il joue donc comme un humain
                    break;
                case 1://si il est random
                    play(player2.play());//il joue random
                    break;
                    case 2://2 => Le joueur 2 est Chuck Norris
                    play(player2.play());
                    if(((Chuck_Norris)player1).get_nb_coups()==3)
                    {
                        victory=true;
                        System.out.print("Bienheureux ceux qui espèrent gagner face à Chuck...");
                    }
                    break;
             }
            
            display_gameboard(this.toString());
            /* test de victoire*/
            if(win() != null)
            {
                victory = true;
            }
        }
        }
        System.out.println("Le joueur "+get_board(super.get_history().get(super.get_history().size()-1).position.x,super.get_history().get(super.get_history().size()-1).position.x)+" a gagné");
    }
    
    public boolean check_tour (Turn tour)
    {
        int X= tour.position.x;
        int Y=tour.position.y;
        try{
        if((X >= this.width || X<0) || super.get_board(X,super.length-1)!=0)
            throw new ArrayIndexOutOfBoundsException();
            }catch(ArrayIndexOutOfBoundsException e)
        {
        System.out.println("Impossible de placer "+tour.player.number+" en "+"["+ X +"]["+Y+"]");
        System.out.println("rejouez");
        return false;
        }
        return !(tour.position.x >= this.width 
                || tour.position.x<0);
    }
    @Override
        public void cancel() {//On met la case visée par le dernier coup à 0
            super.cancel();
        }
    
        @Override
    public Turn lastTurn() 
    {
        return super.lastTurn();
    }
    @Override
    public Player win()
    {
       Turn last_turn = lastTurn();
       Position last_pos = last_turn.position;
       Player last_player = last_turn.player;
       
       for(int i = 1; i < 5; i++)       //Parcourt de la moitié des positions, l'autre moitié étant les opposées elles sont parcourues automatiquement
       {
           Position temp_pos = last_pos;        //initialisation de la position temporaire
           int consecutive = 0;                 //compteur
           
           while(next_position(temp_pos, i) != null)        //on parcourt dans la direction choisie tant qu'on reste sur le plateau
           {
               if(get_board(temp_pos.x, temp_pos.y) == last_player.number)//compare la valeur de la case suivante dans la direction choisie avec le dernier joueur
               {
                   consecutive++;
               }
               else
               {
                   break;       //Détection d'un joueur différent dans la direction choisie, on ne progresse plus dans cette direction
               }
               temp_pos = next_position(temp_pos, i);
           }
           
           temp_pos = last_pos;     //On repart de la case d'origine pour un parcourt dans le sens opposé
           
            while(next_position(temp_pos, 10 - i) != null)      //On parcourt l'opposée de la direction précédente tant qu'on reste sur le plateau
           {
               if(get_board(temp_pos.x, temp_pos.y) == last_player.number)
               {
                   consecutive++;
               }
               else
               {
                   break;
               }
               temp_pos = next_position(temp_pos, 10-i);
           }
            if(consecutive >= 4)        //4 pions au moins sont alignés, le dernier joueur est vainqueur
            {
                return last_player;
            }
       }
       return null;                                
    }
    @Override
    public void display_gameboard(String s) {
       
        super.display_gameboard(s);
       }
    @Override
    public String toString() 
    {
        StringBuilder sb=new StringBuilder();
        for(int j=length-1;j>=0;j--) 
        {
            for(int i = 0; i<width;i++)
            {
                String players="  ";
                switch(super.get_board(i, j))
                {
                    case 1:
                        players = "●";
                        break;
                    case 2:
                        players = "○";
                        break;
                    case 9:
                        players = "◌";
                        break;
                }
                sb.append(players).append("|");
                
                if(i==length)
                {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
    
    void display_gameboard(String s, Turn t)
    {
        play(t);
        super.display_gameboard(s);
        cancel();
    }
}
