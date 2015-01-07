package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.Scanner;

/**
 *
 * @author YOU
 */
public class Game {

    private static GameBoard gameboard;
    private static Human player_1;
    private static Human player_2;
    private static Random_player random_player_1;
    private static Random_player random_player_2;
    private static Chuck_Norris chuck;
    
    public static void main(String[] args) 
    {
    Scanner sc = new Scanner(System.in);
    System.out.println("Choisissez un jeu : 0 pour Puissance4, 1 pour le Morpion");
    String str = sc.nextLine();
    System.out.println("Vous avez saisi : " + str);
    if(str.equals(""+'0'))
    {
        System.out.println("Bienvenue dans le puissance 4");
            gameboard = new ConnectFour();
            player_1 = new Human (5);
            random_player_2 = new Random_player (3);

    }
    else
    {
    System.out.println("Bienvenue dans le Morpion");
    gameboard = new TicTacToe();
    player_1 = new Human(5);
    random_player_2 = new Random_player(3);

    }
        gameboard.display_gameboard();
        
        gameboard.play_loop(player_1, random_player_2);
        gameboard.display_history();
    }
    
}