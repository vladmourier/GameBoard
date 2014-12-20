package fr.univ_lyon1.polytech.apo.gameboard;

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
    
    public static void main(String[] args) {
       
        gameboard = new ConnectFour();
        player_1 = new Human (5);
        random_player_2 = new Random_player (3);
        gameboard.display_gameboard();
        
        gameboard.play_loop(player_1, random_player_2);
        gameboard.display_history();
    }
    
}
