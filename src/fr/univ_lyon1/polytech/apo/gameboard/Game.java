package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.Scanner;

/**
 *
 * @author YOU
 */
public class Game {

    private static GameBoard gameboard;
    private static Player player_1;
    private static Player player_2;

    public static void main(String[] args) 
    {
        boolean quit = false;
        int game, player_type_1, player_type_2;
                while(quit==false)
        {
        game= main_menu();//On choisit son jeu
        switch (game)
        {
            case 9:
                System.out.println("Au revoir");
                System.exit(0);
                break;
            case 0:
                System.out.println("Bienvenue dans le puissance 4");
                gameboard = new ConnectFour();

                player_type_1 = choose_player_menu(0,0);//On choisit son joueur 1

                            switch (player_type_1)
                            {
                                case 0:
                                    player_1 = new Human(1);
                                    break;
                                case 1:
                                    /*random_*/player_1 = new Random_player(1);
                                    break;
                                case 9:
                                    /*chuck_*/player_1 = new Chuck_Norris(1);
                                    break;
                            }
                            player_type_2 = choose_player_menu(1,0);//On choisit le challenger du p4
                            switch (player_type_2)
                            {
                                case 0:
                                    player_2 = new Human(2);
                                    break;
                                case 1:
                                    /*random_*/player_2 = new Random_player(2);
                                    break;
                                case 9:
                                    /*chuck_*/player_2 = new Chuck_Norris(9);
                                    break;
                            }
            break;
            case 1:
            System.out.println("Bienvenue dans le Morpion");
            gameboard = new TicTacToe();
            player_type_1 = choose_player_menu(0,1);//On choisit son joueur 1
            
                            switch (player_type_1)
                            {
                                case 0:
                                    player_1 = new Human(1);
                                    break;
                                case 1:
                                    /*stupid_*/player_1 = new Stupid(1,0);
                                    break;
                                case 2:
                                    /*smart_*/player_1 = new Smart(1,0);
                                    break;
                                case 3 :
                                    /*random_*/player_1 = new Random_player(1);
                                    break;
                            }
            player_type_2 = choose_player_menu(1,1);//On choisit le challenger du TTT
                            switch (player_type_2)
                            {
                                case 0:
                                    player_2 = new Human(2);
                                    break;
                                case 1:
                                   /*    stupid_*/player_2 = new Stupid(2,1);
                                    break;
                                case 2:
                                   /* smart_*/player_2 = new Smart(2,1);
                                    break;
                                case 3 :
                                    /*random_*/player_2 = new Random_player(2);
                                    break;
                            }
            break;

            default :
                quit = true;
    }
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.println();            
        gameboard.display_gameboard(gameboard.toString());
        System.out.println();
        

        gameboard.play_loop(player_1, player_2);
        gameboard.display_history();
        System.out.println("\n Quitter? y/n");
        str = sc.nextLine();
        if(str.charAt(0)=='y')
        {
            quit = true;
            System.out.println("Au revoir");
            System.exit(0);
        }
        }
    }
        private static int choose_player_menu (int player, int jeu)//player = {0 1} (joueur1<= 0 et joueur2<= 1)
                                                               //jeu = {0,1} (0 -> puiss4 et 1 -> TTT)
    {        
        Scanner sc = new Scanner(System.in);
        String str;
        if(jeu==0)
        {
            if(player==0)
            {
                System.out.println("Choisissez un premier joueur : \n - 0 pour un joueur physique,"
                        + "\n - 1 pour une IA, \n  ou 9 pour jouer Chuck Norris");
                str = sc.nextLine();
                System.out.println("Vous avez saisi : " + str);
                return (int) str.charAt(0)-'0';
            } 
            else if (player == 1)
            {
                System.out.println("Choisissez un challenger : \n - 0 pour un joueur physique,"
                        + "\n - 1 pour une IA, \n  ou 9 pour jouer Chuck Norris");
                str = sc.nextLine();
                System.out.println("Vous avez saisi : " + str);
                return (int) str.charAt(0)-'0';
            }
            } 
        else if (jeu==1)
        {
            if(player==0)
            {
                System.out.println("Choisissez un premier joueur : \n - 0 pour un joueur physique,"
                        + "\n - 1 pour une IA stupide, \n  - 2 pour une IA maline, \n ou 3 pour une IA Aléatoire");
                str = sc.nextLine();
                System.out.println("Vous avez saisi : " + str);
                return (int) str.charAt(0)-'0';
            } 
            else if (player == 1)
            {
                System.out.println("Choisissez un Challenger : \n - 0 pour un joueur physique,"
                                     + "\n - 1 pour une IA stupide, \n  - 2 pour une IA maline, \n ou 3 pour une IA Aléatoire");
                str = sc.nextLine();
                System.out.println("Vous avez saisi : " + str);
                return (int) str.charAt(0)-'0';
            }
        }
        return -1;
    }
    
    private static int main_menu()// retourne 0 pour le Puissance 4,
                                  //1 pour le morpion, 9 pour quitter
    {
    Scanner sc = new Scanner(System.in);
    System.out.println("Choisissez un jeu : 0 pour Puissance4, 1 pour le Morpion \n Si vous désirez quitter entrez 9");
    String str = sc.nextLine();
    System.out.println("Vous avez saisi : " + str);
    return (int) str.charAt(0)-'0';
    }
    
}