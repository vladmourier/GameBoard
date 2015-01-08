package fr.univ_lyon1.polytech.apo.gameboard;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YOU
 */
public abstract class GameBoard implements Serializable {
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
    
      public Player get_player (int i, int j)
    {
        int nb_coups_joues=get_history().size();
        for(int cpt = 1; cpt<=nb_coups_joues;cpt++)
        {
        if(get_history().get(nb_coups_joues-cpt).position.equals(new Position(i,j)));
        {
            return get_history().get(nb_coups_joues-cpt).player;
        }       

    }
        return null;
    }
    
    public boolean next_position (int i, int j, int direction)//direction: 0 -> cherches en haut  // 2-> cherches en diagonale \ vers le haut
    // 1 -> cherches en bas // 3 -> cherches en diagonale \ vers le bas // 4 -> cherches en diagonale / vers le haut, 5 pour le bas
    {
        if((i+1>=width&&direction==0)||(j-1>=length&&direction==1)||
          (i-1<0&&j+1>=length&&direction==2)||(direction==3&&i+1>=width&&j-1<0)||
          (direction==4&&i+1>=width&&j+1>=length)||(direction==5&&i-1<0&&j-1<0))
        {
            return false;
        }
            
        switch (direction)
        {
            case 0:
                if(board[i][j+1]==board[i][j])
                {
                    return true;
                }
            case 1:
                if(board[i][j-1]==board[i][j])
                {
                    return true;
                }
            case 2:
                 if(board[i+1][j]==board[i][j])
                {
                    return true;
                }
            case 3:
                 if(board[i-1][j]==board[i][j])
                {
                    return true;
                }
            case 4 : 
                if(board[i-1][j+1]==board[i][j])
                {
                    return true;
                }
            case 5 :
                if(board[i+1][j-1]==board[i][j])
                {
                    return true;
                }
            case 6 :
                if(board[i+1][j+1]==board[i][j])
                {
                    return true;
                }
            case 7 :
                if(board[i-1][j-1]==board[i][j])
                {
                    return true;
                }
        }
                        return false;
    }
}
