package fr.univ_lyon1.polytech.apo.gameboard;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YOU
 */
public abstract class GameBoard {
    public final int width;
    public final int length;
    private int[][] board;
    private List<Turn> history;

    public GameBoard(int width, int length) {
        this.width = width;
        this.length = length;
        board = new int[width][length];
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<length;j++)
            {
                board[i][j]=0;
            }
        }
        history = new ArrayList<Turn>() {};
    }

    public GameBoard(int width, int length, List<Turn> history) {
        this.width = width;
        this.length = length;
        int error = (new int[1])[1]; //TODO: things with history
    }

    public int[][] get_board() {
        return board;
    }
    
    public void set_board(int i, int j, int player) {
        board[i][j] = player;
    }
   public boolean to_history (Turn turn)
   {
       return history.add(turn);
   }
   public List<Turn> get_history ()
   {
       return history;
   }
   public Turn get_history (int i)
   {
       return history.get(i);
   }
   
   public void display_history ()
   {
        for (Turn history1 : history) {
            System.out.println(history1.toString());
        }
   }
    public abstract void play(Turn turn);
    public abstract void play_loop(Player a, Player b);
    public abstract void cancel();   
    public abstract Turn lastTurn();
   /* {
        return null;
    }
    */
    public abstract Player win();
    /*{
        return null;
    }*/

    @Override
    public abstract String toString();
    /*{
        return "TODO";
    }*/
    
    public abstract void display_gameboard();
    /*
   */ 
    
    public int get_board(int i, int j)
    {
        return board[i][j];
    }
}
